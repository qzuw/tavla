/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutavla.tavla.ui;

import java.util.*;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirto;
import tutavla.tavla.logiikka.Sovelluslogiikka;

/**
 * Tekstikäyttöliittymä.
 *
 * @author ttuotila
 */
public class Tekstikayttoliittyma implements Kayttoliittyma {

    private Sovelluslogiikka svl;
    private Scanner lukija;

    public Tekstikayttoliittyma() {
        lukija = new Scanner(System.in);
        svl = new Sovelluslogiikka();
    }

    @Override
    public void kaynnista() {

        int pelaajamaara = kysyPelaajamaara();

        kysyPelaajanimet(pelaajamaara);

        System.out.println("Paina enter heittääksesi noppaa");
        lukija.nextLine();
        svl.heitaNopat();

        arvoAloittaja();

        valitseVari();

        System.out.println("Peli alkaa");
        System.out.println("");

        while (true) {
            pelaaKierros();
            if (svl.onkoJokuVoittanut()) {
                break;
            }
        }
        System.out.println(svl.kukaVoitti() + " voitti!");
    }

    private void pelaaKierros() {
        System.out.println("Kierros alkaa");
        System.out.println(lautaToString());
        System.out.println("");

        for (int i = 0; i<2; i++) {

            if (svl.onkoJokuVoittanut()) {
                System.out.println("Peli on päättynyt!");
                break;
            }

            Pelaaja pelaaja = svl.haeVuorossaOlevaPelaaja();
            
            System.out.println("Pelaajan " + pelaaja + " vuoro");
            System.out.println("Pelaajan siirrot ovat " + svl.haeSiirrot());

            if (pelaaja.onkoIhminen()) {
                pelaajaSiirtaa(pelaaja);
            } else {
                tietokoneSiirtaa(pelaaja);
            }

            svl.heitaNopat();
            System.out.println(lautaToString());
            svl.vaihdaVuoroa();

        }
    }

    private void pelaajaSiirtaa(Pelaaja pelaaja) {
        ArrayList<Integer> siirrot;
        while (true) {
            siirrot = svl.haeSiirrot();
            if (siirrot.isEmpty() || svl.eiVoiSiirtaa()) {
                break;
            }
            int mista = -1;
            int minne = -1;
            if (!svl.eiVoiSiirtaa()) {
                System.out.println("Käytettävissä olevat siirrot:");
                System.out.println(siirrot);
                System.out.println(pelaaja + " voi siirtää nappuloita ruuduista:");
                System.out.println(svl.pelaajaVoiSiirtaaRuuduista());
                try {
                    System.out.println("Mistä siirretään nappula?");
                    mista = Integer.parseInt(lukija.nextLine());
                    if (!svl.pelaajaVoiSiirtaaRuuduista().contains(mista)) {
                        mista = -1;
                    }
                } catch (Exception e) {

                }
                if (mista > -1) {
                    svl.asetaLahtoruutu(mista);
                    System.out.println("Ruudusta " + mista + "voi siirtää ruutuihin:");
                    ArrayList<Integer> kohderuudut = svl.pelaajaVoiSiirtaaRuutuihin();

                    System.out.println(kohderuudut);
                    System.out.println("Minne nappula siirretään?");
                    try {
                        minne = Integer.parseInt(lukija.nextLine());
                        if (!svl.pelaajaVoiSiirtaaRuutuihin().contains(minne)) {
                            minne = -1;
                        }
                    } catch (Exception e) {

                    }
                }

                if (minne > -1) {
                    if (svl.haePelilogiikka().ruutuunVoiSiirtya(minne, pelaaja)) {
                        System.out.println("Siirretään nappula ruudusta " + mista + " ruutuun " + minne);
                        svl.siirraNappulaa(minne);
                    } else {
                        System.out.println("Siirtoa ei voi tehdä");
                    }
                }
            } else {
                System.out.println(pelaaja + " ei voi siirtää mitään!");
                break;
            }

            System.out.println("");
            System.out.println(lautaToString());
        }
    }

    private void tietokoneSiirtaa(Pelaaja pelaaja) {
        ArrayList<Integer> siirrot;
        while (true) {
            siirrot = svl.haeSiirrot();
            if (siirrot.isEmpty() || svl.eiVoiSiirtaa()) {
                break;
            }
            Siirto siirto = svl.pelaaTietokone();
            if (siirto.eiVoiSiirtaa()) {
                System.out.println(pelaaja + " ei voi siirtää!");
            } else {
                System.out.println(pelaaja.haeNimi() + " siirtää ruudusta " + siirto.haeLahto() + " ruutuun " + siirto.haeMaali());
                if (siirto.vastustajanNappulaSyoty()) {
                    System.out.println(pelaaja.haeNimi() + " syö vastustajan nappulan!");
                }
            }
        }
    }

