package com.example.logointerpreterbeta.components.codeEditor.codeSuggestions

object SuggestionList {
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
        fun addSuggestion(suggestion: String) {
            suggestions.add(suggestion)
        }
        fun removeSuggestionIfExists(suggestion: String) {
            if (suggestions.contains(suggestion)) {
                suggestions.remove(suggestion)
            }
        }
}