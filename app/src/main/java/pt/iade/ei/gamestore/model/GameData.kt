package pt.iade.ei.gamestore

import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.GameItem

object GameData {

    val games: List<Game> = listOf(
        Game(
            id = 1,
            title = "Counter-Strike 2",
            description = "Counter-Strike 2, conhecido também como CS2 é um jogo tático multijogador de tiro em primeira pessoa anunciado 22 de março de 2023 e lançado em 27 de setembro de 2023. Desenvolvido e publicado pela Valve.",
            imageRes = R.drawable.cs2,
            iconRes = R.drawable.cs2_ic,
            items = listOf(

                GameItem(
                    id = 1,
                    name = "M4A4 | Howl",
                    price = 1440.07,
                    imageRes = R.drawable.howl,
                    description = "Skin Lendária, Factory New",
                    longDescription = "A M4A4 | Howl é uma das 309 skins disponíveis para fuzis. A sua raridade é “Lendária”, o que faz desta skin um item extremamente raro, com uma probabilidade estimada de obtenção de apenas 0,26%"
                ),

                GameItem(
                    id = 2,
                    name = "AWP | Dragon Lore",
                    price = 1550.77,
                    imageRes = R.drawable.dragon_lore,
                    description = "Skin Lendária, Factory New",
                    longDescription = "A AWP | Dragon Lore foi introduzida no Counter-Strike há 11 anos, a 1 de julho de 2014. Foi lançada como parte do Boston 2018 Cobblestone Souvenir Package, juntamente com a atualização “Operation Breakout”."
                ),

                GameItem(
                    id = 3,
                    name = "Butterfly Knife | Doppler",
                    price = 1983.70,
                    imageRes = R.drawable.butterfly_doppler,
                    description = "Skin Lendária, Factory New",
                    longDescription = "Com um ranking de #237 no mercado de CSGO & CS2, a ★ Butterfly Knife | Doppler (Factory New) – Phase 3 representa uma presença já estabelecida no mercado. O histórico de preços e os padrões de troca deste item fornecem informações valiosas para jogadores, colecionadores e traders."
                )
            )
        ),

        Game(
            id = 2,
            title = "Clash Royale",
            description = "Clash Royale é um jogo de estratégia em tempo real onde coleciona e melhora cartas para enfrentar adversários em batalhas 1v1. Derrube as torres do Rei e das Princesas para ganhar troféus, coroas e alcançar novas arenas.",
            imageRes = R.drawable.clashroyal,
            iconRes = R.drawable.clashroyal_ic,
            items = listOf(

                GameItem(
                    id = 4,
                    name = "14000 Mountain of Gems",
                    price = 119.99,
                    imageRes = R.drawable.gems,
                    description = "O maior pacote de gemas do jogo.",
                    longDescription = "O pacote Mountain of Gems contém 14.000 gemas — o maior conjunto disponível no Clash Royale. Este pack permite acelerar totalmente o progresso, abrir baús instantaneamente, comprar ofertas exclusivas e avançar pelas arenas de forma extremamente rápida. Ideal para jogadores competitivos ou para quem quer maximizar recompensas durante os eventos."
                ),

                GameItem(
                    id = 5,
                    name = "8 MegaKnight Cards",
                    price = 9.99,
                    imageRes = R.drawable.megaknight,
                    description = "8 cartas da lendária Mega Knight.",
                    longDescription = "O Mega Knight é uma das cartas lendárias mais poderosas de Clash Royale, famoso pelo seu salto devastador e grande capacidade de controlo de tropas terrestres. Este pack oferece 8 cartas, permitindo evoluir a carta rapidamente, melhorar o seu nível e torná-la ainda mais eficiente em batalhas competitivas."
                ),

                GameItem(
                    id = 6,
                    name = "Pass Royale",
                    price = 14.99,
                    imageRes = R.drawable.clashroyale_pass,
                    description = "Passe mensal com recompensas exclusivas.",
                    longDescription = "O Pass Royale desbloqueia recompensas premium durante toda a temporada, incluindo baús dourados, skins exclusivas de torre, emotes raros, boosts de progressão e acesso às filas ilimitadas de baús. É a melhor forma de obter conteúdo extra e acelerar o progresso sem depender apenas das recompensas gratuitas."
                )
            )
        )
    )
}