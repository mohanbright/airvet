package com.app.airvet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.airvet.ui.MainVM
import com.app.airvet.api.Resource
import com.app.airvet.common.*
import com.app.airvet.databinding.FragmentHomeBinding
import com.app.airvet.model.UserModel
import com.app.airvet.ui.adapter.PostAdapter
import com.app.healios.ui.common.navigateTo
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class HomeFragment : BaseFragment(), TabLayout.OnTabSelectedListener {
    private val viewModel: MainVM by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var pageCount: Int = 1
    private var newList = ArrayList<UserModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        Timber.e("onCreateView")
        setUpObservers()
        viewModel.loadRandomUsers(pageCount)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI() // Define UI.
        hideActionBar()
        Timber.e("onViewCreated")

    }

    private fun setupUI() {
        setupTab()
        setupSwipeRefresh()
        setupListenerPostListScroll()
    }

    private fun setupTab() {
        binding.tablayout.addOnTabSelectedListener(this)
        var tabs: ViewGroup? = binding.tablayout.getChildAt(0) as ViewGroup?
        for (i in 0 until tabs!!.childCount - 1) {
            val tab = (binding.tablayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(25, 0, 25, 0)
            tab.requestLayout()
        }
    }

    private fun setupSwipeRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            resetVariables()
            viewModel.loadRandomUsers(pageCount)
        }
    }

    // Scroll listener.
    private fun setupListenerPostListScroll() {
        binding.componentList.addInfiniteScrollListener(
            scrolling = { currentListSize: Int -> postListScrollUpAction(currentListSize) },
            comeEnd = { }
        )
    }

    // Infinite scroll action.
    private fun postListScrollUpAction(listSize: Int) {
        // Avoid redundant calls.
        if (!viewModel.isStillLoading) {
            pageCount++
            viewModel.loadRandomUsers(pageCount)
        }
    }


    private fun setUpObservers() {
        val mAdapter = PostAdapter(onItemClick = { userData ->
            navigateTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(userData))
        })

        binding.componentList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.usersList.observe(getThisActivity(), { usersList ->
            usersList?.let {
                if (pageCount == 1)
                    newList.clear()

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        it.data?.let { it1 -> newList.addAll(it1) }
                        mAdapter.submitList(newList.toMutableList())

                        callLoadingProgressBar(false)
                    }
                    Resource.Status.ERROR -> {
                        callLoadingProgressBar(false)
                        showHideNetworkError(true)
                    }
                    Resource.Status.LOADING -> {
                        callLoadingProgressBar(true)
                    }
                }
            }
        })
    }


    private fun showHideNetworkError(isVisible: Boolean = false) {
        binding.errorLayout.parentError.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    // Manage progress bar.
    private fun callLoadingProgressBar(isLoading: Boolean) {
        viewModel.isLoading.postValue(isLoading)
        binding.swiperefresh.isRefreshing = false
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {
        resetVariables()
        when (tab?.position) {
            0 -> viewModel.gender = ""
            1 -> viewModel.gender = "male"
            2 -> viewModel.gender = "female"
        }
        viewModel.loadRandomUsers(pageCount)
    }

    private fun resetVariables() {
        pageCount = 1
        viewModel.gender = ""
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

}