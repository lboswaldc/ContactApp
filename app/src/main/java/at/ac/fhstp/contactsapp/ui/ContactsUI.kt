package at.ac.fhstp.contactsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhstp.contactsapp.data.Contact
import at.ac.fhstp.contactsapp.ui.edit.ContactEditScreen
import at.ac.fhstp.contactsapp.ui.theme.Typography

enum class ContactRoutes(val route: String) {
    Home("home"),
    Detail("contacts/{contactId}"),
    Edit("contacts/{contactId}/edit")
}

@Composable
fun ContactsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ContactRoutes.Home.route,
        modifier = modifier
    ) {
        composable(ContactRoutes.Home.route) {
            ContactsHomeScreen(onEditClick = {
                navController.navigate(ContactRoutes.Edit.route.replace("{contactId}", "$it"))
            }) {
                navController.navigate(ContactRoutes.Detail.route.replace("{contactId}", "$it"))
            }
        }
        composable(
            route = ContactRoutes.Detail.route,
            arguments = listOf(navArgument("contactId") {
                type = NavType.IntType
            })
        ) {
            ContactDetailsScreen()
        }
        composable(
            route = ContactRoutes.Edit.route,
            arguments = listOf(navArgument("contactId") {
                type = NavType.IntType
            })
        ) {
            ContactEditScreen() {
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun ContactsHomeScreen(
    modifier: Modifier = Modifier,
    contactsViewModel: ContactsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onEditClick: (Int) -> Unit,
    onCardClick: (Int) -> Unit
) {
    val state by contactsViewModel.contactsUiState.collectAsStateWithLifecycle()

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // We use lazy column for dynamic lists or large lists
        // it will only draw the visible contacts

        Button(onClick = {
            contactsViewModel.onAddButtonClicked()
        }) {
            Text("Add new contact!")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(state.contacts) { index, contact ->
                ContactListItem(contact, onCardClick = {
                    onCardClick(contact.id)
                }, onEditClick = {
                    onEditClick(contact.id)
                })
            }
        }
        /*Column {
            state.contacts.forEach {
                ContactListItem(it)
            }
        } */
    }
}

@Composable
fun ContactListItem(contact: Contact, onCardClick: () -> Unit, onEditClick: ()->Unit, modifier: Modifier = Modifier) {
    OutlinedCard(
        onClick = { onCardClick() }, modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(contact.name, style = Typography.headlineMedium)
            IconButton(onEditClick) {
                Icon(Icons.Outlined.Edit, "Edit contact")
            }
        }
    }
}

@Composable
fun ContactDetailsScreen(modifier: Modifier = Modifier, contactDetailsViewModel: ContactDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
  val detailUiState by contactDetailsViewModel.detailUiState.collectAsStateWithLifecycle()

  ContactDetails(detailUiState.contact, modifier)
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(contact.name, style = Typography.headlineMedium)
            Row {
                Text("Tel: ${contact.telephoneNumber}", style = Typography.headlineSmall)
                Spacer(Modifier.width(16.dp))
                Text("Age: ${contact.age}", style = Typography.headlineSmall)
            }
        }
    }
}

@Preview
@Composable
private fun ContactDetailsPreview() {
    ContactDetails(Contact(0, "Andrea", "+4354897", 28))
}

@Preview
@Composable
private fun ContactListItemPreview() {
    ContactListItem(Contact(0, "Andrea", "+4354897", 28), {}, {})
}