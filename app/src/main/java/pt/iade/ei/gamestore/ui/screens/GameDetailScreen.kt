package pt.iade.ei.gamestore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.gamestore.GameData
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem
import pt.iade.ei.gamestore.ui.components.GameDetailHeader
import pt.iade.ei.gamestore.ui.components.GameItemRow
import pt.iade.ei.gamestore.ui.components.PurchaseBottomSheet
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBack: () -> Unit = {},
    onBuyItem: (GameItem) -> Unit = {}
) {
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showSheet && selectedItem != null) {
        PurchaseBottomSheet(
            item = selectedItem!!,
            sheetState = sheetState,
            onDismiss = { showSheet = false },
            onBuy = {
                onBuyItem(it)
                showSheet = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(game.title, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
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
                GameDetailHeader(game)
            }

            item {
                Text(
                    "Purchasable Items",
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


@Preview(showBackground = true)
@Composable
fun PreviewCS2() {
    val game = GameData.games.first { it.id == 1 }
    GameStoreTheme {
        GameDetailScreen(game = game)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClashRoyale() {
    val game = GameData.games.first { it.id == 2 }
    GameStoreTheme {
        GameDetailScreen(game = game)
    }
}