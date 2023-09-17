package com.otarbakh.andersen_task__2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
import com.otarbakh.andersen_task__2.databinding.SingleContactFragmentBinding


class ContactsAdapter :
    ListAdapter<ContactsDetail, ContactsAdapter.ContactsViewHolder>(
        RidesDiffCallback()
    ) {

    private lateinit var itemClickListener: (ContactsDetail, Int) -> Unit
    private lateinit var itemClickForDeleteListener: (ContactsDetail, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): ContactsViewHolder {
        val binding =
            SingleContactFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bindData()
    }


    inner class ContactsViewHolder(private val binding: SingleContactFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: ContactsDetail? = null
        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvName.text = model?.name
                tvSurname.text = model?.surname!!


                binding.contactView.setOnClickListener {
                    itemClickListener.invoke(model!!, adapterPosition)
                }
                binding.contactView.setOnLongClickListener {
                    itemClickForDeleteListener.invoke(model!!, adapterPosition)
                    true
                }

            }
        }


    }

    fun setOnItemClickListener(clickListener: (ContactsDetail, Int) -> Unit) {
        itemClickListener = clickListener
    }

    fun setOnDeleteClickListener(clickListener: (ContactsDetail, Int) -> Unit) {
        itemClickForDeleteListener = clickListener
    }


    class RidesDiffCallback : DiffUtil.ItemCallback<ContactsDetail>() {
        override fun areItemsTheSame(
            oldItem: ContactsDetail,
            newItem: ContactsDetail,
        ): Boolean {
            return oldItem.phoneNumber == newItem.phoneNumber
        }

        override fun areContentsTheSame(
            oldItem: ContactsDetail,
            newItem: ContactsDetail,
        ): Boolean {
            return oldItem == newItem
        }
    }
}