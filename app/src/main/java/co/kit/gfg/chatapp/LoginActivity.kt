package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var handler: DatabaseHelper
    var bluetoothAdapter: BluetoothAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        handler= DatabaseHelper(this)

        //Switching to Registration


        btnRegLogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }


        //Login click
        log_button.setOnClickListener{
            if(handler.userPresent(log_bluetooth_name.text.toString(),log_username.text.toString(),log_password.text.toString()))
            {
                //start the activity
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            else//Wrong password
            {
                Toast.makeText(this,"Invalid inputs", Toast.LENGTH_LONG).show()
            }
        }

    }
}




