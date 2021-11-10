package com.hallow.interview.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("cornerRadiusOverride")
fun setCornerRadius(view: View, cornerRadius: Float) {
    view.applyRoundedCorners(cornerRadius)
}
