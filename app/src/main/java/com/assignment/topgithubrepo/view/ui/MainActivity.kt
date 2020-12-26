package com.assignment.topgithubrepo.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.topgithubrepo.R
import com.assignment.topgithubrepo.RepoViewModel
import com.assignment.topgithubrepo.data.local.GithubEntity
import com.assignment.topgithubrepo.data.remote.api.TrendingGithubService
import com.assignment.topgithubrepo.data.remote.model.Items
import com.assignment.topgithubrepo.data.remote.model.Repo
import com.assignment.topgithubrepo.view.adapter.TrendingRepoAdapter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {

    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private val TAG = MainActivity.javaClass.name
    private lateinit var editText: AppCompatEditText
    private lateinit var btn: Button
    private lateinit var languageStr: String
    private lateinit var timeStr: String
    private lateinit var viewmodel: RepoViewModel
    public lateinit var newlist: MutableList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setData()
        setListeners()
        apiCall(languageStr)

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.filter_list)
        mProgressBar = findViewById(R.id.progressbar_main_activity)
        editText = findViewById(R.id.search_edittext)
        btn = findViewById(R.id.btn_search)
    }

    private fun setData() {
        languageStr = "Java"
        timeStr = "weekly"
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewmodel = ViewModelProvider(this).get(RepoViewModel::class.java)

    }

    private fun setListeners() {
        btn.setOnClickListener {
            languageStr = editText.text.toString()
            apiCall(languageStr)
        }
    }

    private fun apiCall(languageStr: String) {
        val  retrofit = Retrofit.Builder()
            .baseUrl(TrendingGithubService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val trendingGithubService = retrofit.create(TrendingGithubService::class.java)
        trendingGithubService.getTrendingRepos(languageStr, timeStr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Repo> {
                override fun onSuccess(t: Repo) {
                    Log.d(TAG, "repositoriesSingle::onSuccess")
                    val data = t.items
                    data?.get(0)?.repoName?.let { Log.d(TAG, it) }
                    val list = data?.toMutableList()
                    recyclerView.adapter =
                        TrendingRepoAdapter(this@MainActivity,
                            R.layout.rv_single_item, data!!)

                    val nameObserver = Observer<GithubEntity> { data ->

                    }

                   // viewmodel.reposList.observe(this@MainActivity, list)


                    hideProgressBar()

                }

                override fun onError(e: Throwable) {
                    hideProgressBar()
                    Log.d(TAG, "repositoriesSingle::onError")
                    Toast.makeText(
                        this@MainActivity,
                        "Error connecting to GitHub",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSubscribe(d: Disposable) {
                    showProgressBar()
                    Log.d(TAG, "repositoriesSingle::onSubscribe")
                }
            })
    }

    private fun hideProgressBar() {
        Log.d(TAG, "::hideProgressBar:")
        mProgressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        Log.d(TAG, "::hideProgressBar:")
        mProgressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }
}

