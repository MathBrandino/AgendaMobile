package br.com.caelum.agenda.model

/**
 * Created by matheusbrandino on 11/1/17.
 */
class User {

    var id : Long = 0
    lateinit var email:String


    override fun toString(): String {
        return "User(id=$id, email='$email')"
    }


}