package com.example.easyhomeapp.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.easyhomeapp.R
import com.example.easyhomeapp.screens.ui.theme.EasyHomeAppTheme
@Composable
fun ProfileScreen(navController: NavController) {
    EasyHomeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Profil
                Text(
                    "Profil Pengguna",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Divider(color = Color.Gray)

                // Foto Profil
                Image(
                    painter = painterResource(id = R.drawable.goto2),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = CircleShape)
                        .align(Alignment.CenterHorizontally)
                )

                // Informasi Profil
                ProfileInfoItem(label = "Nama", value = "Maulana Bryan Syahputra")
                ProfileInfoItem(label = "Universitas", value = "UPN Veteran Jawa Timur")
                ProfileInfoItem(label = "Jurusan", value = "Sistem Informasi")

                // Tombol LinkedIn
                val linkedinProfileUrl = "https://www.linkedin.com/in/bryan-syahputra/"
                val context = LocalContext.current
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinProfileUrl))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                Button(
                    onClick = { context.startActivity(intent) }  ,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("LinkedIn")
                }
            }
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Text(text = value, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun openLinkedInProfile() {
    val linkedinProfileUrl = "https://www.linkedin.com/in/bryan-syahputra/"
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinProfileUrl))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}