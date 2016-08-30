package tutavla.tavla.logiikka;

import java.util.*;
import tutavla.tavla.domain.Lauta;
import tutavla.tavla.domain.Pelaaja;
import tutavla.tavla.domain.Siirrot;
import tutavla.tavla.domain.Siirto;

/**
 * Luokka sisältää logiikan pelilogiikan ja käyttöliittymän välissä.
 *
 * @author ttuotila
 */
public class Sovelluslogiikka {

    private Pelilogiikka pelilogiikka;
    private ArrayList<Pelaaja> siirtojarjestys;
    private RandomTekoaly tekoaly;
    private Siirrot siirrot;
    private int vuoro;
    private int lahtoruutu;

    /**
     * Luodaan Sovelluslogiikka-olio.
     */
    public Sovelluslogiikka() {
        siirtojarjestys = new ArrayList<>();

        Random random = new Random();
        tekoaly = new RandomTekoaly(random);
        siirrot = new Siirrot(random);
        vuoro = 0;
        lahtoruutu = -1;

        Pelaaja pelaaja1 = new Pelaaja();
        pelaaja1.setMusta(true);
        Pelaaja pelaaja2 = new Pelaaja();
        pelaaja2.setMusta(false);
        this.setSiirtojarjestys(pelaaja1, pelaaja2);

        pelilogiikka = new Pelilogiikka();

        pelilogiikka.alustaPelitilanne(siirtojarjestys.get(0), siirtojarjestys.get(1));
    }

    /**
     * Aseta pelaajan nimi ja ihmisyys.
     *
     * @param pelaaja pelaajan indeksi siirtojärjestyslistassa
     * @param nimi pelaajan nimi
     */
    public void maaritaPelaaja(int pelaaja, String nimi) {
        siirtojarjestys.get(pelaaja).setIhminen(true);
        siirtojarjestys.get(pelaaja).setNimi(nimi);
    }

