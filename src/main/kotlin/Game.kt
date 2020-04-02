fun decideWinner(river: List<Card>, players: List<Player>): List<Player> {

    val playersWithPairs = players.filter { player -> player.hand.isPair() }
    if (playersWithPairs.count() > 0) {
        return playersWithPairs
    }

    val highestValue = players.flatMap { it.hand }.maxBy { it.type.value }!!.type.value // !! == :(
    return players.filter { player ->
        val values = player.hand.map { card -> card.type.value }
        values.contains(highestValue)
    }
}

fun List<Card>.isPair() = this[0].type.value == this[1].type.value

enum class Suit(name: String) {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades")
}

sealed class CardType(val name: String, val value: Int) {
    object TWO: CardType("Two", 2)
    object THREE: CardType("Three", 3)
    object FOUR: CardType("Four", 4)
    object FIVE: CardType("Five", 5)
    object SIX: CardType("Six", 6)
    object SEVEN: CardType("Seven", 7)
    object EIGHT: CardType("Eight", 8)
    object NINE: CardType("Nine", 9)
    object TEN: CardType("Ten", 10)
    object JACK: CardType("Jack", 11)
    object QUEEN: CardType("Queen", 12)
    object KING: CardType("King", 13)
    object ACE: CardType("Ace", 14)
}

data class Card(val type: CardType, val suit: Suit)

data class Player(val name: String, val card1: Card, val card2: Card) {
    val hand = listOf(card1, card2)
}