package com.example.ca

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ca.ui.theme.CaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaTheme {
                impli()
            }
        }
    }
}

@Composable
fun impli() {
    val context = LocalContext.current
    val movieName = "Avatar"
    val ticketPrice = 100

    var n by remember { mutableStateOf("") }
    var s by remember { mutableStateOf("") }
    var paidAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Movie Ticket Booking",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Movie: $movieName",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Ticket Price: ₹$ticketPrice",
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = n,
            onValueChange = { n = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = s,
            onValueChange = { s = it },
            label = { Text("Seats") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = paidAmount,
            onValueChange = { paidAmount = it },
            label = { Text("Money") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val seatCount = s.toIntOrNull() ?: 0
                val amountPaid = paidAmount.toIntOrNull() ?: 0
                val totalAmount = ticketPrice * seatCount

                if (n.isNotBlank() && seatCount > 0 && amountPaid == totalAmount) {
                    val message = """
                        Movie ticket booked successfully
                        Name: $n
                        Movie: $movieName
                        Seats: $seatCount
                        Total Amount: ₹$totalAmount
                    """.trimIndent()
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, message)
                    }
                    context.startActivity(
                        Intent.createChooser(intent, "Booking Details")
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Details mismatch",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Book Ticket", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImpliPreview() {
    CaTheme {
        impli()
    }
}
