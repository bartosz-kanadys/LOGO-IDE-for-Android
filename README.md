# Logo IDE for Android

## Opis projektu

**Logo IDE for Android** to zintegrowane środowisko programistyczne (IDE) dla języka Logo, przeznaczone na urządzenia z systemem Android. Projekt ten jest częścią pracy inżynierskiej i ma na celu dostarczenie wygodnego narzędzia do nauki i programowania w języku Logo na urządzeniach mobilnych.

## Główne funkcje

- **Edytor kodu** z podświetlaniem składni i autouzupełnianiem.
- **Interpreter Logo** umożliwiający wykonywanie kodu i rysowanie grafiki.
- **Obsługa plików**: zapisywanie i wczytywanie projektów.
- **Interaktywne narzędzia graficzne**, w tym panel rysowania i wybór kolorów.
- **Wsparcie dla procedur** i dynamiczne zarządzanie zmiennymi.
- **Integracja z pamięcią wewnętrzną** dla lepszego zarządzania projektami.
- **Eksportowanie rysunków do plików graficznych.**

## Technologie

Projekt został zrealizowany w języku Kotlin z wykorzystaniem Jetpack Compose do budowy interfejsu użytkownika. Do analizy składni języka Logo wykorzystano ANTLR4, a do zarządzania stanem aplikacji zastosowano architekturę MVVM.

**Wykorzystane technologie:**

- **Kotlin + Jetpack Compose** - nowoczesny interfejs użytkownika.
- **ANTLR4** - parser dla języka Logo.
- **ViewModel + StateFlow** - zarządzanie stanem aplikacji.
- **File API** - obsługa operacji na plikach.
