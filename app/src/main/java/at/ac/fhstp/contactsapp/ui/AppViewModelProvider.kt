package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.ac.fhstp.contactsapp.ContactsApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ContactsViewModel((this.get(APPLICATION_KEY) as ContactsApplication).contactsRepository)
        }
    }
}