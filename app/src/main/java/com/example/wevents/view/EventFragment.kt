package com.example.wevents.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wevents.R
import com.example.wevents.event


class EventFragment(val event : event) : Fragment() {
    var viewTarget : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var inflate = inflater.inflate(com.example.wevents.R.layout.event_view_fragment, container, false)
        viewTarget = inflate

        inflate.findViewById<TextView>(R.id.name_view).text = event.name
        inflate.findViewById<TextView>(R.id.org_view).text = event.org
        inflate.findViewById<TextView>(R.id.description_view).text = event.description

        inflate.findViewById<Button>(R.id.exit_button).setOnClickListener(View.OnClickListener {
            (activity as MainActivity).removeItemView()
        })

        return inflate
    }

    fun setText(text: String?) {

    }

    fun destroy(view : View)
    {
        (activity as MainActivity).removeItemView()
    }
}
