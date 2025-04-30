package src

import src.PokemonStatus /* uma Zona esses import aqui mas se ta funfando*/
import java.util.Scanner
import Pokemon
import pokemons
import kotlin.random.Random

// CORES
val Vermelho = "\u001B[31m" // aonde tu ver esse codigo tem cor ele funciona de forma bem simples / codigo da cor e fecha com a propriedade Reset até onde quer
val Verde = "\u001B[32m"
val Amarelo = "\u001B[33m"
val Azul = "\u001B[34m"
val Roxo = "\u001B[35m"
val Ciano = "\u001B[36m"
val Reset = "\u001B[0m"


class PokemonStatus (val pokemon: Pokemon) { // define a vida do pokemon sendo atual (50)
    var vida: Int = 50
}
fun main() {
    val scanner = Scanner(System.`in`) // vai ler as entradas
    var run = true // até Sair...

    while (run) {
        println("MENU")
        println("1 - ${Azul}Digite seu Nick${Reset}") // nick dos dois jogadores
        println("2 - ${Verde}Escolher Pokémons (3)${Reset}") // Escolher 3 poke para cada jogador tanto por numero quanto por nome
        println("3 - ${Amarelo}Mostrar Time${Reset}") // mostra o time sendo 1 e 2 (Nomes e tipos)
        println("4 - ${Ciano}Batalhar${Reset}") // da start na batalha onde a logica é o random diminuindo o 'vida' que vai ser criado
        println("5 - ${Vermelho}Sair...${Reset}") // Fim do Run

        print("Escolha uma opção: ")
        when (scanner.nextLine().toIntOrNull()) {
            1 -> {
                print("Digite seu Nick: ")
                nick = scanner.nextLine().trim() // trim trata os espaços vazios
                print("Nick Adversário: ")
                nickTwo = scanner.nextLine().trim()
            }
            2 -> {
                timeOne.addAll(escolherPokemons(scanner, nick)) // adiciona os poke escolhidos no time podendo mostrar na opção 3
                timeTwo.addAll(escolherPokemons(scanner, nickTwo))
            }
            3 -> {
                print("Qual time você quer ver 1 ou 2  ")
                timeResp = scanner.nextLine()?.toIntOrNull() ?: 0
                mostrarTime() // pega la do menu
            }
            4 -> batalhar() // pega la do menu
            5 -> {
                println("Saindo do programa...")
                run = false // saiu
            }
            else -> println("Opção inválida. Digite novamente.")
        }
    }
}

fun escolherPokemons(scanner: Scanner, treinador: String?): List<Pokemon> {
    val pokemonsEscolhidos = mutableListOf<Pokemon>()

    println("Treinador $treinador, escolha seus Pokémons (3): ")

    while (pokemonsEscolhidos.size < 3) {
        val colunaSize = (pokemons.size / 3) + 1 // cria as colunas e adiciona os poke
        val colunaOne = pokemons.take(colunaSize)
        val colunaTwo = pokemons.drop(colunaSize).take(colunaSize)
        val colunaTree = pokemons.drop(colunaSize * 2)

        for (i in 0 until maxOf(colunaOne.size, colunaTwo.size, colunaTree.size)) { // colocar index nos pokemons esolhidos pra facilitar
            val indexOne = i + 1
            val indexTwo = i + colunaOne.size + 1
            val indexTree = i + (colunaTwo.size * 2) + 1

            val pokemonOne = if (i < colunaOne.size) "[${indexOne.toString().padStart(2, '0')}] ${colunaOne[i].nome}" else "" //colunas alinhadas
            val pokemonTwo = if (i < colunaTwo.size) "[${indexTwo.toString().padStart(2, '0')}] ${colunaTwo[i].nome}" else ""
            val pokemonTree = if (i < colunaTree.size) "[${indexTree.toString().padStart(2, '0')}] ${colunaTree[i].nome}" else ""

            println(
                String.format("%-20s %-20s %-20s", pokemonOne, pokemonTwo, pokemonTree) // fixo de 20 caracteres na esquerda  tive que fazer assim pq padEnd(20) nao rolou
            )
        }

        print("Digite o nome do Pokémon: ")
        val escolha = scanner.nextLine().trim()

        val pokemonEncontrado = pokemons.find { it.nome.equals(escolha, ignoreCase = true) } // pra ver se o nome escrito é EQUALS ======
            ?: escolha.toIntOrNull()?.let { numero ->
                pokemons.getOrNull(numero - 1)
            }

        if (pokemonEncontrado != null) { // se for diferente de nulo add na lista (pokemonEncontrado)
            pokemonsEscolhidos.add(pokemonEncontrado)
            println("${Verde}${pokemonEncontrado.nome}${Reset} foi adicionado ao time de ${Ciano}$treinador!${Reset}")
        } else {
            println("Pokémon não encontrado! Tente novamente.")
        }
    }

    return pokemonsEscolhidos
}


