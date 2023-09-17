package com.otarbakh.andersen_task__2.ui.fragments


import android.content.res.Configuration
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.otarbakh.andersen_task__2.R
import com.otarbakh.andersen_task__2.common.BaseFragment
import com.otarbakh.andersen_task__2.common.ColorUtils
import com.otarbakh.andersen_task__2.common.Constants.nameBundleKey
import com.otarbakh.andersen_task__2.common.Constants.numberBundleKey
import com.otarbakh.andersen_task__2.common.Constants.surnameBundleKey
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
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

        getContactDetailsOnLandscape()

        val name = arguments?.getString(nameBundleKey)
        val surname = arguments?.getString(surnameBundleKey)
        val number = arguments?.getString(numberBundleKey)

        binding.apply {
            tvPhone.text = number
            tvName.text = name
            tvSurname.text = surname
        }

    }

    private fun getContactDetailsOnLandscape() {
        if (context != null) {
            val isLandscape =
                resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

            if (isLandscape) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.getPhoneNumber().collectLatest { data ->
                            binding.tvPhone.text = data.phoneNumber
                            binding.tvName.text = data.name
                            binding.tvSurname.text = data.surname
                            binding.save.setOnClickListener {
                                Log.d("modiak", "Shemovida Save shii")
                                viewModel.updateContact(
                                    ContactsDetail(
                                        data.id,
                                        binding.editName.text.toString(),
                                        binding.ediSurname.text.toString(),
                                        binding.tvPhone.text.toString(),
                                        letterInCircle = binding.editName.text.toString().first().uppercase()
                                    )
                                )
                            }
                        }
                    }
                }


            } else {
                updateContact()
            }
        }
    }


    private fun updateContact() {
        val receiveID = arguments?.getInt("id")

        binding.save.setOnClickListener() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.updateContact(
                        contact = ContactsDetail(
                            receiveID!!,
                            name = binding.editName.text.toString(),
                            surname = binding.ediSurname.text.toString(),
                            phoneNumber = binding.editNumber.text.toString(),
                            letterInCircle = binding.editName.text.toString().first().uppercase().toString(),
                            image = ColorUtils.getRandomColor()
                        )
                    )
                }
            }


            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fullScreen_fragment, ContactsFragment())
            transaction.commit()

        }
    }


    override fun listeners() {

    }
}