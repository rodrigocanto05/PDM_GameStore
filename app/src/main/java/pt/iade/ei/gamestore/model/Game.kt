package pt.iade.ei.gamestore.model

import java.io.Serializable

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int,
    val items: List<GameItem>
) : Serializable