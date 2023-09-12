package com.otarbakh.andersen_task__2.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.andersen_task__2.ui.adapter.ContactsAdapter
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.data.ContactsDetail
import com.otarbakh.andersen_task__2.data.contactsList
import com.otarbakh.andersen_task__2.databinding.ContactsFragmentBinding
import com.otarbakh.andersen_task__2.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class ContactsFragment : BaseFragment<ContactsFragmentBinding>(ContactsFragmentBinding::inflate) {

    private val contactsAdapter: ContactsAdapter by lazy { ContactsAdapter() }
    private val viewModel: MainViewModel by viewModels()
    override fun viewCreated() {
        getContacts()

//        contactsAdapter.submitList(contactsList)
    }

    override fun listeners() {
        gotoDetails()
    }

    private fun getContacts(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getContacts().collectLatest {
                    contactsAdapter.submitList(it)
                }
            }
        }
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
            bundle.putInt("id",contactsDetail.id)
            bundle.putString("name", contactsDetail.name)
            bundle.putString("surname", contactsDetail.surname)
            bundle.putString("number", contactsDetail.phoneNumber)

            contactsDetailsFragment.arguments = bundle



            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, contactsDetailsFragment)
            transaction.addToBackStack(null) // Optional: Add to back stack for back navigation
            transaction.commit()

        }
    }

}

