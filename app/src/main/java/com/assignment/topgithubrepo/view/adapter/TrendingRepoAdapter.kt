package com.assignment.topgithubrepo.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.topgithubrepo.R
import com.assignment.topgithubrepo.data.remote.model.Items
import com.squareup.picasso.Picasso

class TrendingRepoAdapter(
    val context: Context,
    private val rowLayout: Int,
    private val repositories: List<Items>
) : RecyclerView.Adapter<TrendingRepoAdapter.RepositoryViewHolder>()
{

    class RepositoryViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view)
    {
        internal var repoName: AppCompatTextView = view.findViewById(R.id.repo_name)
        internal var username: AppCompatTextView = view.findViewById(R.id.username)
        internal var avatar: ImageView = view.findViewById(R.id.avatar_iv)
        internal var cardView: CardView = view.findViewById(R.id.card_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout,parent,false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.repoName.text = repositories[position].repoName
        holder.username.text = repositories[position].repoName
        val avatar = repositories[position].avatars?.get(0)

        Picasso
            .get()
            .load(avatar)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(holder.avatar)

        holder.cardView.setOnClickListener{
            Log.d("TOPREPOTEST",repositories[position].repoName)
        }
    }

    override fun getItemCount(): Int
    {
        return repositories.size
    }

}