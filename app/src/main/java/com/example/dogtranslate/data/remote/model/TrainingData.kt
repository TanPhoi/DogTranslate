package com.example.dogtranslate.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TrainingData(
    @SerializedName("data")
    var data : List<DataTraining>? = null,
    @SerializedName("links")
    var links : Links? = null
)

data class Links (
    @SerializedName("self")
    var self : String? = null,
    @SerializedName("current")
    var current : String? = null,
    @SerializedName("next")
    var next : String? = null,
    @SerializedName("last")
    var last : String? = null
)

data class DataTraining (
    @SerializedName("id")
    var id : String? = null,
    @SerializedName("type")
    var type : String? = null,
    @SerializedName("attributes")
    var attributes : Attributes? = null,
    @SerializedName("relationships")
    var relationships : Relationships? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        TODO("attributes"),
        TODO("relationships")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataTraining> {
        override fun createFromParcel(parcel: Parcel): DataTraining {
            return DataTraining(parcel)
        }

        override fun newArray(size: Int): Array<DataTraining?> {
            return arrayOfNulls(size)
        }
    }
}

data class Attributes (
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("description")
    var description : String? = null,
    @SerializedName("life")
    var life : Life? = null,
    @SerializedName("male_weight")
    var maleWeight : MaleWeight? = null,
    @SerializedName("female_weight")
    var femaleWeight : FemaleWeight? = null,
    @SerializedName("hypoallergenic")
    var hypoallergenic : Boolean? = null
)

data class Relationships(
    @SerializedName("group")
    var group : Group? = null
)

data class Group(
    @SerializedName("data_group")
    var dataGroup : DataGroup? = null
)

data class DataGroup(
    @SerializedName("id")
    var id : String? = null,
    @SerializedName("type")
    var type : String? = null
)

data class Life(
    @SerializedName("max")
    var max : Int? = null,
    @SerializedName("min")
    var min : Int? = null
)

data class MaleWeight(
    @SerializedName("max")
    var max : Int? = null,
    @SerializedName("min")
    var min : Int? = null
)

data class FemaleWeight(
    @SerializedName("max")
    var max : Int? = null,
    @SerializedName("min")
    var min : Int? = null
)