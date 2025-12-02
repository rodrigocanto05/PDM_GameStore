package pt.iade.ei.gamestore.model

import java.io.Serializable

data class GameItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val description: String,  // descriçao curta do item
    val longDescription: String,  // descriçao longa do item
) : Serializable