package com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamentalandroid_submission2_ikhsan.R
import com.dicoding.fundamentalandroid_submission2_ikhsan.databinding.FragFollowBinding
import com.dicoding.fundamentalandroid_submission2_ikhsan.mainActivity.UserAdapter

class FragmentFollowing : Fragment(R.layout.frag_follow) {

    private var _binding: FragFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        username = arg?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragFollowBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollowUsers.setHasFixedSize(true)
            rvFollowUsers.layoutManager = LinearLayoutManager(activity)
            rvFollowUsers.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}