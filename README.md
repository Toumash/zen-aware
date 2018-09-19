# ZenAware
This software is intended to collect mood data throughout the month and make you aware of the trends in your head.
# Zen Master

## Zbieranie danych

3 razy dziennie  
rano 3 zmienne  (samopoczucie x/10 ilosc godzin snu + czy specjalne wydarzenie)  
w południe i wieczorem po jednej

## 3 ekrany

### Logowanie/Rejestracja

email/haslo opcjonalne

Rano

### Ekran wypełniania danych

rano

- suwak do ustalenia poziomu zen
- ilosc snu

południe

- zen

wieczór

- zen
- ważne wydarzenie

### Features

### TODO

1. Projekt graficzny aplikacji
1. Sposób przetwarzania danych
1. Prezentacja poziomu zen

### Architektura

        XXXXX
        XXX     XX                                                                     +----------+
    XXX        XXXXX                    Przetworzone dane                            |          |
    XX               XX                                                                |          |
    XX                X       +----------------------------------------------->        |          |
    X                X                                                                |          |
    X                X                                                                |          |
    XXX         XXX  X        <----------------------------------------------+        |          |
    XXXXXXXXXX  XXXX                                                                |          |
                                        Kolekcja danych                             |          |
        VPS                                                                          |          |
                                                                                    +----------+
