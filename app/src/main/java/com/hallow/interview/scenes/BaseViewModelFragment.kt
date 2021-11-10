package com.hallow.interview.scenes

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.hallow.interview.di.module.ViewModelProviderFactory
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class BaseViewModelFragment(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector() = androidInjector

    inline fun <reified T : ViewModel> viewModel(): Lazy<T> = viewModels(factoryProducer = { providerFactory })

    /**
     * An extension to bind and unbind a value based on the view lifecycle of a Fragment.
     * The binding will be unbound in onDestroyView.
     *
     * @throws IllegalStateException If the getter is invoked before the binding is set,
     *                               or after onDestroyView an exception is thrown.
     */
    fun <T> viewLifecycle(
        initialise: (View) -> T
    ): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {
        private var binding: T? = null

        init {
            // Observe the view lifecycle of the Fragment.
            // The view lifecycle owner is null before onCreateView and after onDestroyView.
            // The observer is automatically removed after the onDestroy event.
            this@BaseViewModelFragment
                .viewLifecycleOwnerLiveData
                .observe(this@BaseViewModelFragment, { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T = this.binding ?: initialise(requireView()).also {
            this.binding = it.apply {
                if (this is ViewDataBinding) lifecycleOwner = viewLifecycleOwner
            }
            this.binding
        }
    }
}
