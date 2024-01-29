package com.example.dogtranslate.data.local.model

import android.os.Parcel
import android.os.Parcelable

class Dog() : Parcelable {
    var id : Int? = null
    var title: String? = null
    var srcDog: Int? = null
    var idSound = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        srcDog = parcel.readValue(Int::class.java.classLoader) as? Int
        idSound = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeValue(srcDog)
        parcel.writeInt(idSound)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dog> {
        override fun createFromParcel(parcel: Parcel): Dog {
            return Dog(parcel)
        }

        override fun newArray(size: Int): Array<Dog?> {
            return arrayOfNulls(size)
        }
    }
}