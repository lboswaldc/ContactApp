package at.ac.fhstp.contactsapp.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContactRemoteService {
    @GET("contacts")
    suspend fun getAllContacts(): List<ContactDto>

    @GET("contacts/{contactId}")
    suspend fun getContactDetails(@Path("contactId") id: Int): ContactDto

    @POST
    suspend fun insertContact(@Body contactDto: ContactDto): ContactDto
}