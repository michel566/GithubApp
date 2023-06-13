package com.michelbarbosa.githubapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.michelbarbosa.githubapp.R
import com.michelbarbosa.githubapp.databinding.FragmentUserDetailBinding
import com.michelbarbosa.githubapp.model.GitRepositoryDomain
import com.michelbarbosa.githubapp.model.UserDetailDomain
import com.michelbarbosa.githubapp.ui.activity.MainActivity
import com.michelbarbosa.githubapp.ui.fragments.adapter.repository.RepositoryAdapter
import com.michelbarbosa.githubapp.ui.viewmodel.UserViewModel
import com.michelbarbosa.githubapp.utils.UiUtil.setupImage
import com.michelbarbosa.githubapp.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val args: UserDetailFragmentArgs by navArgs()
    private val userViewModel: UserViewModel by viewModels()

    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireActivity() is MainActivity)
            mainActivity = requireActivity() as MainActivity
        else
            throw Exception("Only MainActivity can host UserDetailFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.closeSearch()
        mainActivity.onBackPressedDispatcher.addCallback {
            mainActivity.setupToolbarTitle(getString(R.string.app_name))
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.binding.tvTitle.isVisible = false
        loadGetUserDetail()
    }

    private fun loadGetUserDetail() {
        args.userDataWrapper.userDetail?.let {
            setupWidgets(it)
        } ?: kotlin.run {
            lifecycleScope.launch {
                viewLifecycleOwner.lifecycle
                    .repeatOnLifecycle(Lifecycle.State.STARTED){
                        userViewModel.getUserDetail(args.userDataWrapper.login).let {
                            setupWidgets(it)
                        }
                    }
            }
        }
    }

    private fun setupWidgets(user: UserDetailDomain) = with(binding) {
        user.apply {
            mainActivity.setupToolbarTitle(login)
            loadRepositories(login)
            setupImage(requireContext(), avatarUrl, ivImage)
            setTextView(tvName, R.string.user_detail_name, name)
            setTextView(tvEmail, R.string.user_detail_email, email)
            setTextView(tvCompany, R.string.user_detail_company, company)
            setTextView(tvPRepos, R.string.user_detail_p_repository, publicRepos)
            setTextView(tvPGists, R.string.user_detail_p_gists, publicGists)
            setTextView(tvTwitterUsername, R.string.user_detail_twitter_username, twitterUsername)
            setTextView(tvFollowers, R.string.user_detail_followers, followers)
            setTextView(tvCreatedAt, R.string.user_detail_created_at, Util.normalizePatternDate(createdAt))
            setTextView(tvUpdatedAt, R.string.user_detail_updated_at, Util.normalizePatternDate(updatedAt))
        }
    }

    private fun loadRepositories(login: String) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED){
                    userViewModel.listRepositories(
                        userName = login,
                        onListRepositories = ::initAdapter,
                        onListEmpty = {}
                    )
                }
        }
    }

    private fun initAdapter(repositories: List<GitRepositoryDomain>) {
        val reposAdapter = RepositoryAdapter()
        with(binding.rvRepositories){
            scrollToPosition(0)
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = reposAdapter
        }
        reposAdapter.submitList(repositories)
    }

    private fun setTextView(view: TextView, resText: Int, data: String?){
        if (data.isNullOrEmpty()){
            view.isVisible = false
        } else {
            view.isVisible = true
            view.text = getString(resText, data)
        }
    }

    private fun setTextView(view: TextView, resText: Int, data: Int){
        view.isVisible = true
        view.text = getString(resText, data)
    }


}