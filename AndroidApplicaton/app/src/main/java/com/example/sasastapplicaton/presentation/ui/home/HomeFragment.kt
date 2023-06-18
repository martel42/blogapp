package com.example.sasastapplicaton.presentation.ui.home

import PostListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.Blog
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Post
import com.example.sasastapplicaton.data.db.entity.Subs
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.example.sasastapplicaton.databinding.FragmentDashboardBinding
import com.example.sasastapplicaton.databinding.FragmentHomeBinding
import com.example.sasastapplicaton.presentation.activity.CreatePostActivity
import com.example.sasastapplicaton.presentation.ui.dashboard.DashboardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var postListAdapter: PostListAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        setupRecyclerView()



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.postList.observe(viewLifecycleOwner) {
            val a = it.filter { it.blogId != Provider.getInstanceBlogUID() }
            a.forEach(System.out::println)
            viewModel.subsList.observe(viewLifecycleOwner) {
                val b: List<Subs>? = it?.filter { it.userUid == Provider.userUID }
                b?.forEach(System.out::println)
                var c = listOf<Post>()
                if (b != null) {
                    c = a.filter { b.map { it.blogId }.contains(it.blogId) }
                    c.forEach(System.out::println)
                }
                postListAdapter.submitList(c)
            }
        }

    }

    private fun setupRecyclerView() {
        with(binding.rvPostList2) {
            postListAdapter = PostListAdapter()
            adapter = postListAdapter
            recycledViewPool.setMaxRecycledViews(
                30,
                20
            )
        }
    }
}