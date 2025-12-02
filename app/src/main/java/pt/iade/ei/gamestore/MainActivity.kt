package pt.iade.ei.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.ui.screens.GameListScreen
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_GAME = "extra_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val games: List<Game> = GameData.games

        setContent {
            GameStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameListScreen(
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