package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Availabledevicesfragment() : Fragment() {
    val available_device_name=ArrayList<String>()
    val available_device_address=ArrayList<String>()
    lateinit var deviceDataAdapter:DeviceDataAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewOfLayout=inflater!!.inflate(R.layout.fragment_availabledevicesfragment, container, false)
          val recycler_available_devices=viewOfLayout.findViewById<RecyclerView>(R.id.recyler_available_devices)
        val bluetoothAdapter=BluetoothAdapter.getDefaultAdapter()



//        if(bluetoothAdapter.isDiscovering)
//            bluetoothAdapter.cancelDiscovery()
//
//        bluetoothAdapter.startDiscovery()
//        val discoverDeviceIntent=IntentFilter(BluetoothDevice.ACTION_FOUND)
//        activity?.registerReceiver(broad,discoverDeviceIntent)
//
//        recycler_available_devices!!.layoutManager = LinearLayoutManager(context)
//        recycler_available_devices.adapter =DataclassAdapter(available_device_name, available_device_address)
//                Log.d("yyyy","${available_device_name.size}")

        bluetoothAdapter.startDiscovery()
        val intentFilter=IntentFilter(BluetoothDevice.ACTION_FOUND)
//        intentFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED)
       requireActivity().registerReceiver(myreceiver,intentFilter)

        Log.d("Hello","After myreceiver")
        deviceDataAdapter=DeviceDataAdapter(available_device_name,available_device_address)
        recycler_available_devices.layoutManager = LinearLayoutManager(requireContext())
        recycler_available_devices.adapter =deviceDataAdapter

        return viewOfLayout

    }



    private val myreceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            Log.d("Hello","Inside oN receive")

            val action = intent.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device =intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    if (device != null) {
                        available_device_name.add(device.name)
                        available_device_address.add(device.address)
                        Log.d("Hello","${device.name}")
                        deviceDataAdapter.notifyDataSetChanged()
                    }

                }
            }
        }
    }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//    private val broad = object : BroadcastReceiver() {
//
//        override fun onReceive(context: Context?, intent: Intent?) {
//            Log.d("Hello","e")
//            val action: String? = intent?.action
//            val available_device_address = ArrayList<String>()
//            val available_device_name = ArrayList<String>()
//            val recycler_available_devices =
//                activity?.findViewById<RecyclerView>(R.id.recyler_available_devices)
//
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                // Discovery has found a device. Get the BluetoothDevice
//                // object and its info from the Intent.
//                val device: BluetoothDevice? =
//                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//
//                if (device != null) {
//                    Log.d("Ava", device.name)
//                    available_device_name.add(device.name) //name of device
//                    available_device_address.add(device.address) // MAC address
//                    Log.d("Hello","${device.name}")
//                }
//
//
//            }
//
//
//        }
//    }
//
//
//
//    override fun onDestroy() {
//
//        // Don't forget to unregister the ACTION_FOUND receiver.
//        activity?.unregisterReceiver(broad)
//        super.onDestroy()
//    }


}