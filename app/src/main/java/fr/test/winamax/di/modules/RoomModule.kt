package fr.test.winamax.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fr.test.winamax.database.AppDatabase
import fr.test.winamax.database.MessageDao
import javax.inject.Singleton

@Module
class RoomModule constructor(private val context: Context) {
    @Provides
    @Singleton
    fun providesDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): MessageDao {
        return appDatabase.messageDao()
    }

}