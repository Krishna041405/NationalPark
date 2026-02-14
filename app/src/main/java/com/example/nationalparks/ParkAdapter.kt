package com.example.nationalparks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ParkAdapter(private val parks: MutableList<Park>) :
    RecyclerView.Adapter<ParkAdapter.ParkViewHolder>() {

    class ParkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lab3Image: ImageView = itemView.findViewById(R.id.parkImage)
        val lab3Name: TextView = itemView.findViewById(R.id.parkName)
        val lab3Description: TextView = itemView.findViewById(R.id.parkDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lab3_item_park, parent, false)
        return ParkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = parks[position]

        holder.lab3Name.text = park.name
        holder.lab3Description.text = park.description

        Glide.with(holder.itemView.context)
            .load(park.imageUrl)
            .into(holder.lab3Image)
    }

    override fun getItemCount(): Int {
        return parks.size
    }

    fun updateData(newParks: List<Park>) {
        parks.clear()
        parks.addAll(newParks)
        notifyDataSetChanged()
    }
}
