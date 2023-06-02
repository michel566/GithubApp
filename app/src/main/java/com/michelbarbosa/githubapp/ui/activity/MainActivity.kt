package com.michelbarbosa.githubapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.michelbarbosa.githubapp.R
import com.michelbarbosa.githubapp.databinding.ActivityMainBinding
import com.michelbarbosa.githubapp.ui.callbacks.MainCallback
import com.michelbarbosa.githubapp.ui.fragments.UserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var search: SearchView

    private lateinit var userFragment: UserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWidgets()
    }

    private fun setupWidgets() = with(binding) {
        setupSearchView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("RestrictedApi")
    private fun setupSearchView() = with(binding) {
        search = (toolbar.menu as MenuBuilder).actionItems[0].actionView as SearchView

        search.setOnQueryTextFocusChangeListener { view, isFocused ->
            if (isFocused)
                tvTitle.isVisible = false
        }

        search.setOnCloseListener {
            tvTitle.isVisible = true
            false
        }

        search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { text ->
                        if (text.isNotEmpty())
                            userFragment.onQueryTextChange(text)
                        else
                            userFragment.loadData()
                    }
                    return false
                }
            }
        )
    }

    override fun onAttachedUserFragment(fragment: UserFragment) {
        userFragment = fragment
    }

}