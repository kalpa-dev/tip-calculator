package com.example.tipcalculator

import android.os.Bundle
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
            binding.tipAmount.text = getString(R.string.tip_amount_label, NumberFormat.getCurrencyInstance().format(calculateTip()))
        }

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
}