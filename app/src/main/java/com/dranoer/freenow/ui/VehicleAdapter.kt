package com.dranoer.freenow.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
            fleetName = current.fleetType.title,
            fleetIcon = current.fleetType.icon,
            id = current.id,
        )
        holder.itemView.setOnClickListener {
            onClickListener.invoke(current.id, current.latitude, current.longitude)
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idItemView: TextView = itemView.findViewById(R.id.vehicle_id)
        private val fleetNameItemView: TextView = itemView.findViewById(R.id.vehicle_title)
        private val fleetIconItemView: ImageView = itemView.findViewById(R.id.vehicle_icon)

        fun bind(id: Long, fleetName: String?, fleetIcon: Int) {
            fleetNameItemView.text = fleetName
            idItemView.text = itemView.context.getString(R.string.vehicle_id, id.toString())
            fleetIconItemView.load(fleetIcon) {
                crossfade(true)
                placeholder(R.drawable.loading)
            }
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