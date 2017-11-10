package br.com.caelum.agenda.webservice

import br.com.caelum.agenda.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by matheusbrandino on 11/1/17.
 */
interface UserService {

    @POST("api/user/signin")
    fun signin(@Body user: User): Call<User>

    @POST("api/user/signup")
    fun signup(@Body user: User): Call<User>


}