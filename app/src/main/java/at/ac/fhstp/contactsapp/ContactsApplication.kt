package at.ac.fhstp.contactsapp

import android.app.Application
import at.ac.fhstp.contactsapp.data.ContactRepository
import at.ac.fhstp.contactsapp.data.db.ContactsDatabase
import at.ac.fhstp.contactsapp.data.remote.ContactsRemoteService
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class ContactsApplication : Application() {

    val contactsRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/GithubGenericUsername/find-your-pet/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(MediaType.get("application/json")))
            .build()
        val contactsRemoteService = retrofit.create(ContactsRemoteService::class.java)

        ContactRepository(
            ContactsDatabase.getDatabase(this).contactsDao(),
            contactsRemoteService
        )
    }
}