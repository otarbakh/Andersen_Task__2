package com.otarbakh.andersen_task__2.ui.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otarbakh.andersen_task__2.data.ContactsDetail
import com.otarbakh.andersen_task__2.databinding.ContactDetailsFragmentBinding
import com.otarbakh.andersen_task__2.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsDetailsFragment :
    BaseFragment<ContactDetailsFragmentBinding>(ContactDetailsFragmentBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    override fun viewCreated() {


        val name = arguments?.getString("name")
        val surname = arguments?.getString("surname")
        val number = arguments?.getString("number")

        binding.apply {
            tvPhone.text = number
            tvName.text = name
            tvSurname.text = surname
        }




//        if (contactsList != null) {
//            // Handle the data, e.g., display it in a TextView
//            val contactDetails = contactsList.joinToString("\n") { "${it.name} ${it.surname}: ${it.phoneNumber}" }
//            binding.tvPhone.text = contactDetails
//        }
    }


    private fun updateContact(){
        val receiveID = arguments?.getInt("id")

        binding.save.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.updateContact(contact = ContactsDetail(
                        receiveID!!,
                        name =  binding.editName.text.toString(),
                        surname = binding.ediSurname.text.toString(),
                        phoneNumber = binding.editNumber.text.toString()
                    ) )
                }
            }
        }


    }



    override fun listeners() {
        updateContact()
    }
}