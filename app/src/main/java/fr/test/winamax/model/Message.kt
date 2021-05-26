package fr.test.winamax.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val pseudo : String,
    val message: String,
    val date: String,
    val isBot: Boolean
){
    constructor(pseudo: String, message: String, date: String, isBot : Boolean) : this(0, pseudo, message, date, isBot)
}