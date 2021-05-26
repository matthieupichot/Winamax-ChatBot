package fr.test.winamax

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.preference.PreferenceManager
import fr.test.winamax.database.MessageDao
import fr.test.winamax.model.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WebViewInterface(var context: Context, private val messageDao: MessageDao) {

    var username : String = "";
    var botname : String = Build.MODEL;

    @JavascriptInterface
    fun setUserName(userName : String) {
        username = userName
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("Login", true).apply()
    }

    @JavascriptInterface
    fun getUserName() : String {
        return username
    }

    @JavascriptInterface
    fun getBotName() : String {
        return botname
    }

    @JavascriptInterface
    fun getConversation() : String {
        var messageList : List<Message> = emptyList()
        val thread = Thread()
        thread.apply {  messageList = messageDao.getMessages(username).take(50).sortedBy { it.date } }.start()
        thread.join()
        return Json.encodeToString(messageList)
    }

    @JavascriptInterface
    fun saveMessage(message : String, date : String, isBot : Boolean) {
        Thread {
             messageDao.insertMessage(Message(username, message, date, isBot))
        }.start()
    }

    @JavascriptInterface
    fun deleteMessages() {
        Thread {
            messageDao.deleteMessages(username)
        }.start()
    }

    @JavascriptInterface
    fun getBotResponse(message : String) : String {
        val words = message.split(" ").toTypedArray()
        val shuffledWords = words.toMutableList().shuffled()
        var response = ""
        for(word in shuffledWords){
            response += "$word "
        }
        return response
    }

    @JavascriptInterface
    fun disconnect(userName : String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("Login", false).apply()
    }

    @JavascriptInterface
    fun showToast(){
        val toast = Toast.makeText(context, "Veuillez entrer votre pseudo", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show()
    }

}