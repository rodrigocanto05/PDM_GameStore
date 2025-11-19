package pt.iade.ei.gamestore.model

import java.io.Serializable

data class GameItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int
) : Serializable