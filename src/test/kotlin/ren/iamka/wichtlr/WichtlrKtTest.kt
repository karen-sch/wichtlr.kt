package ren.iamka.wichtlr

import org.junit.Assert.*
import org.junit.Test

class WichtlrKtTest {

    @Test
    fun `all secret santas are matched to a different santa`() {

        val santas = setOf("bob@gmail.com",
                "alice@yahoo.com",
                "carol@hotmail.com",
                "dave@gmx.de",
                "eve@applemail.com",
                "frank@gmail.com",
                "ted@web.de")

        repeat(100){_ ->
            val matches = matchSecretSantas(santas)
            assertEquals(santas.size, matches.size)
            assertTrue(matches.map { it.first }.containsAll(santas))
            assertTrue(matches.map { it.second }.containsAll(santas))
            for (match in matches){
                assertNotEquals(match.first, match.second)
            }

        }
    }
}