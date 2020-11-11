package pt.atp.bobi

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change title based on user name
        val intent = intent
        findViewById<TextView>(R.id.textViewUser).text = ("Hello " + intent.getStringExtra("username")).toString()

        findViewById<Button>(R.id.buttonCamera).setOnClickListener{
            openNativeCamera()
        }

        findViewById<Button>(R.id.buttonAlertDialog).setOnClickListener{
            activateAlertDialog()
        }

        findViewById<Button>(R.id.buttonSnackbar).setOnClickListener{
            displaySnackbar()
        }

    }

    private fun openNativeCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private val positiveButtonClick = { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, getString(R.string.inner_alert_positive_button), Toast.LENGTH_SHORT).show() }
    private val negativeButtonClick = { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, getString(R.string.inner_alert_negative_button), Toast.LENGTH_SHORT).show() }
    private fun activateAlertDialog(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle(getString(R.string.activity_title))
        alert.setMessage(getString(R.string.alert_dialog_message))
        alert.setPositiveButton(getString(R.string.alert_dialog_positive_button), DialogInterface.OnClickListener(function = positiveButtonClick))
        alert.setNegativeButton(getString(R.string.alert_dialog_negative_button), DialogInterface.OnClickListener(function = negativeButtonClick))
        alert.show()
    }

    private fun displaySnackbar(){
        val parentLayout = findViewById<View>(android.R.id.content)
        val snack = Snackbar.make(parentLayout, getString(R.string.snackbar_text), Snackbar.LENGTH_SHORT)
        snack.show()
    }
}