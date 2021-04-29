package com.example.wevents.model


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wevents.R
import com.example.wevents.event
import com.example.wevents.network.firebaseConnect
import com.example.wevents.view.MainActivity


class RecyclerAdapter(var events: ArrayList<event>, var context : Context) :
    RecyclerView.Adapter<RecyclerAdapter.EventHolder>()
{
    val fbct = firebaseConnect()

    class EventHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var image = view.findViewById<ImageView>(R.id.Image_Thumb)
        var name = view.findViewById<AppCompatTextView>(R.id.Event_Name)
        var org = view.findViewById<AppCompatTextView>(R.id.Event_Org)
        var infoButton = view.findViewById<Button>(R.id.infoButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
        return EventHolder(v)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        var event = events.get(position)
        holder.name.text = event.name
        holder.org.text = event.org
        fbct.connectFileToImageView(event.imgSrc, holder.image)

        holder.infoButton.setOnClickListener(View.OnClickListener {
            (context as MainActivity).createItemView(event)
        })
    }

    override fun getItemCount(): Int {
        return events.size
    }
}

