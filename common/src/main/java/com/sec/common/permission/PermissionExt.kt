package com.sec.common.permission

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

const val TAG = "PermissionFragment"

/**
 * 权限请求
 */
inline fun FragmentActivity.request(
    vararg permissions: String,
    crossinline dsl: PermissionsCallback.() -> Unit
) {
    val permissionsCallback = PermissionsCallback().apply(dsl)
    val needRequestPermissions = permissions.filter { !isGranted(it) }
    if (needRequestPermissions.isEmpty())
        permissionsCallback.granted()
    else
        getPermissionFragment(this).requestPermissionsByFragment(
            needRequestPermissions.toTypedArray(),
            permissionsCallback
        )
}

fun getPermissionFragment(activity: FragmentActivity): PermissionFragment {
    var fragment = activity.supportFragmentManager.findFragmentByTag(TAG)
    if (fragment == null) {
        fragment = PermissionFragment()
        activity.supportFragmentManager.beginTransaction().add(fragment, TAG).commitNow()
    }
    return fragment as PermissionFragment
}

fun FragmentActivity.isGranted(permission: String) =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED