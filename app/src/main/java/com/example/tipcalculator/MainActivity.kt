package com.example.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity: AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipAmount.text = getString(R.string.tip_amount_label, NumberFormat.getCurrencyInstance().format(0))

        binding.calcBtn.setOnClickListener {
            if(!binding.cost.text.isNullOrEmpty()) {
                binding.tipAmount.text = getString(
                    R.string.tip_amount_label,
                    NumberFormat.getCurrencyInstance().format(calculateTip())
                )
            }
        }

        // hide keyboard on enter
        binding.cost.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view,keyCode)}

    }
    private fun calculateTip():Double{
        val cost = binding.cost.text.toString().toDouble()

        val tipPercentage =  when (binding.tipOptions.checkedRadioButtonId){
            R.id.option_1 -> 20
            R.id.option_2 -> 18
            else -> 15
        }

        val tip = cost * tipPercentage /100

        return if (binding.roundupSwitch.isChecked){
            ceil(tip)
        }else{
            tip
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int):Boolean{
        if (keyCode == KeyEvent.KEYCODE_ENTER){
            // hide keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as  InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return false
    }
}