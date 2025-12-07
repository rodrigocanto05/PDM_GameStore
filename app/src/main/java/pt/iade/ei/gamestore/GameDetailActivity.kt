package pt.iade.ei.gamestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem
import pt.iade.ei.gamestore.ui.screens.GameDetailScreen
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

class GameDetailActivity : ComponentActivity() {

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
                            onBack = { finish() },
                            onBuyItem = { item: GameItem ->
                                Toast.makeText(
                                    this,
                                    "Acabou de comprar o item \"${item.name}\" por ${"%.2f".format(item.price)}€",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}