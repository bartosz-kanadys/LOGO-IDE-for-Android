package com.example.logointerpreterbeta.components.codeEditor.textFunctions

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object NearestWordFinder{
    var nearestSpacePositionToLeft by mutableIntStateOf(-1)
    var nearestSpacePositionToRight by  mutableIntStateOf(-1)
    var nearestWord by  mutableStateOf("")
    fun find(text: String, cursorPosition: Int):String {
        // Znajdź najbliższą spację w lewo od pozycji kursora
        nearestSpacePositionToLeft = findNearestSpaceToLeft(text, cursorPosition)
        nearestSpacePositionToRight = findNearestSpaceToRight(text, cursorPosition)
        if(nearestSpacePositionToRight ==-1) {
            nearestSpacePositionToRight =text.length
        }
        if(nearestSpacePositionToLeft ==-1) {
            nearestSpacePositionToLeft =0
        }
        nearestWord = NearestWordToLeft(
            text,
            nearestSpacePositionToRight,
            nearestSpacePositionToLeft
        )
        return nearestWord
    }
    // Funkcja do znajdowania najbliższej spacji w lewo od pozycji kursora
    fun findNearestSpaceToLeft(text: String, cursorPosition: Int): Int {
        if (cursorPosition > 0) {//
            for (i in cursorPosition - 1 downTo 0) {
                if (text[i] == ' ' || text[i] == '\n') {//
                    return i + 1 // Zwraca indeks pierwszej napotkanej spacji
                }

            }
        }
        return -1 // Zwraca -1, jeśli nie znaleziono spacji
    }
    // Funkcja do znajdowania najbliższej spacji w prawo od pozycji kursora
    fun findNearestSpaceToRight(text: String, cursorPosition: Int): Int {
        if (cursorPosition > 0) {
            for (i in cursorPosition-1 until text.length) {
                if (text[i] == ' ' || text[i] == '\n') {
                    return i // Zwraca indeks pierwszej napotkanej spacji
                }
            }
        }
        return -1 // Zwraca -1, jeśli nie znaleziono spacji
    }
    // Funkcja do znajdowania najbliższej spacji w lewo od pozycji kursora
    fun NearestWordToLeft(text: String, wordEnd: Int,wordStart:Int): String {
        //Log.i("WordStart", wordStart.toString())
        //Log.i("WordEnd", wordEnd.toString())
        if (wordEnd > 0 && wordStart>=0 && wordStart<wordEnd) {
           // Log.i("Word",text.substring(wordStart,wordEnd))
            return text.substring(wordStart,wordEnd)
        }
        return "" // Zwraca -1, jeśli nie znaleziono spacji
    }
}
