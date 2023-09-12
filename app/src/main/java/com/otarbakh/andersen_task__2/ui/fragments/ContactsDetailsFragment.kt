package com.otarbakh.andersen_task__2.ui.fragments


import com.otarbakh.andersen_task__2.databinding.ContactDetailsFragmentBinding

class ContactsDetailsFragment :
    BaseFragment<ContactDetailsFragmentBinding>(ContactDetailsFragmentBinding::inflate) {
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

    override fun listeners() {

    }
}