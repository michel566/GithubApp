package com.michelbarbosa.githubapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.michelbarbosa.githubapp.R
import com.michelbarbosa.githubapp.databinding.FragmentUserBinding
import com.michelbarbosa.githubapp.model.UserDataWrapper
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.ui.callbacks.MainCallback
import com.michelbarbosa.githubapp.ui.fragments.adapter.user.UserAdapter
import com.michelbarbosa.githubapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var userAdapter: UserAdapter
    private val viewModel: UserViewModel by viewModels()
    private lateinit var callback: MainCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireActivity() is MainCallback)
            callback = requireActivity() as MainCallback
        else
            throw Exception("Activity must be implement MainCallback")

        callback.onAttachedUserFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    fun loadData() {
        initAdapter()
        fetchListUsers()
    }

    private fun initAdapter() {
        userAdapter = UserAdapter(::goToUserDetail)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        with(binding.rvUsers) {
            scrollToPosition(0)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun goToUserDetail(user: UserDomain) {
        findNavController().navigate(
            UserFragmentDirections.actionUserFragmentToUserDetailFragment(
                UserDataWrapper(
                    login = user.login,
                    userDetail = viewModel.userDetailDomain
                )
            )
        )
    }

    private fun fetchListUsers() {
        binding.errorLayout.isVisible = false

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listUsers().collectLatest { pagingData ->
                    userAdapter.submitData(pagingData)
                }
            }
        }
    }

    fun onQueryTextChange(text: CharSequence) {
        binding.errorLayout.isVisible = false

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filterUsers(
                    matchItensFound = userAdapter.itemCount,
                    charArray = text,
                    onFilterData = (::submitOnAdapter),
                    onFindUserSuccess = (::submitOnAdapter),
                    onFindUserEmpty = {
                        setErrorScreen(
                            errorMessage = getString(R.string.user_search_empty_error),
                            onTryAgainListener = {
                                onQueryTextChange(text)
                            }
                        )
                    }
                )
            }
        }
    }

    fun onQueryTextSubmit(text: String) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.findUser(
                    charArray = text,
                    onFindUserSuccess = (::submitOnAdapter),
                    onFindUserEmpty = {
                        setErrorScreen(
                            errorMessage = getString(R.string.user_search_empty_error),
                            onTryAgainListener = null
                        )
                    }
                )
            }
        }
    }

    private fun submitOnAdapter(userDetail: PagingData<UserDomain>){
        lifecycleScope.launch {
            userAdapter.submitData(userDetail)
        }
    }

    private fun setErrorScreen(
        errorMessage: String?,
        onTryAgainListener: (() -> Unit)?
    ) =
        with(binding) {
            errorMessage?.let {
                errorLayout.isVisible = true
                errorLayout.setText(it)
            } ?: kotlin.run {
                errorLayout.isVisible = false
            }

            onTryAgainListener?.run {
                errorLayout.setOnClickListener {
                    invoke()
                }
            }
        }

}