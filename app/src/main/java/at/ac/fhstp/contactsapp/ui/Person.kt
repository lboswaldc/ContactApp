package at.ac.fhstp.contactsapp.ui

import androidx.compose.runtime.Composable

class Person(val name: String) {
}

fun main() {
    val person1 = Person("joey machin")
    // Full qualified name -> package-name + class name
    val person2 = at.ac.fhstp.contactsapp.data.Person("Andrea")
}

@Composable
fun AsyncImage(uri: String) {

}