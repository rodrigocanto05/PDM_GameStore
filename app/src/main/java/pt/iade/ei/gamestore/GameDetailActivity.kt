package pt.iade.ei.gamestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

class GameDetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = intent.getSerializableExtra(MainActivity.EXTRA_GAME) as? Game

        setContent {
            GameStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (game != null) {
                        GameDetailScreen(
                            game = game,
                            onBuyItem = { item ->
                                Toast.makeText(
                                    this,
                                    "Acabou de comprar o item \"${item.name}\" por ${"%.2f".format(item.price)}€",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Erro: jogo não encontrado")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBuyItem: (GameItem) -> Unit
) {
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet && selectedItem != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = selectedItem!!.imageRes),
                    contentDescription = selectedItem!!.name,
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = selectedItem!!.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Preço: ${"%.2f".format(selectedItem!!.price)}€",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onBuyItem(selectedItem!!)
                        showSheet = false
                    },
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(text = "Comprar")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(game.title) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Header com info do jogo
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = game.imageRes),
                            contentDescription = game.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = game.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = game.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Lista de itens compráveis
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
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${"%.2f".format(item.price)}€",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailPreview() {
    val fakeGame = Game(
        id = 1,
        title = "Counter-Strike 2",
        description = "FPS competitivo em equipa, clássico da Valve.",
        imageRes = R.drawable.cs,   // usa um drawable qualquer teu
        items = listOf(
            GameItem(1, "Karambit Fade", 2020.0, R.drawable.karambit_fade),
            GameItem(2, "AK-47 Vulcan", 500.0, R.drawable.ak),
            GameItem(3, "Operation Hydra Case", 35.5, R.drawable.hydra)
        )
    )

    GameStoreTheme {
        GameDetailScreen(
            game = fakeGame,
            onBuyItem = {}
        )
    }
}