    private void valitseVari() {
        ArrayList<Pelaaja> pelaajat = svl.haeSiirtojarjestys();

        boolean variValittu = false;

        while (!variValittu) {
            Pelaaja pelaaja1 = pelaajat.get(0);
            Pelaaja pelaaja2 = pelaajat.get(1);

            if (pelaaja1.onkoIhminen()) {
                System.out.println(pelaaja1 + ", haluatko valkoiset vai mustat nappulat?");
                String nappulat = lukija.nextLine().toLowerCase();

                switch (nappulat) {
                    case "musta":
                        svl.asetaPelaajaMustaksi(pelaaja1);
                        variValittu = true;
                        break;
                    case "m":
                        svl.asetaPelaajaMustaksi(pelaaja1);
                        variValittu = true;
                        break;
                    case "mustat":
                        svl.asetaPelaajaMustaksi(pelaaja1);
                        variValittu = true;
                        break;
                    case "valkoiset":
                        svl.asetaPelaajaMustaksi(pelaaja2);
                        variValittu = true;
                        break;
                    case "v":
                        svl.asetaPelaajaMustaksi(pelaaja2);
                        variValittu = true;
                        break;
                    case "valkoinen":
                        svl.asetaPelaajaMustaksi(pelaaja2);
                        variValittu = true;
                        break;
                    case "valkea":
                        svl.asetaPelaajaMustaksi(pelaaja2);
                        variValittu = true;
                        break;
                    case "valkeat":
                        svl.asetaPelaajaMustaksi(pelaaja2);
                        variValittu = true;
                        break;

                }
            } else {
                svl.tietokoneValitseeVarin();
                System.out.print(pelaaja1 + " valitsee ");
                if (pelaaja1.onkoMusta()) {
                    System.out.print("mustat nappulat!");
                } else {
                    System.out.print("valkoiset nappulat!");
                }
                variValittu = true;
            }
        }

        System.out.println("");
    }

    private void arvoAloittaja() {
        ArrayList<Integer> siirrot;
        ArrayList<Pelaaja> pelaajat;
        while (true) {
            siirrot = svl.haeSiirrot();
            pelaajat = svl.haeSiirtojarjestys();
            for (int i = 0; i < 2; i++) {
                System.out.println(pelaajat.get(i) + "  heitti " + siirrot.get(i));
            }
            if (siirrot.size() > 2) {
                System.out.println("");
                System.out.println("Molemmat pelaajat heittivät saman luvun, aloittajaa ei voi päättää!");
                System.out.println("Paina enter heittääksesi noppaa uudestaan");
                lukija.nextLine();
//                svl.poistaSiirrot();
                svl.heitaNopat();
            } else {
                System.out.println("");
                if (siirrot.get(0) > siirrot.get(1)) {
                    System.out.println(pelaajat.get(0) + " heitti suuremman luvun ja aloittaa pelin!");
                    svl.pelaajaSiirtaaEnsin(true, 0);
                } else {
                    System.out.println(pelaajat.get(1) + " heitti suuremman luvun ja aloittaa pelin!");
                    svl.pelaajaSiirtaaEnsin(true, 1);
                }
                break;
            }
        }
        System.out.println("");
    }

    private void kysyPelaajanimet(int pelaajamaara) {
        for (int i = 0; i < pelaajamaara; i++) {
            System.out.println("Pelaajan " + (i + 1) + " nimi?");
            String nimi = lukija.nextLine();
            // whitespacen voisi karsia mutta sitten tarvitaan silmukka
            svl.maaritaPelaaja(i, nimi);
            // tai
//            svl.haeSiirtojarjestys().get(i).asetaNimi(nimi);
//            svl.haeSiirtojarjestys().get(i).asetaIhmiseksi(true);
        }
    }

    private int kysyPelaajamaara() {
        int pelaajamaara = -1;
        while (pelaajamaara < 0 || pelaajamaara > 2) {
            System.out.println("Montako pelaajaa (0-2)?");
            try {
                pelaajamaara = Integer.parseInt(lukija.nextLine());
            } catch (Exception e) {
            }
        }
        return pelaajamaara;
    }

    public String lautaToString() {
        String s = "";
        for (int i = 13; i <= 24; i++) {
            s += i + "  ";
        }

        s += "\n";

        for (int i = 13; i <= 24; i++) {
            s += svl.pelitilanne().haeRuutu(i) + " ";
        }

        s += "\n";
        s += "\n";

        for (int i = 12; i >= 1; i--) {
            s += svl.pelitilanne().haeRuutu(i) + " ";
        }
        s += "\n";
        for (int i = 12; i >= 1; i--) {
            s += i + "  ";
            if (i < 10) {
                s += " ";
            }
        }
        s += "\n";

        return s;
    }

}
