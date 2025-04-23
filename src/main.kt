/*OBS: 
Fiz em ingles por costume dos joguinhos e me confundir em portugues 
esse 'trim' eu nao peguei muito a ideia mas acredito que a função dele seria so eliminar espaços tipo um space antes de digitar algo 
o data class tem um problema eu nao entendi qual a parte de colocar  ele é o molde mas se nao tem pacote nao puxa olha eu nao entendi mas entendi aonde usar
flatMap basicamente ele junta listas o termo usado na comunidade é achatar mas basicamente ele desfragemente listar internas e faz outra
https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/map-of.html mapOf
https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/list-of.html lisOf
https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/trim.html trim
https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/flat-map.html flatmap






*/
fun main() {
        val verde = "\u001B[32m"// cor verde nos nomes do pokemons
        val reset = "\u001B[0m"// reseta a cor pra nao ficar tudo verde kkkkkkkk descobri tarde
    var continuarBuscando = true
    while (continuarBuscando) { // iniciei um loop pro usuario poder procurar o pokemon
        
        println("\nLista de Pokémons:")
        pokemons.forEach { pokemon ->
            val efetivos = pokemon.tipo.flatMap { tipo -> tiposEfetivos[tipo] ?: emptyList() } // flatMap serve para contruir e descontruir uma lista 
            println("Pokémon: ${verde}${pokemon.nome}${reset}") 
            println("Tipos: ${pokemon.tipo.joinToString(", ")}")
            println("Super efetivo contra: ${efetivos.distinct().joinToString(", ")}")
            println("----------------------------")
        }

        
        print("\nDeseja procurar um Pokémon específico? (sim/não): ")
        val resposta = readLine()?.trim()?.lowercase() // esse trim serve pra counterar espaços em branco

        if (resposta == "não" || resposta == "nao") { 
            continuarBuscando = false
            println("Rodou legal? da uma estrela")
        } else {
            print("\nDigite o nome do Pokémon que deseja procurar: ")
            val nomeBusca = readLine()?.trim()?.lowercase() // nao tava rodando pq o toLowerCase agora é lowercase

            
            val pokefind = pokemons.find { it.nome.lowercase() == nomeBusca } // verificação AAAAAAAAA

            // Mostrar o resultado da busca
            if (pokefind != null) {
                val efetivos = pokefind.tipo.flatMap { tipo -> tiposEfetivos[tipo] ?: emptyList() } // a mesma lambda de cima
                println("Pokémon encontrado: ${pokefind.nome}")
                println("Tipos: ${pokefind.tipo.joinToString(", ")}")
                println("Super efetivo contra: ${efetivos.distinct().joinToString(", ")}")
            } else {
                println("É só kanto irmão.")
            }
        }
    }
}
