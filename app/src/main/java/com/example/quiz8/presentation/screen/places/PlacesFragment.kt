package com.example.quiz8.presentation.screen.places

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.quiz8.R
import com.example.quiz8.databinding.FragmentPlacesBinding
import com.example.quiz8.presentation.base.BaseFragment
import com.example.quiz8.presentation.state.PlacesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Math.abs

@AndroidEntryPoint
class PlacesFragment : BaseFragment<FragmentPlacesBinding>(FragmentPlacesBinding::inflate) {

    private val imageAdapter = ImageRecyclerViewAdapter()
    private val viewModel: PlacesViewModel by viewModels()

    override fun setUp() {
        setUpPager()
    }

    override fun setUpListeners() {
    }

    override fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.placesStateFlow.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(placesState: PlacesState) {
        placesState.places?.let {
            imageAdapter.submitList(it)
        }
    }

    private fun setUpPager() {
        with(binding.viewPager) {
            adapter = imageAdapter
            offscreenPageLimit = 1

            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * abs(position))
            }
            setPageTransformer(pageTransformer)

            val itemDecoration = HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin
            )
            addItemDecoration(itemDecoration)
        }

    }

}