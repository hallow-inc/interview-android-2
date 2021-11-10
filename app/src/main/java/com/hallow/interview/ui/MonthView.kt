package com.hallow.interview.ui

import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hallow.interview.R
import com.hallow.interview.models.Day
import com.hallow.interview.models.Month
import com.hallow.interview.utilities.KotlinEpoxyHolder
import java.util.*

@EpoxyModelClass(layout = R.layout.month_view)
abstract class MonthView : EpoxyModelWithHolder<MonthView.Holder>() {

    class Holder : KotlinEpoxyHolder() {
        val titleView by bind<TextView>(R.id.month_title)
        val weeks by bind<LinearLayout>(R.id.month_weeks)
    }

    @EpoxyAttribute
    lateinit var month: Month

    override fun bind(holder: Holder) {
        holder.apply {
            weeks.removeAllViews()
            val context = weeks.context

            titleView.text = month.name

            // TODO: Create WeekView's for the given month
        }
    }




}
