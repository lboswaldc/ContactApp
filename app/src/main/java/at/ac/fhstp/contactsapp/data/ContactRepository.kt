package at.ac.fhstp.contactsapp.data

import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactsDao: ContactsDao) {

    val contacts = contactsDao.getAllContacts().map { contactList ->
        contactList.map { entity ->
            Contact(entity._id, entity.name, entity.telephoneNumber, entity.age)
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