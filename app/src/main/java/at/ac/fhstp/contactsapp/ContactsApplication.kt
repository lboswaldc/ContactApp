package at.ac.fhstp.contactsapp

import android.app.Application
import at.ac.fhstp.contactsapp.data.ContactRepository
import at.ac.fhstp.contactsapp.data.db.ContactsDatabase

class ContactsApplication : Application() {

    val contactRepository by lazy {
        val database = ContactsDatabase.getDatabase(this)
        ContactRepository(database.contactsDao())
    }

}