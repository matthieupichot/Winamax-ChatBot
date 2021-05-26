package fr.test.winamax.di.components

import dagger.Component
import fr.test.winamax.MainActivity
import fr.test.winamax.di.modules.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])

interface RoomComponent {
    fun inject(mainActivity: MainActivity)
}