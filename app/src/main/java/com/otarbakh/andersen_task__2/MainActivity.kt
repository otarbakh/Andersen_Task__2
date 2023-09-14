package com.otarbakh.andersen_task__2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.otarbakh.andersen_task__2.databinding.ActivityMainBinding
import com.otarbakh.andersen_task__2.ui.fragments.ContactsDetailsFragment
import com.otarbakh.andersen_task__2.ui.fragments.ContactsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isLandscape) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.left_fragment, ContactsFragment())
                .replace(R.id.right_fragment, ContactsDetailsFragment())
                .commit()
            binding.fullScreenFragment.visibility = View.INVISIBLE
            binding.leftFragment.visibility = View.VISIBLE
            binding.rightFragment.visibility = View.VISIBLE
        } else {
            // Load only the contact list fragment in portrait mode
            supportFragmentManager.beginTransaction()
                .replace(R.id.fullScreen_fragment, ContactsFragment())
                .commit()
            binding.fullScreenFragment.visibility = View.VISIBLE
            binding.leftFragment.visibility = View.INVISIBLE
            binding.rightFragment.visibility = View.INVISIBLE
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fullScreen_fragment, ContactsFragment())
        transaction.commit()


    }
}