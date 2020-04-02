import CardType.*
import Suit.*
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class GameKtTest {
    @Test
    fun `if all players have equivalent hands then they all win`() {
        val river = listOf(Card(TWO, HEARTS), Card(THREE, CLUBS), Card(FOUR, SPADES), Card(FIVE, DIAMONDS) ,Card(SIX, HEARTS))
        val player1 = Player("Andy", listOf(Card(SEVEN, CLUBS), Card(EIGHT, CLUBS)))
        val player2 = Player("Bob", listOf(Card(SEVEN, DIAMONDS), Card(EIGHT, DIAMONDS)))
        val player3 = Player("Cheryl", listOf(Card(SEVEN, SPADES), Card(EIGHT, SPADES)))

        assertThat(decideWinner(river, listOf(player1, player2, player3))).isEqualTo(listOf(player1, player2, player3))
    }
}