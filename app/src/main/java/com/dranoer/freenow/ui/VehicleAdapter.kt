package com.dranoer.freenow.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dranoer.freenow.R
import com.dranoer.freenow.data.model.VehicleEntity

class VehicleAdapter constructor(
    private val onClickListener: (id: Long, latitude: Double, longitude: Double) -> Unit
) : ListAdapter<VehicleEntity, VehicleAdapter.MainViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(
            fleetType = current.fleetType,
            id = current.id,
        )
        holder.itemView.setOnClickListener {
            onClickListener.invoke(current.id, current.latitude, current.longitude)
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fleetTypeItemView: TextView = itemView.findViewById(R.id.vehicle_title)
        private val idItemView: TextView = itemView.findViewById(R.id.vehicle_id)

        fun bind(fleetType: String?, id: Long) {
            fleetTypeItemView.text = itemView.context.getString(R.string.fleet_type, fleetType)
            idItemView.text = itemView.context.getString(R.string.vehicle_id, id.toString())
        }

        companion object {
            fun create(parent: ViewGroup): MainViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vehicle_item, parent, false)
                return MainViewHolder(view)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<VehicleEntity>() {
            override fun areItemsTheSame(oldItem: VehicleEntity, newItem: VehicleEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: VehicleEntity,
                newItem: VehicleEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}