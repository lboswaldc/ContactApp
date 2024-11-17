package at.ac.fhstp.contactsapp.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.fhstp.contactsapp.data.Contact
import at.ac.fhstp.contactsapp.ui.AppViewModelProvider

@Composable
fun ContactEditScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSave: () -> Unit
) {
    val contact = viewModel.editUiState.contact


    ContactEditForm(contact, modifier, onValueChange = { contactChanged ->
        viewModel.updateContact(contactChanged)
    }) {
        viewModel.saveContact()
        onSave()
    }
}

@Composable
fun ContactEditForm(
    contact: Contact,
    modifier: Modifier = Modifier,
    onValueChange: (Contact) -> Unit = {},
    onSaveButtonClicked: () -> Unit = {}
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
    )
    {
        Column(Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                OutlinedTextField(
                    value = contact.name,
                    label = { Text("Name") },
                    onValueChange = { newText ->
                        onValueChange(contact.copy(name = newText))
                    })
            }
            Row {
                OutlinedTextField(
                    value = contact.age.toString(),
                    label = { Text("Age") },
                    onValueChange = { newText ->
                        onValueChange(contact.copy(age = newText.toIntOrNull() ?: 0))
                    })
            }
            Row {
                OutlinedTextField(
                    value = contact.telephoneNumber,
                    label = { Text("Telephone Number") },
                    onValueChange = { newText ->
                        onValueChange(contact.copy(telephoneNumber = newText))
                    })
            }
            Button(onClick = { onSaveButtonClicked() }) {
                Text("Save changes")
            }
        }
    }
}

@Preview
@Composable
private fun ContactEditPreview() {
    ContactEditForm(Contact(234, "Andrea", "+4311235234", 67)) { }
}