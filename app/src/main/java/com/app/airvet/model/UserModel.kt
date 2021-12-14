package com.app.airvet.model

import android.os.Parcelable
import com.app.airvet.api.model.Model
import com.app.airvet.utils.DateFormatter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("cell")
    val cell: String,
    @field:SerializedName("name")
    val name: Name?,
    @field:SerializedName("location")
    val location: Location?,
    @field:SerializedName("dob")
    val dob: DOB?,
    @field:SerializedName("picture")
    val picture: Picture?,
    @field:SerializedName("nat")
    val nat: String?,
) : Model(), Parcelable

@Parcelize
data class Name(
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("first")
    val first: String,
    @field:SerializedName("last")
    val last: String?
) : Model(), Parcelable

@Parcelize
data class DOB(
    @field:SerializedName("age")
    val age: Int,
    @field:SerializedName("date")
    val date: String
) : Model(), Parcelable {
    fun getDateValue():String{
        return DateFormatter.getStringToDateToFormeDateTimeNoMillisWithSecond(date)
    }
}

@Parcelize
data class Picture(
    @field:SerializedName("large")
    val large: String,
    @field:SerializedName("medium")
    val medium: String,
    @field:SerializedName("thumbnail")
    val thumbnail: String
) : Model(), Parcelable

@Parcelize
data class Location(
    @field:SerializedName("city")
    val city: String,
    @field:SerializedName("state")
    val state: String,
    @field:SerializedName("country")
    val country: String?,
    @field:SerializedName("postcode")
    val postcode: String?,
    @field:SerializedName("coordinates")
    val coordinates: Coordinates?,
    @field:SerializedName("street")
    val street: Street?
) : Model(), Parcelable

@Parcelize
data class Coordinates(
    @field:SerializedName("latitude")
    val latitude: String?,
    @field:SerializedName("longitude")
    val longitude: String?
) : Model(), Parcelable

@Parcelize
data class Street(
    @field:SerializedName("number")
    val number: Int,
    @field:SerializedName("name")
    val name: String?
) : Model(), Parcelable

