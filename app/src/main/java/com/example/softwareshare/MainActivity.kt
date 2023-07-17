package com.example.softwareshare

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.EditText
import com.example.softwareshare.databinding.ActivityDialogBinding
import com.example.softwareshare.databinding.ActivityMainBinding
import com.example.softwareshare.question

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = ActivityDialogBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)

        binding.startButton.setOnClickListener {
            val name = binding.inputName.text.toString() // 수정된 부분
            dialog.show()
            dialogBinding.txtName.text = ("정말 ${name}님이 맞으신가요?")
            dialogBinding.yesBtn.setOnClickListener {
                Log.d("test2", "yes2")
                val intent = Intent(this@MainActivity, question::class.java)

                intent.putExtra("name", name)
                startActivity(intent)
            }
            dialogBinding.noBtn.setOnClickListener {
                Log.d("test2", "no2")
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
