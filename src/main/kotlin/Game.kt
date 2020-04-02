fun decideWinner(river: List<Card>, players: List<Player>): List<Player> = players

enum class Suit(name: String) {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades")
}

enum class CardType(name: String, value: Int) {
    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four", 4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9),
    TEN("Ten", 10),
    JACK("Jack", 11),
    QUEEN("Queen", 12),
    KING("King", 13),
    ACE("Ace", 14)
}

data class Card(val type: CardType, val suit: Suit)

data class Player(val name: String, val hand: List<Card>)