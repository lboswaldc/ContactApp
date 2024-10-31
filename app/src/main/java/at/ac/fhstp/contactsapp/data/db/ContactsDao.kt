package at.ac.fhstp.contactsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Insert
    fun addContact(contactEntity: ContactEntity)
    // INSERT INTO contacts (name, phone) VALUES ('Max',..)

    @Update
    fun updateContact(contactEntity: ContactEntity)
    // UPDATE contacts set name = 'Anna', ...

    @Delete
    fun deleteContact(contactEntity: ContactEntity)
    // DELETE FROM contacts WHERE _id = ..

    @Query("SELECT * FROM contacts")
    fun findAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name = :contactName")
    fun findAllContactsByName(contactName: String): Flow<List<ContactEntity>>

}