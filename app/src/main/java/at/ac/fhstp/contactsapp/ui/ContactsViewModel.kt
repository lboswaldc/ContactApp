package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.fhstp.contactsapp.data.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactsViewModel(val repository: ContactRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.loadInitialContacts()
        }
    }


    val contactsUiState = repository.getAllContacts()
        .map { ContactsUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ContactsUiState(emptyList())
        )

    fun onAddButtonClicked() {
        viewModelScope.launch {
            repository.addRandomContact()
        }
    }
}
