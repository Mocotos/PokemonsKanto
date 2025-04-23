

val tiposEfetivos: Map<String, List<String>> = mapOf(
    "Normal" to emptyList(),
    "Fire" to listOf("Grass", "Ice", "Bug", "Steel"),
    "Water" to listOf("Fire", "Ground", "Rock"),
    "Electric" to listOf("Water", "Flying"),
    "Grass" to listOf("Water", "Ground", "Rock"),
    "Ice" to listOf("Grass", "Ground", "Flying", "Dragon"),
    "Fighting" to listOf("Normal", "Ice", "Rock", "Dark", "Steel"),
    "Poison" to listOf("Grass", "Fairy"),
    "Ground" to listOf("Fire", "Electric", "Poison", "Rock", "Steel"),
    "Flying" to listOf("Grass", "Fighting", "Bug"),
    "Psychic" to listOf("Fighting", "Poison"),
    "Bug" to listOf("Grass", "Psychic", "Dark"),
    "Rock" to listOf("Fire", "Ice", "Flying", "Bug"),
    "Ghost" to listOf("Psychic", "Ghost"),
    "Dragon" to listOf("Dragon"),
    "Dark" to listOf("Psychic", "Ghost"),
    "Steel" to listOf("Ice", "Rock", "Fairy"),
    "Fairy" to listOf("Fighting", "Dragon", "Dark"),
    "Stellar" to listOf("Terastallized")
)
    