package com.example.hilalplaytest.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.hilalplaytest.R
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.ui.icons.Local_fire_department
import com.example.hilalplaytest.ui.icons.Vegan
import java.util.Locale


@Composable
fun MenuItemCard(
    item: MenuItem,
    onClick: () -> Unit,
    onFavoriteClick: (MenuItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.3f))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_item_image_placeholder),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.ic_item_image_placeholder),
                    modifier = Modifier.clip(CircleShape),
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = item.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            modifier = Modifier.size(12.dp),
                            tint = Color(0xFFFFB800)
                        )
                        Text(
                            text = item.rating.toString(),
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }

                    if (item.isAvailable) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Available",
                                modifier = Modifier.size(12.dp),
                                tint = Color(0xFF4CAF50)
                            )
                            Text(
                                text = "Available",
                                fontSize = 10.sp,
                                color = Color(0xFF4CAF50)
                            )
                        }
                    }

                    if (item.hot) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Red)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Icon(
                                imageVector = Local_fire_department,
                                contentDescription = "hot",
                                modifier = Modifier.size(12.dp),
                                tint = Color.White
                            )
                            Text(
                                text = "hot",
                                fontSize = 10.sp,
                                color = Color.Yellow
                            )
                        }
                    }


                    if (item.vegan) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Green.copy(alpha = .2f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Icon(
                                imageVector = Vegan,
                                contentDescription = "vegan",
                                modifier = Modifier.size(12.dp),
                                tint = Color.Black
                            )
                            Text(
                                text = "vegan",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        }
                    }

                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "$${String.format(Locale.ENGLISH, "%.2f", item.price)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                IconButton(onClick = {
                    onFavoriteClick(item)
                }) {
                    Icon(
                        imageVector = if (item.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemCardPreview(

) {
    Column {
        MenuItemCard(
            item = MenuItem(
                isAvailable = true,
                description = "Delicious spicy chicken burger with extra cheese and sauce.",
                hot = true,
                id = 1,
                image = "https://example.com/images/burger.jpg",
                name = "Spicy Chicken Burger",
                price = 24.99,
                rating = 4.5,
                vegan = false,
                isFavorite = true
            ),
            onClick = {}
        ) {}

        MenuItemCard(
            item = MenuItem(
                isAvailable = false,
                description = "Fresh vegan wrap with hummus and veggies.",
                hot = false,
                id = 2,
                image = "https://example.com/images/vegan-wrap.jpg",
                name = "Vegan Wrap",
                price = 19.50,
                rating = 4.8,
                vegan = true,
                isFavorite = false
            ),
            onClick = {}
        ) {}
    }
}