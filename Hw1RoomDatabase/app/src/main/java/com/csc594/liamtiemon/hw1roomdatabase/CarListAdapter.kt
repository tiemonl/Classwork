package com.csc594.liamtiemon.hw1roomdatabase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CarListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cars = emptyList<Car>()

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val current = cars[position]
        val carString = current.car + " " + current.make
        holder.carItemView.text = carString
    }

    internal fun setCars(cars: List<Car>) {
        this.cars = cars
        notifyDataSetChanged()
    }

    override fun getItemCount() = cars.size
}