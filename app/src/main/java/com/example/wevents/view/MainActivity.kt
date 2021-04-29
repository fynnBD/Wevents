package com.example.wevents.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wevents.R
import com.example.wevents.event
import com.example.wevents.model.RecyclerAdapter
import com.example.wevents.model.WeController

class MainActivity : AppCompatActivity() {

    var itemFrag : EventFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this).get(WeController::class.java)
        model.getArticles().observe(this) { articles -> updateRecycler(articles as ArrayList<event>)}
    }

    fun updateRecycler(events: ArrayList<event>)
    {
        var view = findViewById<RecyclerView>(R.id.eventRecycler)
        view.layoutManager = LinearLayoutManager(this)
        view.adapter = RecyclerAdapter(events, this)
    }

    fun createItemView(event : event)
    {
        var newitemFrag = EventFragment(event)
        itemFrag = newitemFrag

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frame, newitemFrag).commit()
    }

    fun removeItemView()
    {
        val currentFrag = itemFrag
        if(currentFrag != null)
        {

            val ft = supportFragmentManager.beginTransaction()
            ft.remove(currentFrag).commit()
        }
        itemFrag = null
    }
}