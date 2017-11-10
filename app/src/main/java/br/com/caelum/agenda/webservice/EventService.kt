package br.com.caelum.agenda.webservice

import br.com.caelum.agenda.model.Event
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by matheusbrandino on 11/3/17.
 */
interface EventService {

    @GET("api/events")
    fun getEvents() : Call<List<Event>>
}