# Tavla

**Aihe:** [Tavla](https://en.wikipedia.org/wiki/Tavla) on vanha lautapeli, jonka tunnetumpi variantti tunnetaan länsimaissa nimellä [Backgammon](https://en.wikipedia.org/wiki/Backgammon). Pelissä kaksi pelaajaa pyrkii kuljettamaan omat pelinappulansa pelilaudan halki noppien määräämillä siirroilla omalle kotialueelleen ja sen jälkeen sieltä ulos.

**Käyttäjät:** Peliä pelaavat pelaajat

**Käyttäjän toiminnot:**

- Uuden pelin käynnistäminen
- Pelin pelaaminen
  - Noppien heittäminen
    - Jos molemmat nopat antavat saman arvon, siirtojen lukumäärä tuplataan (Esim nopat: 4 + 4 => siirrot: 4 + 4 + 4 + 4)
  - Syödyn pelinappulan siirtäminen laudalle
    - Onnistuu jos pelilaudalla on vapaa alue jolle nappulan voi siirtää yhden nopan luvulla
  - Vastustajan pelinappulan syöminen ja/tai oman nappulan siirtäminen
    - Syöminen onnistuu jos omia syötyjä nappuloita ei ole enempää ja pelilaudalla on yksinäinen vastustajan nappula jonka päälle voi siirtää oman nappulan yhden nopan arvolla.
    - Siirtäminen onnistuu jos omia syötyjä nappuloita ei ole enempää ja pelilaudalla on vapaita ruutuja joille jonkun oman nappulan voi siirtää yhden nopan määräämällä siirrolla.
  - Omien nappuloiden siirtäminen pois laudalta
    - Onnistuu jos kaikki omat nappulat ovat kotialueella

Peli loppuu kun toinen pelaaja on siirtänyt kaikki omat nappulansa ulos laudalta.
