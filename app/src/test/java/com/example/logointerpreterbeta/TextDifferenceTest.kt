package com.example.logointerpreterbeta
import androidx.compose.ui.text.AnnotatedString
import com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions.textDiffrence
import org.junit.Assert.assertEquals
import org.junit.Test

class TextDifferenceTest {

    @Test
    fun `test textDiffrence when oldText and newText are the same`() {
        val oldText = AnnotatedString("Hello world")
        val newText = "Hello world"

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        assertEquals(oldText, result)
    }
    @Test
    fun `test textDiffrence when oldText is empty`() {
        val oldText = AnnotatedString("")
        val newText = "Hello world"

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString(newText)
        assertEquals(expected, result)
    }
    @Test
    fun `test textDiffrence when newText is empty`() {
        val oldText = AnnotatedString("Hello world")
        val newText = ""

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString(newText)
        assertEquals(expected, result)
    }
    @Test
    fun `test textDiffrence when both newText and oldText are empty`() {
        val oldText = AnnotatedString("")
        val newText = ""

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString(newText)
        assertEquals(expected, result)
    }
    @Test
    fun `test textDiffrence when oldText and newText have a common prefix`() {
        val oldText = AnnotatedString("Hello world")
        val newText = "Hello there world"

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString("Hello" + newText.substring(5)) // "Hello there world"
        assertEquals(expected, result)
    }

    @Test
    fun `test textDiffrence when oldText and newText are completely different`() {
        val oldText = AnnotatedString("Goodbye world")
        val newText = "Hello world"

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString("Hello world")
        assertEquals(expected, result)
    }

    @Test
    fun `test textDiffrence with function modifying newText`() {
        val oldText = AnnotatedString("Hello world")
        val newText = "Hello world Kotlin"

        val result = textDiffrence(oldText, newText) { it ->
            AnnotatedString(it.uppercase()) //Zamiana nowego tekstu na duży
        }

        val expected = AnnotatedString("Hello" + newText.substring(5).uppercase()) // "Hello WORLD KOTLIN"
        assertEquals(expected, result)
    }
    @Test
    fun `test textDiffrence with function modifying newText with partially inputed word`() {
        val oldText = AnnotatedString("Hello wor")
        val newText = "Hello world"

        val result = textDiffrence(oldText, newText) { it ->
            AnnotatedString(it.uppercase()) //Zamiana nowego tekstu na duży
        }

        val expected = AnnotatedString("Hello" + newText.substring(5).uppercase()) // "Hello WORLD"
        assertEquals(expected, result)
    }

    @Test
    fun `test textDiffrence when oldText has a common prefix with newText and space is involved`() {
        val oldText = AnnotatedString("Hello Kotlin is awesome")
        val newText = "Hello Kotlin is amazing"

        val result = textDiffrence(oldText, newText) { it -> AnnotatedString(it) }

        val expected = AnnotatedString("Hello Kotlin is amazing")
        assertEquals(expected, result)
    }
}
