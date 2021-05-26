package fr.test.winamax

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import fr.test.winamax.database.MessageDao
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val path = "file:///android_asset/index.html"

    @Inject
    lateinit var messageDao: MessageDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Application.roomComponent.inject(this)
        setUpWebView()
    }

    private fun setUpWebView(){
        WebView.setWebContentsDebuggingEnabled(true)
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(WebViewInterface(this, messageDao), "Interface")
        webView.loadUrl(path)
    }

    override fun onBackPressed() {
        val isConnected = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("Login", false)
        if(isConnected) {
            webView.loadUrl(path)
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("Login", false).apply()
        } else {
            super.onBackPressed();
        }
    }

    override fun onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("Login", false).apply()
        super.onDestroy()
    }

}


