package com.example.dogtranslate.data.local.model

import android.os.Parcel
import android.os.Parcelable

class Language() : Parcelable{
    var languageName : String? = null
    var isoLanguage : String? = null
    var image : Int? = null

    constructor(parcel: Parcel) : this() {
        languageName = parcel.readString()
        isoLanguage = parcel.readString()
        image = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(languageName)
        parcel.writeString(isoLanguage)
        parcel.writeValue(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Language> {
        override fun createFromParcel(parcel: Parcel): Language {
            return Language(parcel)
        }

        override fun newArray(size: Int): Array<Language?> {
            return arrayOfNulls(size)
        }
    }
}