package com.example.logointerpreterbeta.components.codeEditor.codeSuggestions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
object SuggestionList {
        var debounceJob: Job? = null
        var lastVariable: String = ""
        var suggestions = mutableListOf(
            "fd",
            "bk",
            "rt",
            "lt",
            "cs",
            "pu",
            "pd",
            "setx",
            "sety",
            "setts",
            "setpensize",
            "setbg",
            "fill",
            "setps",
            "arc",
            "setxy",
            "setpos",
            "make",
            "setcornerrounding",
            "setcr",
            "setpc",
            "setpencolor",
        )
        var variables = mutableListOf<String>()
        fun addSuggestion(suggestion: String) {
            if(suggestion.contains(lastVariable)) {
                debounceJob?.cancel()  // Anulowanie poprzedniego joba, jeśli istnieje
                debounceJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(500)  // Czekaj 300 ms
                    suggestions.add(":" + suggestion.substring(1))
                    variables.add(suggestion)
                }
            }
            else{
                suggestions.add(":" + suggestion.substring(1))
                variables.add(suggestion)
            }
            lastVariable = suggestion
            
        }
        fun checkForVariables(text:String){
            val matchingVariables = variables.filter { variable -> text.contains(variable) }
            matchingVariables.forEach { matchingVariable ->
                removeSuggestionIfExists(matchingVariable)
            }
        }
        fun removeSuggestionIfExists(variable: String) {
            if (variables.contains(variable)) {
                suggestions.remove(":" + variable.substring(1))
                variables.remove(variable)
            }
        }
}