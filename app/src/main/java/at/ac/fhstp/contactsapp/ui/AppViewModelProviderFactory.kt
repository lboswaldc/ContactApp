package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.ac.fhstp.contactsapp.ContactsApplication

object AppViewModelProviderFactory {
    val Factory = viewModelFactory {
        initializer {
            val application = this[APPLICATION_KEY] as ContactsApplication
            ContactsViewModel(application.contactRepository)
        }
    }
}