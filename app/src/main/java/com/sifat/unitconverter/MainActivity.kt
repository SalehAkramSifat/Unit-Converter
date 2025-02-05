package com.sifat.unitconverter

import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sifat.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = listOf(
            "Height: Feet ↔ Meter",
            "Length: Meter ↔ Kilometer",
            "Weight: Kilogram ↔ Pound",
            "Temperature: Celsius ↔ Fahrenheit"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = parent?.getItemAtPosition(position).toString()
                updateUIForSelectedUnit(selectedOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.btnSwap.setOnClickListener {
            val fromUnit = binding.tvUnitFrom.text.toString()
            val toUnit = binding.tvUnitTo.text.toString()

            binding.tvUnitFrom.text = toUnit
            binding.tvUnitTo.text = fromUnit

            val selectedUnit = "{$fromUnit} ↔ {$toUnit}"
            updateUIForSelectedUnit(selectedUnit)
        }
        binding.etInputValue.setOnFocusChangeListener{_, _->
            val inputValue = binding.etInputValue.text.toString().toDoubleOrNull()
            if (inputValue != null){
                val selectedUnit = binding.spinner.selectedItem.toString()
                val result = convert(inputValue, selectedUnit)
                binding.tvOutputValue.text = result.toString()
            }else{
                binding.tvOutputValue.text = "Invalid Input"
            }
        }
    }
    private fun updateUIForSelectedUnit(selectedUnit: String) {
        when (selectedUnit) {
            "Height: Feet ↔ Meter" -> {
                binding.tvUnitFrom.text = "Feet"
                binding.tvUnitTo.text = "Meter"
            }

            "Length: Meter ↔ Kilometer" -> {
                binding.tvUnitFrom.text = "Meter"
                binding.tvUnitTo.text = "Kilometer"
            }

            "Weight: Kilogram ↔ Pound" -> {
                binding.tvUnitFrom.text = "Kilogram"
                binding.tvUnitTo.text = "Pound"
            }

            "Temperature: Celsius ↔ Fahrenheit" -> {
                binding.tvUnitFrom.text = "Celsius"
                binding.tvUnitTo.text = "Fahrenheit"
            }
        }
    }

    private fun convert (inputValue: Double, selectedUnit: String):Double{
        return when(selectedUnit) {
            "Height: Feet ↔ Meter" -> feetToMeter(inputValue)
            "Length: Meter ↔ Kilometer" -> meterKilometer(inputValue)
            "Weight: Kilogram ↔ Pound" -> kilogramPound(inputValue)
            "Temperature: Celsius ↔ Fahrenheit" -> celsiusFahrenheit(inputValue)
            else -> 0.0
        }
    }
    private fun feetToMeter(feet:Double):Double = feet*0.3048
    private fun meterKilometer (meter:Double):Double = meter
    private fun kilogramPound (kilogram:Double):Double =
    private fun celsiusFahrenheit (celsius:Double):Double =
}
