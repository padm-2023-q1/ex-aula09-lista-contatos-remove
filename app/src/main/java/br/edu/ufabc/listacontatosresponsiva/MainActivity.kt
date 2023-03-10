package br.edu.ufabc.listacontatosresponsiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import br.edu.ufabc.listacontatosresponsiva.databinding.ActivityMainBinding
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.google.android.material.snackbar.Snackbar
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<Contact>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindEvents()
        contacts.addAll((application as App).getAllContacts())
    }

    private fun bindEvents() {
        supportFragmentManager.setFragmentResultListener(ContactListFragment.itemClickedKey,
            this) { _, bundle ->
            supportFragmentManager.commit {
                replace(binding.mainFragmentContainer.id, ContactItemFragment().apply {
                    contactId = contacts[bundle.getInt(ContactListFragment.itemClickedPosition)].id
                })
                addToBackStack(null)
            }
        }
    }
}