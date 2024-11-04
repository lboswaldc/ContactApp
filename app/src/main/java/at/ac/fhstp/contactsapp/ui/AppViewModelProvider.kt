package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.ac.fhstp.contactsapp.ContactsApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            val contactsApplication = this[APPLICATION_KEY] as ContactsApplication
            ContactsViewModel(contactsApplication.contactRepository)
        }

        initializer {
            val contactsApplication = this[APPLICATION_KEY] as ContactsApplication
            ContactDetailViewModel(
                this.createSavedStateHandle(),
                contactsApplication.contactRepository
            )
        }

    }

}