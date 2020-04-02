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
    fun `if two players have equivalent hands then they win and others lose`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(THREE, CLUBS), Card(FOUR, SPADES), Card(FIVE, DIAMONDS), Card(SIX, HEARTS))
        val player1 = Player("Andy", Card(SEVEN, CLUBS), Card(NINE, CLUBS))
        val player2 = Player("Bob", Card(SEVEN, DIAMONDS), Card(EIGHT, DIAMONDS))
        val player3 = Player("Cheryl", Card(SEVEN, SPADES), Card(NINE, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1, player3))
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

    @Test
    fun `player with higher pair beats lower pair,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(TWO, CLUBS), Card(TWO, SPADES), Card(TWO, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(FIVE, HEARTS))
        val player2 = Player("Bob", Card(SIX, CLUBS), Card(SIX, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player2))
    }

    @Test
    fun `player with pair beats high value card,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(TWO, CLUBS), Card(TWO, SPADES), Card(TEN, DIAMONDS), Card(EIGHT, HEARTS))
        val player1 = Player("Andy", Card(EIGHT, CLUBS), Card(QUEEN, HEARTS))
        val player2 = Player("Bob", Card(TEN, CLUBS), Card(JACK, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player2))
    }

    @Test
    fun `player with pair from river beats high card,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(FOUR, CLUBS), Card(FOUR, SPADES), Card(FOUR, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(TWO, SPADES))
        val player2 = Player("Bob", Card(SIX, CLUBS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(SIX, SPADES))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1))
    }

    @Test
    fun `players with pair, and equivalent kickers from river beats high card,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(FOUR, CLUBS), Card(FOUR, SPADES), Card(FOUR, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(FIVE, CLUBS), Card(TWO, SPADES))
        val player2 = Player("Bob", Card(SIX, CLUBS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(TWO, CLUBS))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1, player3))
    }

    @Test
    fun `player with pair and highest kicker beats  player with same pair and lower kicker,`() {
        val river =
            listOf(Card(TWO, HEARTS), Card(FOUR, CLUBS), Card(FOUR, SPADES), Card(FOUR, DIAMONDS), Card(TEN, HEARTS))
        val player1 = Player("Andy", Card(SIX, CLUBS), Card(TWO, SPADES))
        val player2 = Player("Bob", Card(SIX, CLUBS), Card(SEVEN, DIAMONDS))
        val player3 = Player("Cheryl", Card(FIVE, SPADES), Card(TWO, CLUBS))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1))
    }
}