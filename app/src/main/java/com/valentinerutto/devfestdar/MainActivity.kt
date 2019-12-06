package com.valentinerutto.devfestdar

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: JokesAdapter
    lateinit var apiInterface: Call<List<RepoList>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        recyclerView = findViewById(R.id.recylerview)
        recyclerAdapter = JokesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        fab.setOnClickListener { view ->
            getJokes()

        }

        apiInterface = NetworkService.create().UserRepositories("valentinerutto")

    }

  private  fun getJokes() {
        //Sets up up the API call
      Toast.makeText(this@MainActivity,"clicked-failed",Toast.LENGTH_LONG).show()

      apiInterface.enqueue(object : Callback<List<RepoList>> {
            override fun onResponse(
                call: Call<List<RepoList>>,
                response: Response<List<RepoList>>
            ) {
                val followers = response.body()

                recyclerAdapter.setJokesListItems(followers!!)
                Log.d("RequestCall", followers.size.toString())
                Toast.makeText(this@MainActivity,followers.size.toString(),Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<List<RepoList>>, t: Throwable) {
                Log.e("RequestCall", "Request failed: $t")
                Toast.makeText(this@MainActivity,"clicked-failed",Toast.LENGTH_LONG).show()



            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
