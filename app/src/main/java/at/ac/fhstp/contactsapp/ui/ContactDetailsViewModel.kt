package at.ac.fhstp.contactsapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.fhstp.contactsapp.data.Contact
import at.ac.fhstp.contactsapp.data.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ContactDetailUi(
    val contact: Contact = Contact(0, "", "", 0)
)

class ContactDetailsViewModel(savedStateHandle: SavedStateHandle, private val contactRepository: ContactRepository): ViewModel() {

    private val contactId: Int = checkNotNull(savedStateHandle["contactId"])

    private val _detailUiState = MutableStateFlow(ContactDetailUi())
    val detailUiState = _detailUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val contact = withContext(Dispatchers.IO) {
                contactRepository.findContactById(contactId)
            }
            _detailUiState.update {
                ContactDetailUi(contact)
            }
        }
    }

}