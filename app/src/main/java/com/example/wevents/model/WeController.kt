package com.example.wevents.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wevents.event
import com.example.wevents.network.firebaseConnect

class WeController : ViewModel() {
    var events : MutableLiveData<ArrayList<event>> = MutableLiveData<ArrayList<event>>()
    var api = firebaseConnect()

    fun getArticles() : LiveData<List<event>>
    {
        if (events == null)
        {
            events = MutableLiveData<ArrayList<event>>()
        }

        api.getEvents(this::updateEvents)
        return events as MutableLiveData<List<event>>
    }

    private fun updateEvents(eventList : ArrayList<event>) {
        this.events.value = eventList
    }

}