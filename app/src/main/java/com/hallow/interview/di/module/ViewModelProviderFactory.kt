package com.hallow.interview.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject
constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) { // if the viewmodel has not been created
            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            creator = creators.filter { modelClass.isAssignableFrom(it.key) }.toList().first().second
        }

        // return the Provider
        return creator.get() as T
    }
}
