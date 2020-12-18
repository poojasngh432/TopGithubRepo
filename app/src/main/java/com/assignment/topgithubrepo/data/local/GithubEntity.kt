package com.assignment.topgithubrepo.data.local

import android.os.Parcel
import android.os.Parcelable

class GithubEntity(
    var repo: String?,
    var username: String?,
    var repoLink: String?,
    var desc: String?,
    var lang: String?,
    var stars: String?,
    var forks: String?,
    var avatar: String?,
    var added_stars: String?
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(repo)
        writeString(username)
        writeString(repoLink)
        writeString(desc)
        writeString(lang)
        writeString(stars)
        writeString(forks)
        writeString(avatar)
        writeString(added_stars)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GithubEntity> = object : Parcelable.Creator<GithubEntity> {
            override fun createFromParcel(source: Parcel): GithubEntity = GithubEntity(source)
            override fun newArray(size: Int): Array<GithubEntity?> = arrayOfNulls(size)
        }
    }
}
