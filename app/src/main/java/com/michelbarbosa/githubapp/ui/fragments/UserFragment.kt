package com.michelbarbosa.githubapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.michelbarbosa.githubapp.databinding.FragmentUserBinding
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
        initAdapter()
        fetchListUsers()
    }

    private fun fetchListUsers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listUsers().collectLatest { pagingData ->
                    userAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initAdapter() {
        userAdapter = UserAdapter(::goToUserRepository)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        with(binding.rvUsers) {
            scrollToPosition(0)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun goToUserRepository(user: UserDomain) {
        Toast.makeText(requireContext(), "id = ${user.id}", Toast.LENGTH_SHORT).show()
    }

    fun onChangeText(text: String) {
        Toast.makeText(requireContext(), "text = $text", Toast.LENGTH_SHORT).show()
    }


}