package pt.atp.bobi

import android.app.AppComponentFactory
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //--------- Setup - Active components------------

        // Authenticate Button
        findViewById<Button>(R.id.btn_auth).setOnClickListener{

            val check = areCredentialsValid()
            if(check != "false"){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", check)
                startActivity(intent)
                finish()
            }
        }

        // Save Credentials Check Box
        findViewById<CheckBox>(R.id.checkBox_save_credentials).setOnClickListener{
                TODO()
        }

        //--------- End Setup  ------------
    }

    private fun areCredentialsValid(): String{
        val username = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        if(username.isEmpty()){
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
        if(password.isEmpty()){
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }

        if (username == password){
            return username
        }
        else{
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }

    }
}