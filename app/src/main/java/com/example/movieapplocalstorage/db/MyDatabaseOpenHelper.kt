package com.example.movieapplocalstorage.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.movieapplocalstorage.model.favorite.MovieFavorite
import com.example.movieapplocalstorage.model.favorite.TvFavorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(MovieFavorite.TABLE_FAVORITE, true,
            MovieFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MovieFavorite.ID_MOVIE to INTEGER + UNIQUE,
            MovieFavorite.OVERVIEW to TEXT,
            MovieFavorite.POSTER_PATH to TEXT,
            MovieFavorite.TITLE to TEXT)

        db.createTable(TvFavorite.TABLE_FAVORITE_TV, true,
            TvFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TvFavorite.ID_TV to INTEGER + UNIQUE,
            TvFavorite.OVERVIEW to TEXT,
            TvFavorite.POSTER_PATH to TEXT,
            TvFavorite.TITLE to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.dropTable(MovieFavorite.TABLE_FAVORITE, true)
    }
}


val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)