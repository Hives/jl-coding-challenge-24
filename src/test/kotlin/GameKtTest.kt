import CardType.*
import Suit.*
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class GameKtTest {
    @Test
    fun `if all players have equivalent hands then they all win`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(THREE, CLUBS), Card(FOUR, SPADES), Card(FIVE, DIAMONDS), Card(SIX, HEARTS))
        val player1 = Player("Andy", Card(SEVEN, CLUBS), Card(EIGHT, CLUBS))
        val player2 = Player("Bob", Card(SEVEN, DIAMONDS), Card(EIGHT, DIAMONDS))
        val player3 = Player("Cheryl", Card(SEVEN, SPADES), Card(EIGHT, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1, player2, player3))
    }

    @Test
    fun `if all players score highest card, player with highest card wins`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(TWO, CLUBS), Card(TWO, SPADES), Card(TWO, DIAMONDS), Card(THREE, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(SIX, CLUBS))
        val player2 = Player("Bob", Card(FIVE, DIAMONDS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player2))
    }

    @Test
    fun `if river card is highest card, player with next highest card wins,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(TWO, CLUBS), Card(TWO, SPADES), Card(TWO, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(SIX, CLUBS))
        val player2 = Player("Bob", Card(FIVE, DIAMONDS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player2))
    }

    @Test
    fun `player with a pair beats highest card,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(TWO, CLUBS), Card(TWO, SPADES), Card(TWO, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(FIVE, HEARTS))
        val player2 = Player("Bob", Card(FIVE, DIAMONDS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1))
    }
}