package com.otarbakh.andersen_task__2.ui.fragments


import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.otarbakh.andersen_task__2.common.Constants.idBundleKey
import com.otarbakh.andersen_task__2.common.Constants.nameBundleKey
import com.otarbakh.andersen_task__2.common.Constants.numberBundleKey
import com.otarbakh.andersen_task__2.common.Constants.surnameBundleKey
import com.otarbakh.andersen_task__2.ui.adapter.ContactsAdapter
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.common.BaseFragment
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
import com.otarbakh.andersen_task__2.databinding.ContactsFragmentBinding
import com.otarbakh.andersen_task__2.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsFragment : BaseFragment<ContactsFragmentBinding>(ContactsFragmentBinding::inflate) {

    private val contactsAdapter: ContactsAdapter by lazy { ContactsAdapter() }
    private val viewModel: MainViewModel by activityViewModels()

    override fun viewCreated() {
        getContacts()
    }


    override fun listeners() {
        gotoDetails()
        searchContactByQuery()
        delete()
    }

    private fun getContacts() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getContactsFromViewModel()
                viewModel.state.collectLatest {
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

            lifecycleScope.launch {
                viewModel.setPhoneNumber(contactsDetail)
            }


            val contactsDetailsFragment = ContactsDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(idBundleKey, contactsDetail.id)
            bundle.putString(nameBundleKey, contactsDetail.name)
            bundle.putString(surnameBundleKey, contactsDetail.surname)
            bundle.putString(numberBundleKey, contactsDetail.phoneNumber)

            contactsDetailsFragment.arguments = bundle


            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fullScreen_fragment, contactsDetailsFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }

    private fun searchContact(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    val filteredContacts = it.filter {
                        it.name!!.contains(query, true) or it.surname!!.contains(query, true)
                    }
                    contactsAdapter.submitList(filteredContacts)
                }
            }
        }
    }


    private fun searchContactByQuery() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchContact(newText)
                return true
            }
        })
    }

    private fun delete() {
        contactsAdapter.setOnDeleteClickListener { contactsDetail, i ->
            viewModel.delete(contactsDetail)
        }
    }

}

