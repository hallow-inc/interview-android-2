package com.hallow.interview.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Outline
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

fun Int.toDp(resources: Resources): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), resources.displayMetrics).toInt()

fun Int.toDp(context: Context) = toDp(context.resources)

fun Date.formatDayOfWeekLetter(): String = SimpleDateFormat("EEEEE").format(this)

fun Context.colorFromAttr(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return ContextCompat.getColor(this, typedValue.resourceId)
}

fun Context.getAttributeColor(attributeId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attributeId, typedValue, true)
    return typedValue.data
}

fun View.applyRoundedCorners(radius: Float) {
    outlineProvider = RoundedCornerOutlineProvider(radius)
    clipToOutline = true
}

private class RoundedCornerOutlineProvider(private val cornerRadius: Float) : ViewOutlineProvider() {
    override fun getOutline(view: View?, outline: Outline?) {
        view?.run { outline?.setRoundRect(0, 0, width, height, cornerRadius) }
    }
}
