package com.dicoding.fundamentalandroid_submission1_ikhsan

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.fundamentalandroid_submission1_ikhsan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvUsers.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()


        val actionbar = supportActionBar
        actionbar!!.title = "List User"

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }
    }

    private val listUser: ArrayList<User>
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)

            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataRepository = resources.getStringArray(R.array.repository)

            val listUser = ArrayList<User>()
            for (i in dataUsername.indices) {
                val user = User(dataUsername[i],
                    dataName[i],
                    dataLocation[i],
                    dataPhoto.getResourceId(i, -1),
                    dataFollowers[i],
                    dataFollowing[i],
                    dataCompany[i],
                    dataRepository[i])
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.adapter = listUserAdapter


        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                with(intent) {
                    putExtra(DetailActivity.EXTRA_USER, data)
                }
                startActivity(intent)
            }
        })
    }

}