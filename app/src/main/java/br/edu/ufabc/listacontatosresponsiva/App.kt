package br.edu.ufabc.listacontatosresponsiva

import android.app.Application
import android.util.Log
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import java.io.FileNotFoundException

class App : Application() {
    companion object {
        private const val contactsFile = "contacts.json"
    }

    private val contacts = mutableListOf<Contact>()

    override fun onCreate() {
        super.onCreate()

        try {
            resources.assets.open(contactsFile).use {
                contacts.addAll(Klaxon().parseArray(it) ?: emptyList())
            }
        } catch (e: FileNotFoundException) {
            Log.e("APP", "Failed to open dataset file", e)
        } catch (e: KlaxonException) {
            Log.e("APP", "Failed to parse dataset file", e)
        }
    }

    fun getAllContacts() = contacts

    fun getContactById(id: String) = try {
        contacts.first {it.id == id}
    } catch (e: NoSuchElementException) {
        Contact("", "", "", "", "")
    }


}