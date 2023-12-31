package com.otarbakh.andersen_task__2.ui.fragments


import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.andersen_task__2.common.Constants.idBundleKey
import com.otarbakh.andersen_task__2.common.Constants.nameBundleKey
import com.otarbakh.andersen_task__2.common.Constants.numberBundleKey
import com.otarbakh.andersen_task__2.common.Constants.surnameBundleKey
import com.otarbakh.andersen_task__2.ui.adapter.ContactsAdapter
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.common.BaseFragment
import com.otarbakh.andersen_task__2.common.ColorUtils
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
        deleteAll()
        insertAll()
    }

    private fun deleteAll(){
        binding.deleteall.setOnClickListener {
            viewModel.deleteall()
        }
    }

    private fun insertAll(){
        binding.deleteall.setOnLongClickListener {
            for (i in 1..100) {
                val randomColor = ColorUtils.getRandomColor()
                val name = "Contact$i"
                val firstLetter = name.substring(0, 1)

                val contact = ContactsDetail(
                    id = i,
                    name = name,
                    surname = "Surname",
                    phoneNumber = "123-456-7890",
                    image = randomColor,
                    letterInCircle = firstLetter
                )
                viewModel.insert(contact)
            }
            true
        }
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
        val divider = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            divider.setDrawable(it)
        }
        binding.rvContacts.addItemDecoration(divider)
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

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(getString(R.string.do_you_really_want_to_delete_contact))
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.delete(contactsDetail)
                        Toast.makeText(requireContext(), R.string.deleted, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.you_kept_your_contact),
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()

        }
    }

}

