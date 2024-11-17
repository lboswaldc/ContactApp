package at.ac.fhstp.contactsapp.data

import android.util.Log
import at.ac.fhstp.contactsapp.data.db.ContactEntity
import at.ac.fhstp.contactsapp.data.db.ContactsDao
import at.ac.fhstp.contactsapp.data.remote.ContactsRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactsDao: ContactsDao, private val contactsRemoteService: ContactsRemoteService) {

    suspend fun loadInitialContacts() {
        try {
            val contactDtoList = contactsRemoteService.getAllContacts()
            contactDtoList.map {
                Contact(0, it.name, "${it.telephoneNumber}", it.age)
            }.forEach {
                insertContact(it)
            }
        } catch (e: Exception) {
            Log.e("Repository", "Something went wrong! ${e.message}", e)
        }

    }

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

    suspend fun updateContact(contact: Contact) {
        contactsDao.updateContact(ContactEntity(contact.id, contact.name, contact.telephoneNumber, contact.age))
    }

    val names = listOf(
        "Max",
        "Tom",
        "Anna",
        "Matt"
    )

}