package com.assignment.topgithubrepo.view.ui.repolist

import androidx.lifecycle.MutableLiveData
import com.assignment.topgithubrepo.data.remote.response.Item
import com.assignment.topgithubrepo.data.repository.RepoRepository
import com.assignment.topgithubrepo.viewmodel.BaseViewModel

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}