package com.example.easyhomeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyhomeapp.screens.ui.theme.EasyHomeAppTheme
import com.example.taskfront_endjetpackcompose.data.DataProvider
import com.example.taskfront_endjetpackcompose.data.House

@Composable
fun SearchScreen(navController: NavController) {
    EasyHomeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val houses = remember { DataProvider.houseList }

 Card (
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
 ){
     LazyVerticalGrid(
         contentPadding = PaddingValues(16.dp),
         verticalArrangement = Arrangement.spacedBy(16.dp),
         horizontalArrangement = Arrangement.spacedBy(16.dp),
         columns = GridCells.Adaptive(140.dp),
         modifier = Modifier.fillMaxSize()) { items(
         items = houses,
         itemContent = {
             KatalogListItem(house = it)
         }
     )

     }
 }
        }
    }
}

@Composable
fun KatalogListItem(
    house: House,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = house.img),
            contentDescription = house.typeName,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = house.typeName,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}