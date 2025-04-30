package src

import java.util.Scanner
import Pokemon
import pokemons
import kotlin.random.Random
import tiposEfetivos

var nick: String? = null
var nickTwo: String? = null

val timeOne = mutableListOf<Pokemon>()
val timeTwo = mutableListOf<Pokemon>()

var timeResp: Int = 0
// variaveis globais e imports acima ______________________________

fun mostrarMenu() {
    val scanner = Scanner(System.`in`)
    var run = true

    while (run) {
        println("MENU")
        println("1 - Digite seu Nick")
        println("2 - Escolher Pokémons (3)")
        println("3 - Mostrar Time")
        println("4 - Batalhar")
        println("5 - Sair...")


        print("Escolha uma opção: ")
        when (scanner.nextLine().toIntOrNull()) {
            1 -> {
                print("Digite seu Nick: ")
                nick = scanner.nextLine().trim()
                print("Nick Adversário: ")
                nickTwo = scanner.nextLine().trim()
            }
            2 -> {
                timeOne.clear()
                timeTwo.clear()
                timeOne.addAll(escolherPokemons(scanner, nick))
                timeTwo.addAll(escolherPokemons(scanner, nickTwo))
            }
            3 -> {
                mostrarTime()
            }
            4 -> batalhar()
            5 -> {
                println("Saindo do programa...")
                run = false
            }
            else -> println("Opção inválida. Digite novamente.")
        }
    }
}

fun mostrarTime() {
    if (timeResp == 1) {
        println("Time de ${Ciano}${nick}${Reset}")
        timeOne.forEach {
            println("- ${Amarelo}${it.nome}${Reset} ${Roxo}(${it.tipo.joinToString()})${Reset}")
        }
    } else {
        println("Time de ${Ciano}${nickTwo}${Reset}")
        timeTwo.forEach {
            println("- ${Amarelo}${it.nome}${Reset} ${Roxo}(${it.tipo.joinToString()})${Reset}")
        }
    }
}
fun escolherPokemonInicial(time: List<Pokemon>): Pokemon {
    println("Escolha seu Pokémon inicial: ")
    time.forEachIndexed { index, pokemon ->
        println("${index + 1}. ${pokemon.nome} (${pokemon.tipo.joinToString()})")
    }
    while (true) {
        print("Digite o número do Pokémon: ")
        val escolhaInicial = readLine()?.toIntOrNull()

        if (escolhaInicial != null && escolhaInicial in 1..time.size) {
            return time[escolhaInicial - 1]
        } else {
            println("Escolha inválida.")
        }
    }
}

fun escolherPokemonBatalha(time: List<PokemonStatus>): PokemonStatus { // loop forEach pra escolher os 3 pokemons pra batalha
    println("Escolha seu Pokémon para a batalha:")
    time.forEachIndexed { index, pokemon ->
        println("${index + 1}. ${Verde}${pokemon.pokemon.nome}${Reset} (${pokemon.pokemon.tipo.joinToString()})")
    }

    while (true) { // pra escolher o pokemom caso seja duas pessoas em vez de um esqeuizofrenico
        print("Digite o número do Pokémon: ")
        val escolha = readLine()?.toIntOrNull()
        if (escolha != null && escolha in 1..time.size) {
            return time[escolha - 1]
        } else {
            println("Escolha inválida.")
        }
    }
}

fun danoEfetivo(atacante: Pokemon, defensor: Pokemon, danoBase: Int): Int {
    return if (atacante.tipo.any { tipo -> tiposEfetivos[tipo]?.any { it in defensor.tipo } == true }) { //  tipos do pokemon que esta atacando/defendendo
        println("Olha o crítico TAPORRAAAkkkkk!")
        danoBase * 2 // se o pokemon for efetivo contra ja dobra o dano nessa porra
    } else danoBase // o dano base é o random la em baixo
}


