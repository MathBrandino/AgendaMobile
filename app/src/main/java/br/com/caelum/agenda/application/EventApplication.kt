package br.com.caelum.agenda.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.caelum.agenda.model.User

/**
 * Created by matheusbrandino on 11/3/17.
 */
class EventApplication : Application() {


    private lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences("EventsTech", Context.MODE_PRIVATE)

    }

    fun userLogged(): Boolean {

        val email = preferences.getString("email", null)

        return email != null

    }

    fun logout(){

        preferences.edit().remove("email").commit()
    }

    fun login(user : User){
        preferences.edit().putString("email", user.email).commit()
    }
}