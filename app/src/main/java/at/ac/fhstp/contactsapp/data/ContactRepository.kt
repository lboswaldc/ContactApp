package at.ac.fhstp.contactsapp.data

import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactsDao: ContactsDao) {

    fun getAllContacts(): Flow<List<Contact>> {
        return contactsDao.getAllContacts().map {
            it.map {item -> Contact(item._id, item.name, item.telephoneNumber, item.age) }
        }
    }

    suspend fun findContactById(id: Int): Contact {
        val item = contactsDao.findContactById(id)
        return Contact(item._id, item.name, item.telephoneNumber, item.age)
    }

    suspend fun insertContact(contact: Contact) {
        contactsDao.addContact(ContactEntity(_id=0, contact.name, contact.telephoneNumber, contact.age))
    }

    suspend fun addRandomContact() {
        insertContact(Contact(0, names.random(), "+4357894", 45))
    }

    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

    fun createContacts(): List<Contact> {
        val contacts = (1..20).map {
            Contact(
                0,
                "${names.random()} $it",
                "+43 123456$it",
                25 + it
            )
        }
        return contacts
    }


}