    /**
     * Määritä siirtääkö annettu pelaaja ensimmäisenä.
     *
     * @param pelaajaEnsin true jos pelaaja siirtää ensin
     * @param pelaaja määritettävä pelaaja
     */
    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, Pelaaja pelaaja) {
        if (!(siirtojarjestys.get(0).equals(pelaaja) == pelaajaEnsin)) {
            siirtojarjestys.add(siirtojarjestys.get(0));
            siirtojarjestys.remove(0);
        }
    }

    /**
     * Määritä siirtääkö siirtojärjestyslistan annetussa indeksissä oleva
     * pelaaja ensin.
     *
     * @param pelaajaEnsin true jos pelaaja siirtää ensin
     * @param pelaajaIndex pelaajan indeksi siirtojärjestyslistassa
     */
    public void pelaajaSiirtaaEnsin(boolean pelaajaEnsin, int pelaajaIndex) {
        if ((pelaajaIndex != 0) == pelaajaEnsin) {
            siirtojarjestys.add(siirtojarjestys.get(0));
            siirtojarjestys.remove(0);
        }
    }

    /**
     * Aseta annetun pelaajan nappulat mustiksi.
     *
     * @param pelaaja pelaaja jonka nappulat määritellään mustiksi
     */
    public void asetaPelaajaMustaksi(Pelaaja pelaaja) {
        for (Pelaaja p : siirtojarjestys) {
            if (p.equals(pelaaja)) {
                p.setMusta(true);
            } else {
                p.setMusta(false);
            }
        }
    }

    private void setSiirtojarjestys(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        siirtojarjestys.clear();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
    }

    /**
     * Palauta lista pelaajista siirtojärjestyksessä.
     *
     * @return lista pelaajista siirtojärjestyksessä
     */
    public ArrayList<Pelaaja> getSiirtojarjestys() {
        return siirtojarjestys;
    }

    /**
     * Vaihdetaan vuorossa olevan pelaajan indeksiä.
     */
    public void vaihdaVuoroa() {
        if (vuoro == 0) {
            vuoro = 1;
        } else {
            vuoro = 0;
        }
    }

    public Pelaaja getVuorossaOlevaPelaaja() {
        return siirtojarjestys.get(vuoro);
    }

    /**
     * Heitä noppia.
     */
    public void heitaNopat() {
        siirrot.heitaNopat();
    }

    /**
     * Hae noppien arvoista saatavat siirrot.
     *
     * @return lista kokonaislukuja jotka ovat arvotut siirrot
     */
    public ArrayList<Integer> haeSiirrot() {
        return siirrot.haeSiirrot();
    }

    /**
     * Anna tietokoneen valita väri.
     */
    public void tietokoneValitseeVarin() {
        this.asetaPelaajaMustaksi(tekoaly.valitseVari(siirtojarjestys));
    }

    /**
     * Antaa tietokoneen pelattavaksi yhden siirron ja palauttaa Siirto-oliona
     * tämän tuloksen.
     *
     * @return valittu siirto
     */
    public Siirto pelaaTietokone() {
        Pelaaja tietokone = siirtojarjestys.get(vuoro);
        Siirto siirto = new Siirto(0, 0, false, false);
        if (!this.eiVoiSiirtaa()) {
            siirto = tekoaly.pelaa(tietokone, pelilogiikka, siirrot.haeSiirrot());
        } else {
            siirto = new Siirto(0, 0, false, true);
        }
        return siirto;
    }

    /**
     * Aseta pelaajan siirron lähtoruutu.
     *
     * @param lahtoruutu lähtöruudun indeksi
     * @return true jos pelaajalla on ruudussa siirrettävissä oleva nappula
     */
    public boolean asetaLahtoruutu(int lahtoruutu) {
        if (pelilogiikka.pelaajaVoiSiirtaaRuuduista(siirtojarjestys.get(vuoro)).contains((Integer) lahtoruutu)) {
            this.lahtoruutu = lahtoruutu;
            return true;
        }
        return false;
    }

    /**
     * Nollaa pelaajan siirron lähtöruudun.
     */
    public void nollaaLahtoruutu() {
        lahtoruutu = -1;
    }

    /**
     * Haetaan asetettu lähtöruutu.
     *
     * @return lähtöruudun indeksi
     */
    public int getLahtoruutu() {
        return lahtoruutu;
    }

    /**
     * Haetaan mahdolliset kohderuudut pelaajan siirrolle.
     *
     * @return lista mahdollisia kohderuutuja
     */
    public ArrayList<Integer> pelaajaVoiSiirtaaRuutuihin() {
        return pelilogiikka.pelaajaVoiSiirtaaRuutuihin(siirtojarjestys.get(vuoro), lahtoruutu, siirrot.haeSiirrot());
    }

    /**
     * Haetaan mahdolliset lähtöruudut pelaajan siirrolle.
     *
     * @return lista mahdollisia lähtöruutuja
     */
    public ArrayList<Integer> pelaajaVoiSiirtaaRuuduista() {
        return pelilogiikka.pelaajaVoiSiirtaaRuuduista(siirtojarjestys.get(vuoro));
    }

    /**
     * Siirretään pelaajan nappulaa.
     *
     * @param minne kohderuudun indeksi
     */
    public void siirraNappulaa(int minne) {
        if (lahtoruutu > -1) {
            pelilogiikka.siirraNappulaa(siirtojarjestys.get(vuoro), lahtoruutu, minne);
            // tässä alla on bugi: jos kaikki nappulat ovat kotialueella, peli ei oikeasti toimi aivan näin
            if (siirrot.haeSiirrot().contains((Integer) Math.abs(lahtoruutu - minne))) {
                siirrot.haeSiirrot().remove((Integer) Math.abs(lahtoruutu - minne));
            } else if (pelilogiikka.nappulatKotialueella(siirtojarjestys.get(vuoro))) {
                int poistettava = -1;
                for (int siirto : siirrot.haeSiirrot()) {
                    if (siirto >= Math.abs(lahtoruutu - minne)) {
                        poistettava = siirto;
                    }
                }
                siirrot.haeSiirrot().remove((Integer) poistettava);
            }
            lahtoruutu = -1;
        }
    }

    /**
     * Tarkistaa eikö annettu voi Pelaaja siirtää mitään nappuloitaan.
     *
     * @return palauttaa true jos mitään nappuloita ei voi siirtää
     */
    public boolean eiVoiSiirtaa() {
        boolean voiSiirtaa = false;
        Pelaaja pelaaja = siirtojarjestys.get(vuoro);
        if (pelaaja.getMaali() == 0) {
            voiSiirtaa = voikoPelaajaSiirtaa(-1);
        } else {
            voiSiirtaa = voikoPelaajaSiirtaa(1);
        }
        return !voiSiirtaa;
    }

    private boolean voikoPelaajaSiirtaa(int suunta) {
        boolean voiSiirtaa = false;
        Pelaaja pelaaja = siirtojarjestys.get(vuoro);
        if (pelilogiikka.pelaajanNappulaMaara(Math.abs(pelaaja.getMaali() - 25), pelaaja) > 0) {
            for (int siirto : siirrot.haeSiirrot()) {
                //System.out.println("siirto " + siirto);
                if (pelilogiikka.ruutuunVoiSiirtya((Math.abs(pelaaja.getMaali() - 25) + suunta * siirto), pelaaja)) {
                    voiSiirtaa = true;
                    break;
                }
            }
        } else {
            for (int siirto : siirrot.haeSiirrot()) {
                //System.out.println("else siirto " + siirto);
                for (int ruutu : pelilogiikka.pelaajaVoiSiirtaaRuuduista(pelaaja)) {
                    //System.out.println("else ruutu " + ruutu);
                    if (pelilogiikka.pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu, siirrot.haeSiirrot()).contains(ruutu + suunta * siirto)) {
                        voiSiirtaa = true;
                        break;
                    }
                }
            }
            if (pelilogiikka.nappulatKotialueella(pelaaja)) {
                for (int ruutu : pelilogiikka.pelaajaVoiSiirtaaRuuduista(pelaaja)) {
                    for (int kohderuutu : pelilogiikka.pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu, siirrot.haeSiirrot())) {
                        for (int siirto : siirrot.haeSiirrot()) {
                            if (siirto >= ((Integer) Math.abs(ruutu - kohderuutu))) {
                                voiSiirtaa = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return voiSiirtaa;
    }

    /**
     * Hae Pelilauta.
     *
     * @return palauttaa pelilaudan
     */
    public Lauta pelitilanne() {
        return pelilogiikka.pelitilanne();
    }

    /**
     * Käy Pelaajat läpi ja tarkistaa onko joku voittanut.
     *
     * @return true jos joku on voittanut
     */
    public boolean onkoJokuVoittanut() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Palauttaa pelin voittaneen Pelaajan.
     *
     * @return pelin voittanut pelaaja
     */
    public Pelaaja kukaVoitti() {

        for (Pelaaja pelaaja : siirtojarjestys) {
            if (pelilogiikka.onkoPelaajaVoittanut(pelaaja)) {
                return pelaaja;
            }
        }

        return null;
    }

    /**
     * Hae pelilogiikka.
     *
     * @return pelilogiikka
     */
    public Pelilogiikka getPelilogiikka() {
        return pelilogiikka;
    }

}
