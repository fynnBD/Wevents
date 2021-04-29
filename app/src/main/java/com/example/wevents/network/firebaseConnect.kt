package com.example.wevents.network

import android.net.Uri
import android.widget.ImageView
import com.example.wevents.event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlin.reflect.KFunction1

class firebaseConnect {
    var fbdb = FirebaseDatabase.getInstance()
    var storageRef = FirebaseStorage.getInstance().getReference()

    fun getEvents(callBackFunction: KFunction1<ArrayList<event>, Unit>)
    {
        var ref = fbdb.reference.child("events")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var p = snapshot.getValue() as HashMap<String, HashMap<String, String>>
                var g = ArrayList<event>()

                for(i in p.keys)
                {
                    var eventMap = p.get(i)
                    if(eventMap != null) {
                        g.add(event(eventMap["imgSrc"].toString(), eventMap["name"].toString(), eventMap["org"].toString(), eventMap["description"].toString()))
                    }
                }

                callBackFunction(g)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun connectFileToImageView(fileEnd : String, view : ImageView) {
        println(fileEnd)
        var ref = storageRef.child("images/${fileEnd}").downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(view)
        }
    }
}