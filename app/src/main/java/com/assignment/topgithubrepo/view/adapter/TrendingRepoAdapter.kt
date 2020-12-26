package com.assignment.topgithubrepo.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.assignment.topgithubrepo.R
import com.assignment.topgithubrepo.data.local.GithubEntity
import com.assignment.topgithubrepo.data.remote.model.Items
import com.assignment.topgithubrepo.view.ui.repodetail.DetailsActivity
import com.squareup.picasso.Picasso
import androidx.core.util.Pair

class TrendingRepoAdapter(
    val context: Activity,
    private val rowLayout: Int,
    private val repositories: List<Items>
) : RecyclerView.Adapter<TrendingRepoAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        internal var repoName: AppCompatTextView = view.findViewById(R.id.repo_name)
        internal var username: AppCompatTextView = view.findViewById(R.id.username)
        internal var avatar: ImageView = view.findViewById(R.id.avatar_iv)
        internal var cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.repoName.text = repositories[position].repoName
        val avatar = repositories[position].avatars?.get(0)

        Picasso
            .get()
            .load(avatar)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(holder.avatar)

        val arr = repositories[position].repoName.split("/").toTypedArray()
        val username = arr[0]
        holder.username.text = username

        holder.cardView.setOnClickListener {

            val intent = Intent(context, DetailsActivity::class.java)

            val githubEntity = GithubEntity(
                repositories[position].repoName,
                username,
                repositories[position].repoLink,
                repositories[position].desc,
                repositories[position].lang,
                repositories[position].stars,
                repositories[position].forks,
                repositories[position].avatars?.get(0),
                repositories[position].addedStars
            )
            intent.putExtra("INTENT_DATA", githubEntity)

            val p1 = Pair.create<View?, String?>(holder.avatar, "image")
            val p2 = Pair.create<View?, String?>(holder.repoName, "text")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, p1, p2)
            ActivityCompat.startActivity(context, intent, options.toBundle())
        }
    }


    override fun getItemCount(): Int {
        return repositories.size
    }

}