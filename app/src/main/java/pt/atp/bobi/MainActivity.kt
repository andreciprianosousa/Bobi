package pt.atp.bobi

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

    private val positiveButtonClick = { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, "Nice!", Toast.LENGTH_SHORT).show() }
    private val negativeButtonClick = { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, "Damn!", Toast.LENGTH_SHORT).show() }
    private fun activateAlertDialog(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Bobi")
        alert.setMessage("Teste")
        alert.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
        alert.setNegativeButton("Cancel", DialogInterface.OnClickListener(function = negativeButtonClick))
        alert.show()
    }

    private fun displaySnackbar(){
        val parentLayout = findViewById<View>(android.R.id.content)
        val snack = Snackbar.make(parentLayout,"This is a simple Snackbar", Snackbar.LENGTH_SHORT)
        snack.show()
    }
}