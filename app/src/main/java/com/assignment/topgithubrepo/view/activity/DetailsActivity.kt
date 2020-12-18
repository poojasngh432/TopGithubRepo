package com.assignment.topgithubrepo.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.assignment.topgithubrepo.R
import com.assignment.topgithubrepo.data.local.GithubEntity
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var githubEntity: GithubEntity
    private lateinit var titleTV: AppCompatTextView
    private lateinit var descTV: AppCompatTextView
    private lateinit var repoLink: AppCompatTextView
    private lateinit var starsTV: AppCompatTextView
    private lateinit var watchersTV: AppCompatTextView
    private lateinit var forksTV: AppCompatTextView
    private lateinit var langTV: AppCompatTextView
    private lateinit var imgLangTV: AppCompatTextView
    private lateinit var avatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        githubEntity = intent.getParcelableExtra("INTENT_DATA")!!
        initViews()
        setData()
    }

    private fun setData() {
        Picasso.get()
            .load(githubEntity.avatar)
            .placeholder(R.drawable.ic_placeholder)
            .into(avatar)

        githubEntity.lang?.let {
            langTV.visibility = View.VISIBLE
            imgLangTV.visibility = View.VISIBLE
            langTV.text = githubEntity.lang
        }
        titleTV.text = githubEntity.repo
        repoLink.text = githubEntity.repoLink
        descTV.text = githubEntity.desc
        starsTV.text = githubEntity.stars
        watchersTV.text = githubEntity.added_stars
        forksTV.text = githubEntity.forks

        repoLink.setMovementMethod(LinkMovementMethod.getInstance())
        repoLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githubEntity.repoLink))
            startActivity(browserIntent)
        }

    }

    private fun initViews() {
        avatar = findViewById(R.id.item_profile_img)
        titleTV = findViewById(R.id.item_title)
        langTV = findViewById(R.id.item_language)
        imgLangTV = findViewById(R.id.item_img_language)
        repoLink = findViewById(R.id.repo_link)
        descTV = findViewById(R.id.desc)
        starsTV = findViewById(R.id.item_stars)
        watchersTV = findViewById(R.id.item_watchers)
        forksTV = findViewById(R.id.item_forks)
    }


}
