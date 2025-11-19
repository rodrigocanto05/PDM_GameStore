package pt.iade.ei.gamestore

import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem

object SampleData {

    val games: List<Game> = listOf(
        Game(
            id = 1,
            title = "Counter-Strike 2",
            description = "FPS competitivo em equipa, clássico da Valve atualizado para Source 2.",
            imageRes = R.drawable.cs,
            items = listOf(
                GameItem(
                    id = 1,
                    name = "Karambit ★ | Fade (Factory New)",
                    price = 2020.00,
                    imageRes = R.drawable.karambit_fade
                ),
                GameItem(
                    id = 2,
                    name = "AK-47 | Vulcan (Fild Tested)",
                    price = 500.00,
                    imageRes = R.drawable.ak
                ),
                GameItem(
                    id = 3,
                    name = "Operation Hydra Case",
                    price = 35.50,
                    imageRes = R.drawable.hydra
                )
            )
        ),
        Game(
            id = 2,
            title = "Clash Royale",
            description = "Clash Royale é um jogo de estratégia, defesa de torres e combate multiplayer.",
            imageRes = R.drawable.clashroyal,
            items = listOf(
                GameItem(
                    id = 4,
                    name = "Pass Royal",
                    price = 12.00,
                    imageRes = R.drawable.pass
                ),
                GameItem(
                    id = 5,
                    name = "Three fragments of evolution (Mega Knight)",
                    price = 6.99,
                    imageRes = R.drawable.mega
                ),
                GameItem(
                    id = 6,
                    name = "14000 Gems Pack",
                    price = 110.00,
                    imageRes = R.drawable.gemas
                )
            )
        )
    )
}