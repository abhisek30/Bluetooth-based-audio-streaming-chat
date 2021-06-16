package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class DeviceDataAdapter(
    val name: ArrayList<String>,
    val address: ArrayList<String>
): RecyclerView.Adapter<DeviceDataAdapter.dataViewholder>() {

    class dataViewholder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):dataViewholder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.device_info,parent,false)
        return dataViewholder(itemView)
    }

    override fun onBindViewHolder(holder: dataViewholder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.device_name).text=name[position]
        holder.itemView.findViewById<TextView>(R.id.device_address).text=address[position]

    }

    override fun getItemCount(): Int {
        return name.size
    }

}