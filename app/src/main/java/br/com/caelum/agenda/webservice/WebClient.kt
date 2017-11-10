package br.com.caelum.agenda.webservice

import android.util.Log
import br.com.caelum.agenda.model.Event
import br.com.caelum.agenda.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by matheusbrandino on 11/1/17.
 */
class WebClient {

    private val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(JacksonConverterFactory.create()).build()
    private val userService = retrofit.create(UserService::class.java)
    private val eventService = retrofit.create(EventService::class.java)

    fun signin(user: User, delegateToProblem: (t: Throwable) -> Unit, delegateToSucess: (User) -> Unit) {



        userService.signin(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                delegateToProblem(t!!)

            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {

                if (response!!.code() == 200) {

                    val userManaged: User = response!!.body()!!

                    delegateToSucess(userManaged)
                } else {

                    val throwable = Throwable(response.errorBody()!!.string())

                    delegateToProblem(throwable)

                }
            }


        })

    }

    fun signup(user: User, delegateToProblem: (t: Throwable) -> Unit, delegateToSucess: (User) -> Unit) {


        userService.signup(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                delegateToProblem(t!!)

            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {

                if (response!!.code() == 201) {

                    val userManaged: User = response!!.body()!!

                    delegateToSucess(userManaged)
                } else {

                    val throwable = Throwable(response.errorBody()!!.string())

                    delegateToProblem(throwable)

                }
            }


        })

    }


    fun getEvents(delegateToProblem: (t: Throwable) -> Unit, delegateToSucess: (List<Event>) -> Unit) {


        eventService.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {

                val events = response!!.body()
                delegateToSucess(events!!)


            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                delegateToProblem(t!!)
            }


        })

    }

}