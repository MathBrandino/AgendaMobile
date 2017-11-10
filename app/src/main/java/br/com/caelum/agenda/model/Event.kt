package br.com.caelum.agenda.model


/**
 * Created by matheusbrandino on 11/3/17.
 */
class Event {

    var id: Long = 0
    lateinit var title: String
    lateinit var description: String
    lateinit var date: String
    lateinit var address: String
    lateinit var poster: String


    override fun toString(): String {
        return "Event(id=$id, title='$title', description='$description', date=$date, address='$address', poster='$poster')"
    }


}