private fun List<String>.containsAny(others: List<String>): Boolean { // sinceramente ali nas variaveis Status tava com erro e essa linha arrumou por que ? nao sei.
    return this.any { it in others }
}
fun batalhar() {
    val timeStatusOne = timeOne.map { PokemonStatus(it) }.toMutableList() // Status do time recebendo o time percorrido la na lista dos pokemons
    val timeStatusTwo = timeTwo.map { PokemonStatus(it) }.toMutableList()

    println("${Vermelho}\n🔥🔥 BATALHA INICIADA ENTRE ${nick} E ${nickTwo}! 🔥🔥${Reset}\n")

    while (timeStatusOne.isNotEmpty() && timeStatusTwo.isNotEmpty()) { // se nao tiver fazia
        val atualOne = escolherPokemonBatalha(timeStatusOne) // pokemon que tiver on na batalha
        val atualTwo = escolherPokemonBatalha(timeStatusTwo)

        println("${Ciano}\n${nick}${Reset} escolheu ${Verde}${atualOne.pokemon.nome}${Reset}")
        Thread.sleep(1000)
        println("${Ciano}${nickTwo}${Reset} escolheu ${Verde}${atualTwo.pokemon.nome}\n${Reset}")
        Thread.sleep(1000)

        while (atualOne.vida > 0 && atualTwo.vida > 0) { // loop de vida for maior que 0 continua lutando até morrer
            println("\n🌀 ${Amarelo}-- Turno de Batalha --${Reset}")
            Thread.sleep(1000) // sleep

            val danoBaseOne = Random.nextInt(10, 30)  // random dando dano e gerando porcentagem de chance
            val danoBaseTwo = Random.nextInt(10, 30)

            val danoOne = danoEfetivo(atualOne.pokemon, atualTwo.pokemon, danoBaseOne) // dano do pokemon recebendo o critico dobrado
            Thread.sleep(1000)
            val danoTwo = danoEfetivo(atualTwo.pokemon, atualOne.pokemon, danoBaseTwo)
            Thread.sleep(1000)

            atualTwo.vida -= danoOne // vai atualizando a vida do poke
            atualOne.vida -= danoTwo

            println("${Verde}${atualOne.pokemon.nome} ${Reset} atacou e causou ${Vermelho}$danoOne de dano.\n${Reset}") // pikachu deu 19 de dano
            Thread.sleep(1000)
            println("${Verde}${atualTwo.pokemon.nome}${Reset} atacou e causou ${Vermelho}$danoTwo de dano.\n${Reset}")
            Thread.sleep(1000)
            println("❤️ Vida de ${Verde}${atualOne.pokemon.nome}${Reset}: ${Vermelho}${atualOne.vida.coerceAtLeast(0)}${Reset}")
            println("❤️ Vida de ${Verde}${atualTwo.pokemon.nome}${Reset}: ${Vermelho}${atualTwo.vida.coerceAtLeast(0)}${Reset}")
            println("${Azul}----------------------------${Reset}")
            Thread.sleep(1000)
        }

        when {
            atualOne.vida <= 0 && atualTwo.vida <= 0 -> {
                println("\n⚖️ Empate entre ${Amarelo}${atualOne.pokemon.nome}${Reset} e ${Amarelo}${atualTwo.pokemon.nome}${Reset}!")
                timeStatusOne.remove(atualOne)
                timeStatusTwo.remove(atualTwo)
            }
            atualOne.vida <= 0 -> {
                println("\n☠️ ${Verde}${atualOne.pokemon.nome}${Reset} foi derrotado!")
                timeStatusOne.remove(atualOne)
            }
            atualTwo.vida <= 0 -> {
                println("\n☠️ ${Verde}${atualTwo.pokemon.nome}${Reset} foi derrotado!")
                timeStatusTwo.remove(atualTwo)
            }
        }
    }
       println("\n ${Amarelo}--- RESULTADO FINAL ---${Reset}")
    if (timeStatusOne.isEmpty() && timeStatusTwo.isEmpty()) {
        println("🤝 A batalha terminou em ${Roxo}EMPATE${Reset}!") // se os dois ficarem sem pokemon ai sim empate na batalha  se nao auto explicativo em baixo
    } else if (timeStatusOne.isEmpty()) {
        println("🏆 ${Ciano}${nickTwo}${Reset} LEVA A ${Amarelo}INSÍGNIA DE PEDRA!${Reset}")
    } else {
        println("🏆 ${Ciano}${nick}${Reset} LEVA A ${Azul}INSÍGNIA DE ÁGUA!${Reset}")
    }
}

fun main() {
    mostrarMenu()
}
