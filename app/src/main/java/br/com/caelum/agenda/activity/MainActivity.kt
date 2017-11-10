package br.com.caelum.agenda.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.agenda.R
import br.com.caelum.agenda.model.Event
import br.com.caelum.agenda.webservice.WebClient
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by matheusbrandino on 11/3/17.
 */
class MainActivity : AppCompatActivity() {


    val client = WebClient()
    var events = ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client.getEvents({t -> delegateToProblem(t) }, { list -> delegateToSucess(list) })



    }


    fun delegateToSucess(list : List<Event>) {

        events.addAll(list)

        main_list.adapter = ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, events)


    }

    fun delegateToProblem(t: Throwable) {

        Snackbar.make(main_list, t.message!!, Snackbar.LENGTH_LONG).show()
    }

}