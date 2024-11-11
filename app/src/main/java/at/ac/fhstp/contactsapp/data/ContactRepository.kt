package at.ac.fhstp.contactsapp.data

import android.util.Log
import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import at.ac.fhstp.contactsapp.data.remote.ContactRemoteService
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactsDao: ContactsDao,
    private val contactRemoteService: ContactRemoteService
) {

    val contacts = contactsDao.getAllContacts().map { contactList ->
        contactList.map { entity ->
            Contact(entity._id, entity.name, entity.telephoneNumber, entity.age)
        }
    }

    suspend fun loadInitialContacts() {
        Log.w("REPOSITORY", "Start loading")
       val remoteContacts = contactRemoteService.getAllContacts()
        Log.w("REPOSITORY", "Done loading")
       remoteContacts.map {
           dto -> ContactEntity(0, dto.name, dto.age, dto.telephoneNumber.toString())
       }.forEach {
           entity -> contactsDao.addContact(entity)
       }
    }

    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

    suspend fun addRandomContact() {
        contactsDao.addContact(ContactEntity(0, names.random(), 45, "+4357894"))
    }

    suspend fun findContactById(contactId: Int): Contact {
        val contactEntity = contactsDao.findContactById(contactId)
        return Contact(
            contactEntity._id, contactEntity.name, contactEntity.telephoneNumber, contactEntity.age
        )
    }
}