package pt.iade.ei.gamestore.model

import java.io.Serializable

data class Game(
    val id: Int,
    val title: String,
    val description: String,    // desc do jogo
    val imageRes: Int,     // imagem do gamelistscreen
    val iconRes: Int,      // imagem do gamedetailscreen
    val items: List<GameItem>
) : Serializable