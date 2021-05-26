package fr.test.winamax

import android.app.Application
import com.facebook.stetho.Stetho
import fr.test.winamax.di.components.DaggerRoomComponent
import fr.test.winamax.di.components.RoomComponent
import fr.test.winamax.di.modules.RoomModule

class Application : Application() {

    companion object {
        lateinit var roomComponent: RoomComponent
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        roomComponent = DaggerRoomComponent.builder()
            .roomModule(RoomModule(this))
            .build()
    }

}