package com.example.mtstetaandroid.data

import androidx.room.*


@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

@Entity(tableName = "movies")
class MovieEntity (

    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "rateScore") val rateScore: Int?,
    @ColumnInfo(name = "ageRestriction") val ageRestriction: Int?,
    @ColumnInfo(name = "imageURL") val imageURL: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)




@Dao
interface MovieDao{
    @Insert
    fun insertAll(movies: List<MovieEntity>)

    @Query("Select * from movies")
    fun getAllmovies(): List<MovieEntity>
}



