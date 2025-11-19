package pt.iade.ei.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_GAME = "extra_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val games = SampleData.games

        setContent {
            GameStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameStoreScreen(
                        games = games,
                        onGameClick = { game ->
                            val intent = Intent(this, GameDetailActivity::class.java)
                            intent.putExtra(EXTRA_GAME, game)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameStoreScreen(
    games: List<Game>,
    onGameClick: (Game) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Store") }
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
            items(games) { game ->
                GameCard(
                    game = game,
                    onClick = { onGameClick(game) }
                )
            }
        }
    }
}

@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = game.imageRes),
                contentDescription = game.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = game.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {
    GameStoreTheme {
        GameCard(
            game = SampleData.games.first(),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameStoreScreenPreview() {
    GameStoreTheme {
        GameStoreScreen(
            games = SampleData.games,
            onGameClick = {}
        )
    }
}