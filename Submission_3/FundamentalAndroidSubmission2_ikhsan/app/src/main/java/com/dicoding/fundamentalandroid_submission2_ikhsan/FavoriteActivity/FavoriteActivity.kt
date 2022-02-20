package com.dicoding.fundamentalandroid_submission2_ikhsan.FavoriteActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamentalandroid_submission2_ikhsan.database.FavoriteUser
import com.dicoding.fundamentalandroid_submission2_ikhsan.databinding.ActivityFavoriteBinding
import com.dicoding.fundamentalandroid_submission2_ikhsan.detailActivity.DetailUserActivity
import com.dicoding.fundamentalandroid_submission2_ikhsan.mainActivity.UserAdapter
import com.dicoding.fundamentalandroid_submission2_ikhsan.model.User

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.title = "Favorite User"



        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                with(intent) {
                    putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    putExtra(DetailUserActivity.EXTRA_ID, user.id)
                    putExtra(DetailUserActivity.EXTRA_URL, user.avatar_url)
                }
                startActivity(intent)
            }
        })

        binding.apply {
            rvFavoriteUser.setHasFixedSize(true)
            rvFavoriteUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavoriteUser.adapter = adapter
        }
        viewModel.getFavUser()?.observe(this, {
            if (it != null) {
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }
    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for (user in users) {
            val userMapped = User (
                user.login,
                user.id,
                user.avatar_url,
                user.url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}