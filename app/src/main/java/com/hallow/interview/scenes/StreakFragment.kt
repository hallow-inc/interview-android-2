package com.hallow.interview.scenes

import android.os.Bundle
import android.view.View
import com.hallow.interview.R
import com.hallow.interview.databinding.FragmentStreakBinding
import com.hallow.interview.streakHeader
import com.hallow.interview.ui.monthView

class StreakFragment : BaseViewModelFragment(R.layout.fragment_streak) {

    private val binding by viewLifecycle { FragmentStreakBinding.bind(it) }
    private val viewModel by viewModel<StreakViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchData()

        // Please look through all files before starting

        // TODO: Load in a month from the network request. You'll need to do the processing of days inside StreakViewModel
        // TODO: Pass the loaded month to the pre-made EpoxyRecyclerView in onViewCreated.
        // TODO: Complete the MonthView bind() method
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.withModels {
            val month = viewModel.month.value

            if (month != null) {
                streakHeader {
                    id("header")
                    title("Activity")
                }

                monthView {
                    id(month.name)
                    month(month)
                }
            }
        }
    }
}
