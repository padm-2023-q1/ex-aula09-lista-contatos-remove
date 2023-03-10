package br.edu.ufabc.listacontatosresponsiva

import java.io.Serializable

/**
 * Transfer (domain) object.
 */
data class Contact(
    var id: String,
    var name: String,
    var email: String,
    var phone: String,
    var address: String
) : Serializable