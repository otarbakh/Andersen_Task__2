package com.otarbakh.andersen_task__2.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.Pref
import com.otarbakh.andersen_task__2.common.Constants.idBundleKey
import com.otarbakh.andersen_task__2.common.Constants.nameBundleKey
import com.otarbakh.andersen_task__2.common.Constants.numberBundleKey
import com.otarbakh.andersen_task__2.common.Constants.surnameBundleKey
import com.otarbakh.andersen_task__2.ui.adapter.ContactsAdapter
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
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

    }

    override fun listeners() {
        gotoDetails()
    }

    private fun getContacts() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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

            Pref.savePhoneNumber(requireContext(),contactsDetail.phoneNumber)

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

}

