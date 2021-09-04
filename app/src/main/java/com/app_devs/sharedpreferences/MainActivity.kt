package com.app_devs.sharedpreferences

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.app_devs.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var radioButton:RadioButton

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences=getSharedPreferences("MyPref",Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        binding.saveBtn.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString()
            val selectedGender = binding.radioGroup.checkedRadioButtonId
            radioButton = findViewById(selectedGender)
           // val gender: String = radioButton.text.toString()
            editor.apply {
                putString("name",name)
                putString("age",age)
                putInt("gender",selectedGender)
                apply()
            }

        }
        binding.loadBtn.setOnClickListener {
            binding.etName.setText(sharedPreferences.getString("name",null))
            binding.etAge.setText(sharedPreferences.getString("age",null))
            binding.radioGroup.check(sharedPreferences.getInt("gender",0))

        }
    }
}