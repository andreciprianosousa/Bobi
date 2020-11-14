package pt.atp.bobi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels() // Using activity-ktx (in gradle dependencies) to quickly create a viewModel. There are other ways to do this, this is the quickest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()

    }

    private fun setup() {
        findViewById<EditText>(R.id.editTextPassword).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<EditText>(R.id.editTextPassword).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_auth).setOnClickListener {
            validateCredentialsAndRedirect()
        }

        viewModel.loginResultLiveData.observe(this){ loginResult ->
            if (!loginResult) {
                findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_mismatch)
                findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            }else{
                val username = findViewById<EditText>(R.id.editTextUsername).text.toString()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", username)

                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateCredentialsAndRedirect() {

        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_username)
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return
        }

        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_password)
            findViewById<TextView>(R.id.tv_error).visibility = View.VISIBLE
            return
        }

        viewModel.areCredentialsValid(username, password)

    }
}