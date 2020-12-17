package com.assignment.topgithubrepo.data.remote.api

import com.assignment.topgithubrepo.data.remote.model.Repo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingGithubService {

    companion object{
        const val BASE_URL = "https://trendings.herokuapp.com/"
    }

    @GET("repo")
    fun getTrendingRepos(@Query("lang") lang: String,
                         @Query("since") since: String
    ): Single<Repo>


}