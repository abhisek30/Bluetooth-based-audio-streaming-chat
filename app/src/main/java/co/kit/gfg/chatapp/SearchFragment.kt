package co.kit.gfg.chatapp;

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment


class SearchFragment : Fragment() {
    var REQUEST_ENABLE_BT = 1
    val available_device_name = ArrayList<String>()
    val available_device_address = ArrayList<String>()
    val pairedDevicesFragment = PairedDevicesFragment()
    val availableDevicesFragment = Availabledevicesfragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_search, container, false)

        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val bluetooth_animation =
            viewOfLayout.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.bluetooth_animation)
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "Device does not support Bluetooth", Toast.LENGTH_LONG).show()
        } else {

            val scanning_button: ConstraintLayout = viewOfLayout.findViewById(R.id.scanning_button)
            val paired_button: ConstraintLayout = viewOfLayout.findViewById(R.id.paired_button)

            if (!bluetoothAdapter.isEnabled) {

                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()

            }
            if (!bluetoothAdapter.isEnabled) {

                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()

            }
            if (!bluetoothAdapter.isEnabled) {

                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()

            }


            while (!bluetoothAdapter.isEnabled) {

            }




            scanning_button.setOnClickListener {
                Toast.makeText(context, "Scanning Devices", Toast.LENGTH_SHORT).show()
                available_devices_display()
            }



            paired_button.setOnClickListener {
                Toast.makeText(context, "Paired Devices", Toast.LENGTH_SHORT).show()
                paired_device_display()
            }
        }
        return viewOfLayout
    }


    private fun paired_device_display() {


        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container_devices, pairedDevicesFragment)
        fragmentTransition.commit()
    }


    private fun available_devices_display() {

        val requestCode = 1;
        val discoverableIntent: Intent =
            Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
            }
        Toast.makeText(context, "Discovering", Toast.LENGTH_SHORT).show()
        startActivityForResult(discoverableIntent, requestCode)
//            val intentFilter=IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)
//            Log.d("Hello","Starrting")
//            activity?.registerReceiver(scanModeReceiver, intentFilter)

        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container_devices, availableDevicesFragment)
        fragmentTransition.commit()

    }


}
