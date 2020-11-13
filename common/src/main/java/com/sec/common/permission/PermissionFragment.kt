package com.sec.common.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sec.common.intents.ForResultViewModel

/**
 * PermissionFragment
 */
class PermissionFragment : Fragment() {

    private val viewModel by lazy {
        activityViewModels<ForResultViewModel>().value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun requestPermissionsByFragment(permissions: Array<String>, callback: PermissionsCallback) {
        val requestCode = viewModel.atomicInteger.getAndIncrement().also {
            viewModel.permissionSparseArray.put(it, callback)
        }
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val neverAskPermissions = mutableListOf<String>()
        val deniedPermissions = mutableListOf<String>()
        val grantedPermissions = mutableListOf<String>()
        permissions.forEachIndexed { index, permission ->
            if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                if (shouldShowRequestPermissionRationale(permission)) {
                    deniedPermissions.add(permission)
                } else {
                    neverAskPermissions.add(permission)
                }
            } else {
                grantedPermissions.add(permission)
            }
        }
        val permissionsCallback: PermissionsCallback? =
            viewModel.permissionSparseArray.get(requestCode, null).also {
                viewModel.permissionSparseArray.delete(requestCode)
            }
        if (deniedPermissions.isNotEmpty()) {
            // denied
            permissionsCallback?.denied(deniedPermissions)
        }
        if (neverAskPermissions.isNotEmpty()) {
            // never ask
            permissionsCallback?.neverAskAgain(neverAskPermissions)
        }
        if (deniedPermissions.isEmpty() && neverAskPermissions.isEmpty()) {
            // granted
            permissionsCallback?.granted()
        }
    }
}