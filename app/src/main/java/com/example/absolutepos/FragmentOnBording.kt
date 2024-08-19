package com.example.absolutepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class FragmentOnBoarding : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: OnboardingPagerAdapter
    private lateinit var nextButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var createFirstFragment: createfirstistu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_bording, container, false)
        viewPager = view.findViewById(R.id.view_pager)
        progressBar = view.findViewById(R.id.progress_bar)
        nextButton = requireActivity().findViewById(R.id.next_btn)
        backButton = requireActivity().findViewById(R.id.back_btn)

        createFirstFragment = createfirstistu()

        backButton.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }

        val fragments = arrayOf(
            LoginFragment(),
            createFirstFragment,
            TypeFragment(),
            sizeinsFragment(),
            hereordeliveryFragment(),
            FinalFragment()
        )

        adapter = OnboardingPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                progressBar.progress = (position + 1) * 100 / fragments.size
            }
        })

        nextButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            val currentFragment = fragments[currentItem]

            when (currentFragment) {
                is LoginFragment -> currentFragment.saveLoginData()
                is createfirstistu -> {
                    currentFragment.saveCreateData()
                }

                is TypeFragment -> {
                    val typeFragment = currentFragment as TypeFragment
                    typeFragment.saveSelectedData()
                }
            }

            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }

        return view
    }
}