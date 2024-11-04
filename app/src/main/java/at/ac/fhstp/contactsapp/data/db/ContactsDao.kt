package at.ac.fhstp.contactsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contactEntity: ContactEntity)

    @Update
    suspend fun updateContact(contactEntity: ContactEntity)

    @Delete
    suspend fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts WHERE _id = :id")
    suspend fun findContactById(id: String) : ContactEntity

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name = :contactName")
    fun findContactsWithName(contactName: String): Flow<List<ContactEntity>>

}