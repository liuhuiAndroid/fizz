package com.sec.common.adapter

interface SortedItemCell : ItemCell {

    /**
     *  排序标志
     */
    fun order(): Long
}