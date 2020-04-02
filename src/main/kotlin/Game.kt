fun decideWinner(river: List<Card>, players: List<Player>): List<Player> {

    val playersWithPairs =
        players.filter { it.hasPair(river) }
                .map { player -> Pair(player, player.hand.getValueOfPair(river)) }

    if (playersWithPairs.count() > 0) {
        val highestValue =
            playersWithPairs.maxBy { (_, value) -> value }!!.let { (_, value) -> value }
        return playersWithPairs.filter { (_, value) -> value == highestValue }.map { it.first }
    }

    return filterForHighestCard(river, players)
}


fun filterForHighestCard(river: List<Card>, players: List<Player>): List<Player> {
    val highestValue = players.flatMap { it.hand }.maxBy { it.type.value }!!.type.value // !! == :(
    return players.filter { player ->
        val values = player.hand.map { card -> card.type.value }
        values.contains(highestValue)
    }
}

fun List<Card>.getValueOfPair(river: List<Card>): Int {
    if (this[0].type.value == this[1].type.value) {
        return this[0].type.value
    }

    return this.first { handCard ->
        river.map { riverCard ->
            riverCard.type.value
        }
            .contains(handCard.type.value)
    }.type.value
}


fun isPair(card1: Card, card2: Card) = card1.type.value == card2.type.value

fun Player.hasPair(river: List<Card>) =
    (river + card2).any { isPair(card1, it) } || (river + card1).any { isPair(card2, it) }

enum class Suit(name: String) {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades")
}

sealed class CardType(val name: String, val value: Int) {
    object TWO : CardType("Two", 2)
    object THREE : CardType("Three", 3)
    object FOUR : CardType("Four", 4)
    object FIVE : CardType("Five", 5)
    object SIX : CardType("Six", 6)
    object SEVEN : CardType("Seven", 7)
    object EIGHT : CardType("Eight", 8)
    object NINE : CardType("Nine", 9)
    object TEN : CardType("Ten", 10)
    object JACK : CardType("Jack", 11)
    object QUEEN : CardType("Queen", 12)
    object KING : CardType("King", 13)
    object ACE : CardType("Ace", 14)
}

data class Card(val type: CardType, val suit: Suit)

data class Player(val name: String, val card1: Card, val card2: Card) {
    val hand = listOf(card1, card2)
}