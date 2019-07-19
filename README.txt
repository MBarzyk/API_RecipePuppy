Polecenie:
Tworzymy aplikację do wyszukiwania przepisów. Api wymaga podania składników, dlatego musisz je wczytać ze scannera.
Aplikacja powinna pozwalać dopisywać składniki do listy. Najlepiej zrobić pętlę w której użytkownik podaje tak długo jak chce.
Najlepiej by użytkownik dopisywał składniki tak długo aż wpisze "koniec".
Wpisanie słowa koniec powinno zatrzymać wczytywanie składników i powinno być zadane pytanie:
"Czy skończyłeś wpisywać składniki?"
"Tak"
"Szukam przepisów..."
"Twoje przepisy to: ..." (i tu je wypisujemy)
Po tym jak użytkownik skończy je wpisywać pobierz z API przepisy i wypisz je na ekran.
Możesz jeden pod drugim.
**Do aplikacji będzie rozwinięcie. Najpierw zróbmy podstawową część.


** ROZSZERZENIE

Po załadowaniu wyników (pierwszej strony) wylistuj je użytkownikowi.
Po wylistowaniu zadaj mu pytanie:
        - "Czy widzisz przepis który Ciebie interesuje?"

Jeśli użytkownik odpowie:
        - "Nie" - to pobierz i wyświetl następną strone
        - "Tak" - to zapytaj użytkownika który przepis go interesuje. Np:
(link http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3)
"Który przepis Cię interesuje:"
1. Vegetable-Pasta Oven Omelet
2. Roasted Pepper and Bacon Omelet
3. Broccoli Oven Omelet Recipe
4. Eggplant Omelet with Coriander and Caraway
5. Broccoli and Cheese Omelet
..... (itd.)
Użytkownik odpowiada:
"4"
i wtedy otwórz u użytkownika w przeglądarce nowe okno i załaduj pole "href" przepisu (czyli link do niego).
Żeby to zrobić, skorzystaj z funkcji:
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI({TUTAJ_WPISZ_POLE_HREF}));
        }
    Przykład użycia:
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("http://www.bigoven.com/recipe/broccoli-and-cheese-omelet/45151"));
        }