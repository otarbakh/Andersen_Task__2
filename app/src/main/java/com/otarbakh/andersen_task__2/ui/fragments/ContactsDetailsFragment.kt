package com.otarbakh.andersen_task__2.ui.fragments


import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otarbakh.andersen_task__2.databinding.ContactDetailsFragmentBinding
import com.otarbakh.andersen_task__2.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsDetailsFragment :
    BaseFragment<ContactDetailsFragmentBinding>(ContactDetailsFragmentBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels()
    override fun viewCreated() {



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getPhoneNumber().collectLatest {
                    binding.tvPhone.text = it.phoneNumber
                    binding.tvName.text = it.name
                    binding.tvSurname.text = it.surname
                }
            }
        }


//        val name = arguments?.getString(nameBundleKey)
//        val surname = arguments?.getString(surnameBundleKey)
//        val number = arguments?.getString(numberBundleKey)
//
//        binding.apply {
//
//            tvPhone.text = number
//            tvName.text = name
//            tvSurname.text = surname
//        }

    }


    private fun updateContact() {
//        val receiveID = arguments?.getInt("id")
//
//        binding.save.setOnClickListener {
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.updateContact(
//                        contact = ContactsDetail(
//                            receiveID!!,
//                            name = binding.editName.text.toString(),
//                            surname = binding.ediSurname.text.toString(),
//                            phoneNumber = binding.editNumber.text.toString()
//                        )
//                    )
//
//                }
//            }
//            val transaction = parentFragmentManager.beginTransaction()
//            transaction.replace(R.id.fullScreen_fragment, ContactsFragment())
//            transaction.commit()
//        }
    }


    override fun listeners() {
        updateContact()

    }
}