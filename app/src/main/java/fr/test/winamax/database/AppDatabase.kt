package fr.test.winamax.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.test.winamax.model.Message

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao() : MessageDao
}