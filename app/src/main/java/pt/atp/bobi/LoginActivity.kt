package pt.atp.bobi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //--------- Setup - Active components------------

        // "Done" on keyboard search
        findViewById<EditText>(R.id.editTextPassword).setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val check = areCredentialsValid()
                if(check != "false"){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username", check)
                    startActivity(intent)
                    finish()
                }
                return@OnEditorActionListener true
            }
            false
        })

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
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        if(username.isEmpty()){
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_username)
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        if(password.isEmpty()){
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_password)
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }

        if (username == password){
            return username
        }
        else{
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_mismatch)
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return "false"
        }

    }
}