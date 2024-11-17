package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.ac.fhstp.contactsapp.ContactsApplication
import at.ac.fhstp.contactsapp.ui.edit.ContactEditViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ContactsViewModel((this[APPLICATION_KEY] as ContactsApplication).contactsRepository)
        }

        initializer {
            ContactDetailsViewModel(this.createSavedStateHandle(), (this[APPLICATION_KEY] as ContactsApplication).contactsRepository)
        }

        initializer {
            ContactEditViewModel(this.createSavedStateHandle(), (this[APPLICATION_KEY] as ContactsApplication).contactsRepository)
        }
    }
}