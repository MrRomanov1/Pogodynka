# Pogodynka
Aplikacja desktopowa do sprawdzania pogody stworzona przy wykorzystaniu:
- Java
- JavaFX
- SceneBuilder
- Open Weather Map API
- Maxmind GeoLite2 DB

## Funkcjonalność
- [x] Pobieranie przybliżonej lokalizacji użytkownika na podstawie zewnętrznego adresu IP
- [x] Autosugestia przy wpisywaniu lokalizacji
- [x] Wyświetlanie bieżącej pogody w dwóch miastach
- [x] Wyświetlanie prognozy na 7 kolejnych dni 

## Jak uruchomić
- pobieramy paczkę oraz wymienione biblioteki - w tym bazę GeoLite2 (baza powinna trafić do folderu resources)
- rejestrujemy się na https://openweathermap.org/api i pobieramy swój klucz
- zmieniamy nazwę pliku Config_TO_EDIT na Config i wprowadzamy tam klucz API