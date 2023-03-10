package br.edu.ufabc.listacontatosresponsiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ufabc.listacontatosresponsiva.databinding.FragmentContactItemBinding

class ContactItemFragment: Fragment() {
    companion object {
        const val contactIdKey = "contactId"
    }

    private lateinit var binding: FragmentContactItemBinding
    var contactId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactItemBinding.inflate(inflater, container, false)
        contactId = contactId.ifEmpty { savedInstanceState?.getString(contactIdKey) ?: "" }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as App).getContactById(contactId).let {contact ->
            binding.contactListItemFullname.text = contact.name
            binding.contactListItemPhoneValue.text = contact.phone
            binding.contactListItemEmailValue.text = contact.email
            binding.contactListItemAddressValue.text = contact.address
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(contactIdKey, contactId)
    }
}