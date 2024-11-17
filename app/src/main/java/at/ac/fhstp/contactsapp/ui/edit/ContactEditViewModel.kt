package at.ac.fhstp.contactsapp.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.fhstp.contactsapp.data.Contact
import at.ac.fhstp.contactsapp.data.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ContactEditUi(
    val contact: Contact = Contact(0, "", "", 0)
)

class ContactEditViewModel(private val savedStateHandle: SavedStateHandle,
                           private val contactRepository: ContactRepository) : ViewModel() {

    private val contactId: Int = checkNotNull(savedStateHandle["contactId"])

    var editUiState by mutableStateOf(ContactEditUi())
        private set

    init {
        viewModelScope.launch {
            val contact = withContext(Dispatchers.IO) {
                contactRepository.findContactById(contactId)
            }
            editUiState = ContactEditUi(contact)
        }
    }

    fun updateContact(contact: Contact) {
        editUiState = editUiState.copy(contact=contact)
    }

    fun saveContact() {
        viewModelScope.launch {
            contactRepository.updateContact(editUiState.contact)
        }
    }

}