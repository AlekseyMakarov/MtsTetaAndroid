package com.example.mtstetaandroid

import android.app.Application
import androidx.room.Room
import com.example.mtstetaandroid.data.AppDatabase


//public class App extends Application {
//
//    public static App instance;
//
//    private AppDatabase database;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        instance = this;
//        database = Room.databaseBuilder(this, AppDatabase.class, "database")
//            .build();
//    }
//
//    public static App getInstance() {
//        return instance;
//    }
//
//    public AppDatabase getDatabase() {
//        return database;
//    }
//}

class MyApp : Application() {
    var database: AppDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        private var instance: MyApp? = null

        fun getInstance(): MyApp? {
            return instance
        }
    }
}