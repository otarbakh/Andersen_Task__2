package com.otarbakh.andersen_task__2.fragments


import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.andersen_task__2.ContactsAdapter
import com.otarbakh.andersen_task__2.ContactsDetail
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.contactsList
import com.otarbakh.andersen_task__2.databinding.ContactsFragmentBinding

class ContactsFragment : BaseFragment<ContactsFragmentBinding>(ContactsFragmentBinding::inflate) {

    private val contactsAdapter: ContactsAdapter by lazy { ContactsAdapter() }

    override fun viewCreated() {
        setupRecycler()
        contactsAdapter.submitList(contactsList)
    }

    override fun listeners() {
        gotoDetails()
    }

    private fun setupRecycler() {
        binding.rvContacts.apply {
            adapter = contactsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun gotoDetails() {
        contactsAdapter.setOnItemClickListener { contactsDetail, i ->
            Log.d("kk", contactsDetail.phoneNumber)

            val contactsDetailsFragment = ContactsDetailsFragment()
            val bundle = Bundle()

            bundle.putSerializable("contactsList", contactsList.toTypedArray())

            contactsDetailsFragment.arguments = bundle



            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, contactsDetailsFragment)
            transaction.addToBackStack(null) // Optional: Add to back stack for back navigation
            transaction.commit()

        }
    }

}

