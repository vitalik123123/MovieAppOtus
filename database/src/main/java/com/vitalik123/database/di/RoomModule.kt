package com.vitalik123.database.di

import android.content.Context
import androidx.room.Room
import com.vitalik123.core.utils.Constants
import com.vitalik123.database.MovieRoomDatabase
import com.vitalik123.database.repository.ratelist.RatelistRoomRepository
import com.vitalik123.database.repository.ratelist.RatelistRoomRepositoryImpl
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun providesDatabase(context: Context): MovieRoomDatabase =
        Room.databaseBuilder(
            context,
            MovieRoomDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesWatchlistRepository(roomDatabase: MovieRoomDatabase): WatchlistRoomRepository =
        WatchlistRoomRepositoryImpl(roomDatabase.watchlistDao())

    @Provides
    fun providesRatelistRepository(roomDatabase: MovieRoomDatabase): RatelistRoomRepository =
        RatelistRoomRepositoryImpl(roomDatabase.ratelistDao())
}