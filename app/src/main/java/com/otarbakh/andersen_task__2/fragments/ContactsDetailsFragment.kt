package com.otarbakh.andersen_task__2.fragments

import android.util.Log
import androidx.fragment.app.Fragment
import com.otarbakh.andersen_task__2.ContactsDetail
import com.otarbakh.andersen_task__2.contactsList
import com.otarbakh.andersen_task__2.databinding.ContactDetailsFragmentBinding

class ContactsDetailsFragment :
    BaseFragment<ContactDetailsFragmentBinding>(ContactDetailsFragmentBinding::inflate) {
    override fun viewCreated() {

        val contactsList = arguments?.getSerializable("contactsList") as? Array<ContactsDetail>

        if (contactsList != null) {
            // Handle the data, e.g., display it in a TextView
            val contactDetails = contactsList.joinToString("\n") { "${it.name} ${it.surname}: ${it.phoneNumber}" }
            binding.tvPhone.text = contactDetails
        }
    }

    override fun listeners() {

    }
}