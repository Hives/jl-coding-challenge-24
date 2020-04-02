import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class AddKtTest {
    @Test
    fun `Example test`() {
        assertThat(add(1, 2)).isEqualTo(3)
    }

    @Test
    fun `Example test 2`() {
        assertThat(add(10, 20)).isEqualTo(30)
    }
}