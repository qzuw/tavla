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
        ArrayList<Pelaaja> pelaajat = svl.getSiirtojarjestys();

        System.out.println("Kierros alkaa");
        System.out.println(svl.pelitilanne());
        System.out.println("");

        for (Pelaaja pelaaja : pelaajat) {

            if (svl.onkoJokuVoittanut()) {
                System.out.println("Peli on päättynyt!");
                break;
            }

            System.out.println("Pelaajan " + pelaaja + " vuoro");
            System.out.println("Pelaajan siirrot ovat " + svl.haeSiirrot());

            if (pelaaja.isIhminen()) {
                pelaajaSiirtaa(pelaaja);
            } else {
                tietokoneSiirtaa(pelaaja);
            }

            svl.heitaNopat();
            System.out.println(svl.pelitilanne());

        }
    }

    private void pelaajaSiirtaa(Pelaaja pelaaja) {
        ArrayList<Integer> siirrot;
        while (true) {
            siirrot = svl.haeSiirrot();
            if (siirrot.isEmpty() || svl.eiVoiSiirtaa(pelaaja)) {
                break;
            }
            // yksi siirto

        }
    }

    private void tietokoneSiirtaa(Pelaaja pelaaja) {
        ArrayList<Integer> siirrot;
        while (true) {
            siirrot = svl.haeSiirrot();
            if (siirrot.isEmpty() || svl.eiVoiSiirtaa(pelaaja)) {
                break;
            }
            Siirto siirto = svl.pelaaTietokone(pelaaja);
            if (siirto.eiVoiSiirtaa()) {
                System.out.println(pelaaja + " ei voi siirtää!");
            } else {
                System.out.println(pelaaja.getNimi() + " siirtää ruudusta " + siirto.getLahto() + " ruutuun " + siirto.getMaali());
                if (siirto.vastustajanNappulaSyoty()) {
                    System.out.println(pelaaja.getNimi() + " syö vastustajan nappulan!");
                }
            }
        }
    }

    private void valitseVari() {
        ArrayList<Pelaaja> pelaajat = svl.getSiirtojarjestys();

        boolean variValittu = false;

        while (!variValittu) {
            Pelaaja pelaaja1 = pelaajat.get(0);
            Pelaaja pelaaja2 = pelaajat.get(1);

            if (pelaaja1.isIhminen()) {
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
                if (pelaaja1.isMusta()) {
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
            pelaajat = svl.getSiirtojarjestys();
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
//            svl.getSiirtojarjestys().get(i).setNimi(nimi);
//            svl.getSiirtojarjestys().get(i).setIhminen(true);
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

}
