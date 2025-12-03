package pt.iade.ei.gamestore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.gamestore.GameData
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.ui.components.GameCard
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@Composable
fun GameListScreen(
    games: List<Game>,
    onGameClick: (Game) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 78.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Pro Market",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
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

@Preview(showBackground = true)
@Composable
fun PreviewGameList() {
    GameStoreTheme {
        GameListScreen(
            games = GameData.games,
            onGameClick = {}
        )
    }
}