package com.example.dogtranslate.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Rating {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null
    @ColumnInfo(name = "rating_score")
    var ratingScore : Int? = null
    @ColumnInfo(name = "comment")
    var comment : String? = null
    @ColumnInfo(name = "timestamp")
    var timestamp : Long? = null
}