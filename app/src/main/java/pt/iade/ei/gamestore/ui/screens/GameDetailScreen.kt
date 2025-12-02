package pt.iade.ei.gamestore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.gamestore.GameData
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBack: () -> Unit = {},
    onBuyItem: (GameItem) -> Unit = {}
) {
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    // Bottom Sheet
    if (showSheet && selectedItem != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Text(
                    text = selectedItem!!.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(selectedItem!!.imageRes),
                        contentDescription = selectedItem!!.name,
                        modifier = Modifier
                            .size(110.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = selectedItem!!.longDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Preço: %.2f€".format(selectedItem!!.price),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Button(
                        onClick = {
                            onBuyItem(selectedItem!!)
                            showSheet = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("Comprar com 1 toque")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        game.title,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.StarBorder, contentDescription = "Favorito")
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = game.iconRes),
                            contentDescription = game.title,
                            modifier = Modifier.size(110.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(Modifier.width(16.dp))

                        Text(
                            text = game.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Purchasable Items",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            items(game.items) { item ->
                GameItemRow(
                    item = item,
                    onClick = {
                        selectedItem = item
                        showSheet = true
                    }
                )
            }
        }
    }
}

@Composable
fun GameItemRow(
    item: GameItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2
                )
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = "%.2f€".format(item.price),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCS2() {
    val game = GameData.games.first { it.id == 1 }
    GameStoreTheme {
        GameDetailScreen(
            game = game,
            onBack = {},
            onBuyItem = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClashRoyale() {
    val game = GameData.games.first { it.id == 2 }
    GameStoreTheme {
        GameDetailScreen(
            game = game,
            onBack = {},
            onBuyItem = {}
        )
    }
}
