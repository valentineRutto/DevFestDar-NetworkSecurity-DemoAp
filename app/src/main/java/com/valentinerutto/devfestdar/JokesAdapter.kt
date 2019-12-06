package com.valentinerutto.devfestdar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokesAdapter(val context: Context) : RecyclerView.Adapter<JokesAdapter.MyViewHolder>() {

    var repoList: List<RepoList> = listOf()
    var name: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        for (i in 0 until repoList.size) {
//            name += repoList[i].name
//            holder.tvName.text = name
//
//        }
      holder.bind(repoList[position])

    }

    fun setJokesListItems(jokesList: List<RepoList>) {
        this.repoList = jokesList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repoList: RepoList) {
            tvName.text = repoList.name
        }

        val tvName: TextView = itemView.findViewById(R.id.setup)

    }
}