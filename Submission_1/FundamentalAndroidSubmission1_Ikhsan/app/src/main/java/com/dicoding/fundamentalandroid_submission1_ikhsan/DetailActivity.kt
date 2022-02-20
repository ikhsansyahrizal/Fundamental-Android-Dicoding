package com.dicoding.fundamentalandroid_submission1_ikhsan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.fundamentalandroid_submission1_ikhsan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Detail User"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val username = user.username.toString()
        val name = user.name.toString()
        val location = user.location.toString()
        val avatar = user.avatar.toInt()
        val followes = user.followers.toString()
        val following = user.following.toString()
        val company = user.company.toString()
        val repository = user.repository.toString()

        binding.imgAvatar.setImageResource(avatar)
        binding.tvFollowers.text = followes
        binding.tvFollowing.text = following
        binding.tvUsername.text = username
        binding.tvName.text = name
        binding.tvLocation.text = location
        binding.tvRepository.text = repository
        binding.tvCompany.text = company

        binding.btnShare.setOnClickListener() {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            val link =
                "https://github.com/" + username
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