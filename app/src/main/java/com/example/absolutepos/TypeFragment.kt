package com.example.absolutepos;

import android.content.Context
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
class TypeFragment : Fragment() {
    private lateinit var checkBoxes: MutableList<CheckBox>
    private lateinit var selectedData: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_type, container, false)

        val checkBoxRest = view.findViewById<CheckBox>(R.id.checkBoxRest)
        val checkBoxBar = view.findViewById<CheckBox>(R.id.checkBoxBar)
        val checkBoxCafe = view.findViewById<CheckBox>(R.id.checkBoxCafe)
        val checkBoxDining = view.findViewById<CheckBox>(R.id.checkBoxDining)
        val checkBoxCoffeeShop = view.findViewById<CheckBox>(R.id.checkBoxcoffeshop)
        val checkBoxCooking = view.findViewById<CheckBox>(R.id.checkBoxcooking)
        val checkBoxOther = view.findViewById<CheckBox>(R.id.checkboxOther)

        checkBoxes = mutableListOf(
            checkBoxRest,
            checkBoxBar,
            checkBoxCafe,
            checkBoxDining,
            checkBoxCoffeeShop,
            checkBoxCooking,
            checkBoxOther,
        )
        selectedData = mutableListOf()

        return view
    }

    fun saveSelectedData() {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val selectedData = getSelectedData()
        editor.putStringSet("selected_texts", selectedData.toSet())
        editor.apply()
    }

    fun getSelectedData(): List<String> {
        selectedData.clear()
        checkBoxes.forEach { checkBox ->
            if (checkBox.isChecked) {
                selectedData.add(getTextFromCheckBox(checkBox))
            }
        }
        return selectedData
    }

    private fun getTextFromCheckBox(checkBox: CheckBox): String {
        val textViewId = when (checkBox.id) {
            R.id.checkBoxRest -> R.id.textrest
            R.id.checkBoxBar -> R.id.textbar
            R.id.checkBoxCafe -> R.id.textcafe
            R.id.checkBoxDining -> R.id.textdining
            R.id.checkBoxcoffeshop -> R.id.textcoffeshop
            R.id.checkBoxcooking -> R.id.textcooking
            R.id.checkboxOther -> R.id.textother
            else -> throw RuntimeException("Unknown checkbox ID")
        }
        val textView = view?.findViewById<TextView>(textViewId)
        return textView?.text.toString()
    }
}