package com.sifat.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sifat.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var pagerAdapter: ViewPagerAdapter

    private val options = listOf(
        "Height: Feet ↔ Meter",
        "Length: Meter ↔ Kilometer",
        "Weight: Kilogram ↔ Pound",
        "Temperature: Celsius ↔ Fahrenheit"
    )

    private val history = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        binding.toolbar.setNavigationItemSelectedListener {

        }

        // ViewPager2 setup
        viewPager = binding.viewpager
        pagerAdapter = ViewPagerAdapter()
        viewPager.adapter = pagerAdapter

        // Spinner setup
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = parent?.getItemAtPosition(position).toString()
                updateUIForSelectedUnit(selectedOption)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.btnSwap.setOnClickListener {
            val fromUnit = binding.tvUnitFrom.text.toString()
            val toUnit = binding.tvUnitTo.text.toString()

            binding.tvUnitFrom.text = toUnit
            binding.tvUnitTo.text = fromUnit

            val newSelectedUnit = options.find { it.contains("$toUnit ↔ $fromUnit") }
            if (newSelectedUnit!= null){
                val position = options.indexOf(newSelectedUnit)
                binding.spinner.setSelection(position)
            }
            performConversion()
        }

        binding.etInputValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                performConversion()
            }
        })
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

    private fun performConversion() {
        val inputValue = binding.etInputValue.text.toString().toDoubleOrNull()
        if (inputValue != null) {
            val selectedUnit = binding.spinner.selectedItem.toString()
            val result = convert(inputValue, selectedUnit)
            binding.tvOutputValue.text = result.toString()

            val fromUnit = binding.tvUnitFrom.text.toString()
            val toUnit = binding.tvUnitTo.text.toString()
            history.add("$inputValue $fromUnit = $result $toUnit")
        } else {
            binding.tvOutputValue.text = "Invalid Input"
            if (binding.etInputValue.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convert(inputValue: Double, selectedUnit: String): Double {
        return when (selectedUnit) {
            "Height: Feet ↔ Meter" -> feetToMeter(inputValue)
            "Length: Meter ↔ Kilometer" -> meterKilometer(inputValue)
            "Weight: Kilogram ↔ Pound" -> kilogramPound(inputValue)
            "Temperature: Celsius ↔ Fahrenheit" -> celsiusFahrenheit(inputValue)
            else -> 0.0
        }
    }

    private fun feetToMeter(feet: Double): Double = feet * 0.3048
    private fun meterKilometer(meter: Double): Double = meter / 1000
    private fun kilogramPound(kilogram: Double): Double = kilogram * 2.20462
    private fun celsiusFahrenheit(celsius: Double): Double = (celsius * 9 / 5) + 32

    private fun onNaigationItemSelected(item: MenuItem){

    }
}