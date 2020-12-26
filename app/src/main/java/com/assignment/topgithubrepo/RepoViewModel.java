package com.assignment.topgithubrepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.topgithubrepo.data.local.GithubEntity;

import java.util.List;

public class RepoViewModel extends ViewModel {
    LiveData<List<GithubEntity>> reposList;

    public LiveData<List<GithubEntity>> getReposList() {
        return reposList;
    }

    public void setReposList(LiveData<List<GithubEntity>> reposList) {
        this.reposList = reposList;
    }
}
