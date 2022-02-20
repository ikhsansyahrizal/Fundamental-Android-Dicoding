package com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity

import FollowAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.fundamentalandroid_submission2_ikhsan.R
import com.dicoding.fundamentalandroid_submission2_ikhsan.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detalBinding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel


    companion object {
        const val EXTRA_USERNAME = "extra_username"

        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detalBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detalBinding.root)

        val actionbar = supportActionBar
        actionbar?.title = "Detail User"

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val username = intent.getStringExtra(EXTRA_USERNAME) ?: "null"

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel.setUserDetail(username)
        Log.d("Detail", "username: $username")

        viewModel.getUserDetail().observe(this, {
            detalBinding.apply {
                Glide.with(this@DetailUserActivity)
                    .load(it.avatar_url)
                    .centerCrop()
                    .into(imgAvatar)

                tvUsername.text = it.login
                tvName.text = it.name
                tvLocation.text = it.location
                tvCompany.text = it.company
                tvBio.text = it.bio
                tvFollowers.text = "${it.followers}"
                tvFollowing.text = "${it.following}"
            }
        })


        val followAdapter = FollowAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = followAdapter
        val tabs: TabLayout = findViewById(R.id.table)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        detalBinding.btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            val link =
                "https://github.com/$username"
            share.putExtra(Intent.EXTRA_SUBJECT, "More Info ")
            share.putExtra(Intent.EXTRA_TEXT, link)
            startActivity(Intent.createChooser(share, "Share to"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

