package com.example.logointerpreterbeta.components.CodeEditor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


object NearestWordFinder{
    var nearestSpacePosition by mutableStateOf(-1)
    var nearestSpacePositionToTheRight by  mutableStateOf(-1)
    var nearestWord by  mutableStateOf("")
    fun find(text: String, cursorPosition: Int) {
        // Znajdź najbliższą spację w lewo od pozycji kursora
        nearestSpacePosition = findNearestSpaceToLeft(text, cursorPosition)
        nearestSpacePositionToTheRight = findNearestSpaceToRight(text, cursorPosition)
        if(nearestSpacePositionToTheRight==-1) {
            nearestSpacePositionToTheRight=text.length
        }
        nearestWord = NearestWordToLeft(
            text,
            nearestSpacePositionToTheRight,
            nearestSpacePosition
        )
    }
    // Funkcja do znajdowania najbliższej spacji w lewo od pozycji kursora
    fun findNearestSpaceToLeft(text: String, cursorPosition: Int): Int {
        if (cursorPosition > 0) {
            for (i in cursorPosition - 1 downTo 0) {
                if (text[i] == ' ') {
                    return i // Zwraca indeks pierwszej napotkanej spacji
                }
            }
        }
        return -1 // Zwraca -1, jeśli nie znaleziono spacji
    }
    // Funkcja do znajdowania najbliższej spacji w lewo od pozycji kursora
    fun findNearestSpaceToRight(text: String, cursorPosition: Int): Int {
        if (cursorPosition > 0) {
            for (i in cursorPosition-1 until text.length) {
                if (text[i] == ' ') {
                    return i // Zwraca indeks pierwszej napotkanej spacji
                }
            }
        }
        return -1 // Zwraca -1, jeśli nie znaleziono spacji
    }
    // Funkcja do znajdowania najbliższej spacji w lewo od pozycji kursora
    fun NearestWordToLeft(text: String, cursorPosition: Int,wordStart:Int): String {
        if (cursorPosition > 0 && wordStart>0) {
            return text.substring(wordStart,cursorPosition)
        }
        return text // Zwraca -1, jeśli nie znaleziono spacji
    }
}
