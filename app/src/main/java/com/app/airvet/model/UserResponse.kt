package com.app.airvet.model

import android.os.Parcelable
import com.app.airvet.api.model.Model
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @field:SerializedName("results")
    val results: List<UserModel>,
    @field:SerializedName("info")
    val info: InfoModel
) : Model(), Parcelable

@Parcelize
data class InfoModel(
    @field:SerializedName("results")
    val results: Int?,
    @field:SerializedName("page")
    val page: Int?,
    @field:SerializedName("seed")
    val seed: String?,
    @field:SerializedName("version")
    val version: String?
) : Parcelable

