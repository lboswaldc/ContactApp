package at.ac.fhstp.contactsapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import at.ac.fhstp.contactsapp.data.ContactRepository
import at.ac.fhstp.contactsapp.data.db.ContactsDatabase
import at.ac.fhstp.contactsapp.ui.ContactsApp
import at.ac.fhstp.contactsapp.ui.theme.ContactsAppTheme

class ContactsApplication : Application() {

    val contactsRepository by lazy {
         ContactRepository(ContactsDatabase.getDatabase(this).contactsDao())
    }
}