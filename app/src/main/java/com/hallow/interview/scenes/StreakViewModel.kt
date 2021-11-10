package com.hallow.interview.scenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hallow.interview.api.repositories.ProfileRepository
import com.hallow.interview.models.Month
import javax.inject.Inject
import nl.komponents.kovenant.then

class StreakViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _month = MutableLiveData<Month>()
    val month: LiveData<Month> = _month

    fun fetchData() {
        profileRepository.getActivity().then { days ->
            TODO("Create a current 'Month' object with the passed in days")
        }.success {
            _month.postValue(it)
        }
    }
}
