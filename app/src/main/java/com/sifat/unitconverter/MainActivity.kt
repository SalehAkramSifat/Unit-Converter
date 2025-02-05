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

}
