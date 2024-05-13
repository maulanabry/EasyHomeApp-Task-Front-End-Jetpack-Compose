package com.example.easyhomeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.easyhomeapp.navigation.Screens
import com.example.easyhomeapp.screens.ui.theme.EasyHomeAppTheme
import com.example.taskfront_endjetpackcompose.data.DataProvider
import com.example.taskfront_endjetpackcompose.data.Developer
import com.example.taskfront_endjetpackcompose.data.House


@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    EasyHomeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val developers = remember { DataProvider.developerList }
            val houses = remember { DataProvider.houseList }
            Column ( modifier = Modifier.fillMaxSize()){
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    text = "Rekomendasi",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black
                )
                Box(Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)) {
                    LazyRow {
                        items(
                            items = houses,
                            itemContent = {
                                HouseListItem(house = it)
                            }
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    text = "Developer",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp))
                {

                    LazyColumn(modifier = Modifier) {
                        items(items = developers, key = { it.id }) {
                            DeveloperListItem(developer = it) {
                                 developerId ->
                                    navController.navigate(Screens.DetailDeveloper.route + "/$developerId")

                            }
                        }
                    }
                }

            }





        }
    }
}





@Composable
fun DeveloperListItem (
    developer: Developer,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {onItemClicked(developer.id)}
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
        ),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row {
            DeveloperImage(developer)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)




            ) {
                Text(text = developer.Name, style = MaterialTheme.typography.bodySmall,fontWeight = FontWeight.Black )
                Text(text = developer.Origin, style = MaterialTheme.typography.bodyMedium)

            }
        }
    }
}


@Composable
private fun DeveloperImage(developer: Developer) {
    Image(
        painter = painterResource(id = developer.Img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

    )
}


/*
@Composable
fun <NavController> HomeScreen(navController: NavController) {
    EasyHomeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val houses = remember { DataProvider.houseList }
            LazyColumn {
                items(
                    items = houses,
                    itemContent = {
                        HouseListItem (house = it)
                    }
                )
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .clip(MaterialTheme.shapes.large)
                ) {

                }

            }
        }
    }
}
}*/


@Composable
fun HouseListItem(house: House) {

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
        ),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row {
            HouseImage(house)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)


            ) {
                Text(text = house.typeName, style = MaterialTheme.typography.bodySmall,fontWeight = FontWeight.Black )
                Text(text = house.tipe, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
private fun HouseImage(house: House) {
    Image(
        painter = painterResource(id = house.img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}
