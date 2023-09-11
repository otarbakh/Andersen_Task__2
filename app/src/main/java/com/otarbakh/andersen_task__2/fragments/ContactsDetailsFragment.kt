package com.otarbakh.andersen_task__2.fragments

import android.util.Log
import androidx.fragment.app.Fragment
import com.otarbakh.andersen_task__2.ContactsDetail
import com.otarbakh.andersen_task__2.databinding.ContactDetailsFragmentBinding

class ContactsDetailsFragment :
    BaseFragment<ContactDetailsFragmentBinding>(ContactDetailsFragmentBinding::inflate) {
    override fun viewCreated() {

        val contactsList = arguments?.getSerializable("contactsList") as Array<ContactsDetail>

        Log.d("jeimsi", contactsList.toString())


        if (contactsList != null) {
            // Do something with the data, e.g., display it in TextViews
            binding.tvPhone.text =
                contactsList.toString()
        }
    }

    override fun listeners() {

    }
}