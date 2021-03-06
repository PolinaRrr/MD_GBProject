package com.example.md_gbproject.view.layouts

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class NestedBehavior(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        Log.d("@@@", "$child")
        if (dependency is AppBarLayout) {
            val appbar = dependency as AppBarLayout
            child.y = appbar.height.toFloat() + appbar.y
        } else {

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}