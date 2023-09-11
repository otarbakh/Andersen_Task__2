package com.otarbakh.andersen_task__2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.otarbakh.andersen_task__2.databinding.ActivityMainBinding
import com.otarbakh.andersen_task__2.fragments.ContactsDetailsFragment
import com.otarbakh.andersen_task__2.fragments.ContactsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, ContactsFragment())
        transaction.commit()


    }
}