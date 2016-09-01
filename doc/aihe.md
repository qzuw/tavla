# Tavla

**Aihe:** [Tavla](https://en.wikipedia.org/wiki/Tavla) on vanha lautapeli, jonka tunnetumpi variantti tunnetaan länsimaissa nimellä [Backgammon](https://en.wikipedia.org/wiki/Backgammon). Pelissä kaksi pelaajaa pyrkii kuljettamaan omat pelinappulansa pelilaudan halki noppien määräämillä siirroilla omalle kotialueelleen ja sen jälkeen sieltä ulos.

Pelaajat heittävät vuorollaan kahta noppaa ja sen jälkeen siirtävät jotain tai joitain omia nappuloitansa eteenpäin noppien määräämän etäisyyden. Nappulaa ei saa siirtää ruutuun jossa on kaksi tai useampi vastustajan nappula. Nappulaa on aina siirrettävä tasan yksittäisen nopan määräämä etäisyys, minkä jälkeen sitä voi siirtää uudestaan toisen nopan määräämän etäisyyden. Jos molemmat nopat ovat saman arvoiset, siirtojen määrä tuplataan. Jos nappulan siirtää yksittäisen vastustajan nappulan päällä, tulee vastustajan nappula syödyksi ja joutuu palaamaan vastustajan vuorolla pelilaudalle ennen kuin vastustaja voi siirtää muita nappuloita.

Kun pelaaja on saanut siirrettyä kaikki nappulansa omalle kotialueelleen, voi hän alkaa siirtämään niitä ulos pelilaudalta. Peli päättyy kun toinen pelaajista on saanut kaikki nappulansa pois laudalta.

**Käyttäjät:** Peliä pelaava(t) pelaaja(t)

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


## Luokkakaavio

![Luokkakaavio](https://github.com/qzuw/tavla/blob/master/doc/tavla-luokkakaavio.png)

## Rakennekuvaus

Käynnistettäessä ohjelma luodaan ensin käyttöliittymä, joka puolestaan luo tarvitsemansa käyttöliittymän osat sekä sovelluslogiikan. Sovelluslogiikka toimii varsinaisen pelilogiikan ja käyttöliittymän välissä ja huolehtii pelin kulkuun (vuorot, pelaajat ja pelaajien järjestys) kuuluvista asioista jotka eivät ole selkeästi pelin toimintaan pelilaudalla liittyviä. Sovelluslogiikka puolestaan luo Siirrot-olion hallinnoimaan pelinoppia ja niiden luvuista saatavia siirtoja, tekoälyn joka pelaa niiden pelaajien siirrot joita ei ohjaa ihmispelaaja sekä pelilogiikan joka huolehtii nappuloiden siirtelystä pelilaudalla. Pelilogiikka luo itselleen pelilaudan, joka puolestaan luo omat ruutunsa. Pelilogiikka luo myös pelinappulat joita liikutellaan pelilaudalla.

GraafinenKayttoliittyma luo itselleen piirtoalustan pelilaudan piirtämistä varten sekä kaksi muuta ikkunaa, nämä ovat tietojen kyselyä varten sekä ilmoitusasioita varten joihin tarvitaan pelaajan kuittaus. Näiden lisäksi GraafinenKayttoliittyma luo GUILogiikka-olion ikkunoiden ja pelin kulun hallintaa varten. HiirenKuuntelija, KyselyKuuntelija ja VuoroKuuntelija kuuntelevat kukin omaa ikkunaansa ja kommunikoivat GUILogiikan kanssa ja kautta.

## Sekvenssikaaviot

![Sekvenssikaavio siirto](https://github.com/qzuw/tavla/blob/master/doc/siirto-sekvenssik.png)

Ensimmäinen sekvenssikaavio kuvaa käyttöliittymän ja eri logiikoiden vuorovaikutusta.

![Sekvenssikaavio nopat](https://github.com/qzuw/tavla/blob/master/doc/heitaNopat-sekvenssik.png)

Toinen sekvenssikaavio kuvaa noppien heittämistä. GetArvo()n paluuarvoa ei ole merkitty koska Siirrot ottaa sen talteen mutta ei tässä yhteydessä palauta sitä edelleen.
