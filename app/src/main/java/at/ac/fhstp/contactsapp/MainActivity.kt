package at.ac.fhstp.contactsapp
// ^^^^ always should be the first line in a kotlin file, typically written automatically


// we import Code from other places, e.g. libraries or our own code from different folders (packages)
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import at.ac.fhstp.contactsapp.ui.ContactsApp
import at.ac.fhstp.contactsapp.ui.theme.ContactsAppTheme

// This line means that MainActivity inherits(extends) ComponentActivity
// MainActivity inherits all functions and member variables from the full hierarchy of classes from ComponentActivity
class MainActivity : ComponentActivity() {
    // we change the method from the parent class (ComponentActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        // super is the parent (ComponentActivity)
        super.onCreate(savedInstanceState)
        // From bottom of screen to top of screen
        enableEdgeToEdge()

        // Set content allows us to pass a Composable function -> we enter the world of @Composables
        setContent {
            ContactsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactsApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


/*
@Composable
fun Display(modifier: Modifier = Modifier) {
    Text("Hi")
}


open class Shape(val length: Double) {
    open fun print() {
        println("hello from Shape")
    }
}

open class Rectangle(val width: Double, length: Double): Shape(length)

class Sphere(val height: Double, width: Double, length: Double): Rectangle(width, length) {
    override fun print() {
        println("hello from sphere")
    }
}

fun main() {
    val rectangle = Rectangle(2.2, 4.2)
    rectangle.print()
    rectangle.length
    rectangle.width

    val sphere = Sphere(2.2, 3.3, 4.4)
    sphere.print()
} */