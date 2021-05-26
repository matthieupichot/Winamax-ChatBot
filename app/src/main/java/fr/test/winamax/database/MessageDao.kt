package fr.test.winamax.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.test.winamax.model.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Query("SELECT * FROM message WHERE pseudo = :pseudo ")
    fun getMessages(pseudo : String) : List<Message>

    @Query("DELETE FROM message WHERE pseudo = :pseudo")
    fun deleteMessages(pseudo : String)

}