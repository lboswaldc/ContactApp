package at.ac.fhstp.contactsapp.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// https://my-json-server.typicode.com/GithubGenericUsername/find-your-pet/contacts

interface ContactsRemoteService {
    @GET("contacts")
    suspend fun getAllContacts(): List<ContactDto>

    @GET("contacts/{contactId}")
    suspend fun getContactById(@Path("contactId") contactId: Int) : ContactDto

    @GET("contacts/search") // becomes contacts/search?filter=<content of filterText>
    suspend fun findContacts(@Query("filter") filterText : String): List<ContactDto>

    @POST("contacts")
    suspend fun addContact(@Body contactDto: ContactDto): ContactDto
}