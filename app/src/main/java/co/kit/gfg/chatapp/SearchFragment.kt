package co.kit.gfg.chatapp;

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import co.kit.gfg.chatapp.data.PairedDeviceData
import java.util.*


class SearchFragment : Fragment() {
    var REQUEST_ENABLE_BT = 1
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


            if(bluetoothAdapter.isEnabled) {
                //Making a connection to Server CLass

                var bluetoothConnection = BluetoothConnection()
                bluetoothConnection.ServerClass().start()
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
            PairedDeviceData.deviceName.clear()
            PairedDeviceData.deviceAddress.clear()
           PairedDeviceData.device.clear()

        /*Launching The Paired Device Fragment*/
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container_devices, pairedDevicesFragment)
        fragmentTransition.commit()

    }


    private fun available_devices_display() {

//        val requestCode = 1;
//        val discoverableIntent: Intent =
//            Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
//                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
//            }
//        Toast.makeText(context, "Discovering", Toast.LENGTH_SHORT).show()
//        startActivityForResult(discoverableIntent, requestCode)
//            val intentFilter=IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)
//            Log.d("Hello","Starting)
//            activity?.registerReceiver(scanModeReceiver, intentFilter)

        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container_devices, availableDevicesFragment)
        fragmentTransition.commit()
        Handler().postDelayed({
            AlertPopUp()
        }, 6000)

    }

    private fun AlertPopUp()
    {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("Cannot detect nearby bluetooth device , please connect it manually")
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id ->
                dialog.dismiss()

            })

        val alert = dialogBuilder.create()
        alert.setTitle("Scanning Failed")
        alert.show()

    }

}
