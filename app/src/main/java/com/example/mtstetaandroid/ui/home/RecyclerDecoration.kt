package com.example.mtstetaandroid.ui.home

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.extensions.dpToPx

object RecyclerDecoration:RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        val parent_adapter = parent.adapter
        if (parent_adapter != null){
            outRect.set(
                if (itemPosition % 2 == 0) parent.dpToPx(20f) else parent.dpToPx(10f),
                if (itemPosition < 2) parent.dpToPx(0f) else parent.dpToPx(50f),
                if (itemPosition % 2 == 1) parent.dpToPx(20f) else parent.dpToPx(10f),
                when (parent_adapter.itemCount % 2) {
                    0 -> if (itemPosition == parent_adapter.itemCount - 1 || itemPosition == parent_adapter.itemCount - 2) parent.dpToPx(
                        16f
                    ) else 0
                    1 -> if (itemPosition == parent_adapter.itemCount - 1) parent.dpToPx(16f) else 0
                    else -> 0
                }
            )
        }
        else {
            super.getItemOffsets(outRect, itemPosition, parent)
        }
    }
}