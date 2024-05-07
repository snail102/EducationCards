package ru.anydevprojects.educationcards.database.di

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module
import ru.anydevprojects.educationcards.database.AppDatabase
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.CardStatisticsDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.database.dao.DeckStatisticsDao

fun provideDataBase(application: Application): AppDatabase = Room.databaseBuilder(
    application,
    AppDatabase::class.java,
    "AppDatabase"
).fallbackToDestructiveMigration().build()

fun provideDeckDao(appDatabase: AppDatabase): DeckDao = appDatabase.deckDao()

fun provideCardDao(appDatabase: AppDatabase): CardDao = appDatabase.cardDao()

fun provideDeckStatisticsDao(appDatabase: AppDatabase): DeckStatisticsDao =
    appDatabase.deckStatisticsDao()

fun provideCardStatisticsDao(appDatabase: AppDatabase): CardStatisticsDao =
    appDatabase.cardStatisticsDao()

val databaseModule = module {
    single { provideDataBase(get()) }
    single { provideDeckDao(get()) }
    single { provideCardDao(get()) }
    single { provideCardStatisticsDao(get()) }
    single { provideDeckStatisticsDao(get()) }
}
