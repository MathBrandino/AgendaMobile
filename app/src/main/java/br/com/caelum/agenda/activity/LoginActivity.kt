package br.com.caelum.agenda.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.caelum.agenda.R
import br.com.caelum.agenda.application.EventApplication
import br.com.caelum.agenda.model.User
import br.com.caelum.agenda.webservice.WebClient
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val client: WebClient = WebClient()

    lateinit var app: EventApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        app = application as EventApplication

        if (app.userLogged()) {
            toMain()
        }

        signin.setOnClickListener {
            val email = email.text.toString()

            if (isValid(email)) {
                val user = User()
                user.email = email
                client.signin(user, delegateToSucess = { user -> delegateToSucess(user) }, delegateToProblem = { t -> delegateToProblem(t) })
            }

        }

        signup.setOnClickListener {
            val email = email.text.toString()

            if (isValid(email)) {
                val user = User()
                user.email = email

                client.signup(user, { t -> delegateToProblem(t) }, { user -> delegateToSucess(user) })
            }
        }

    }

    private fun isValid(email: String): Boolean {
        if (email.trim().isEmpty() || !email.contains("@")) {
            til.error = "Necessário ter um email válido"

            return false
        }

        til.isErrorEnabled = false
        return true
    }


    fun toMain() {

        val mainIntent = Intent(this, MainActivity::class.java)

        startActivity(mainIntent)

        finish()
    }

    fun delegateToSucess(user: User) {
        app.login(user)

        toMain()

    }

    fun delegateToProblem(t: Throwable) {

        til.error = t.message
    }
}
