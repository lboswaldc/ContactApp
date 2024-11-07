package at.ac.fhstp.contactsapp.data

import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactsDao: ContactsDao) {
    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

    val contacts = contactsDao.readAllContacts().map { list ->
        list.map { entity ->
            Contact(entity._id, entity.name, entity.telephoneNumber, entity.age)
        }
    }

    suspend fun addRandomContact() {
        contactsDao.addContact(ContactEntity(0, names.random(), "+4357894", 45))
    }
}