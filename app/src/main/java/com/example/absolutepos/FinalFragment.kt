package com.example.absolutepos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FinalFragment : Fragment() {

    private lateinit var userDataTextView: TextView
    private lateinit var showUserDataButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_final, container, false)
        userDataTextView = view.findViewById(R.id.userDataTextView)
        userDataTextView.visibility = View.GONE

        showUserDataButton = view.findViewById(R.id.showUserDataButton)

        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "No Name")
        val userPhone = sharedPreferences.getString("user_phone", "No Phone")
        val institutionName = sharedPreferences.getString("institution_name", "No Institution")
        val cityAndCountry = sharedPreferences.getString("city_and_country", "No City")
        val address = sharedPreferences.getString("address", "No Address")

        Log.d("FinalFragment", "UserName: $userName")
        Log.d("FinalFragment", "UserPhone: $userPhone")
        Log.d("FinalFragment", "InstitutionName: $institutionName")
        Log.d("FinalFragment", "CityAndCountry: $cityAndCountry")
        Log.d("FinalFragment", "Address: $address")

        showUserDataButton.setOnClickListener {
            val userData = """
        User Name: $userName
        User Phone: $userPhone
        Institution Name: $institutionName
        City and Country: $cityAndCountry
        Address: $address
       
    """.trimIndent()

            val selectedDataCheckType = sharedPreferences.getStringSet("selected_texts", emptySet())
            val selectedTexts = selectedDataCheckType?.joinToString("\n") ?: ""

            userDataTextView.text = "$userData\nВаш тип заведения:\n$selectedTexts"
            userDataTextView.visibility = View.VISIBLE
        }

        return view
    }
}