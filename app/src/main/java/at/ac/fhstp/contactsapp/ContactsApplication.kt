package at.ac.fhstp.contactsapp

import android.app.Application
import at.ac.fhstp.contactsapp.data.ContactRepository
import at.ac.fhstp.contactsapp.data.db.ContactsDatabase
import at.ac.fhstp.contactsapp.data.remote.ContactsRemoteService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class ContactsApplication : Application() {

    val contactsRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/GithubGenericUsername/find-your-pet/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .build()
        val contactsRemoteService = retrofit.create(ContactsRemoteService::class.java)

        ContactRepository(
            ContactsDatabase.getDatabase(this).contactsDao(),
            contactsRemoteService
        )
    }
}