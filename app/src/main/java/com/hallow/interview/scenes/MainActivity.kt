package com.hallow.interview.scenes

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.hallow.interview.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false

    private inline fun <T : ViewBinding> viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) = lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}
