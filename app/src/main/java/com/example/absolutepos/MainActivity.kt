package com.example.absolutepos

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var nextButton: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        dotsIndicator = findViewById(R.id.dots_indicator)
        nextButton = findViewById(R.id.next_btn)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val fragment = FragmentOnBoarding()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        nextButton.setOnClickListener {
            if (viewPager.currentItem < (viewPager.adapter?.itemCount ?: 0) - 1) {
                viewPager.currentItem += 1
            } else {
                val typeFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as TypeFragment
                val selectedData = typeFragment.getSelectedData()
                saveDataToSharedPreferences(selectedData)

                // нав.фраг
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, FinalFragment())
                fragmentTransaction.commit()
            }
        }
    }

    private fun saveDataToSharedPreferences(selectedData: List<String>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet("selected_data", selectedData.toSet())
        editor.apply()
    }
}