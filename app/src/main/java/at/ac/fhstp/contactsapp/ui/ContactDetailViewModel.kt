package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import at.ac.fhstp.contactsapp.data.ContactRepository

class ContactDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ContactRepository) : ViewModel() {

    private val contactId : Int = savedStateHandle["contactId"] ?: 0
}