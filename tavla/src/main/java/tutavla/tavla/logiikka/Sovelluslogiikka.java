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
        pelaaja1.asetaMustaksi(true);
        Pelaaja pelaaja2 = new Pelaaja();
        pelaaja2.asetaMustaksi(false);
        this.asetaSiirtojarjestys(pelaaja1, pelaaja2);

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
        siirtojarjestys.get(pelaaja).asetaIhmiseksi(true);
        siirtojarjestys.get(pelaaja).asetaNimi(nimi);
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
                p.asetaMustaksi(true);
            } else {
                p.asetaMustaksi(false);
            }
        }
    }

    /**
     * Asetetaan siirtojärjestys.
     *
     * @param pelaaja1 ensimmäisenä siirtävä pelaaja
     * @param pelaaja2 toisena siirtävä pelaaja
     */
    public void asetaSiirtojarjestys(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        siirtojarjestys.clear();
        siirtojarjestys.add(pelaaja1);
        siirtojarjestys.add(pelaaja2);
    }

    /**
     * Palauta lista pelaajista siirtojärjestyksessä.
     *
     * @return lista pelaajista siirtojärjestyksessä
     */
    public ArrayList<Pelaaja> haeSiirtojarjestys() {
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

    /**
     * Hae vuorossa oleva pelaaja.
     *
     * @return pelaaja jonka vuoro on
     */
    public Pelaaja haeVuorossaOlevaPelaaja() {
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
     * Asetetaan siirrot.
     *
     * @param s siirroiksi asetettavat luvut
     */
    public void asetaSiirrot(ArrayList<Integer> s) {
        siirrot.asetaSiirrot(s);
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
            this.asetaLahtoruutu(siirto.haeLahto());
            this.siirraNappulaa(siirto.haeMaali());
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
    public int haeLahtoruutu() {
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
            if (siirrot.haeSiirrot().contains((Integer) Math.abs(lahtoruutu - minne))) {
                siirrot.haeSiirrot().remove((Integer) Math.abs(lahtoruutu - minne));
            } else if (pelilogiikka.nappulatKotialueella(siirtojarjestys.get(vuoro))) {
                int poistettava = -1;
                poistettava = haePoistettavaSiirto(minne, poistettava);
                siirrot.haeSiirrot().remove((Integer) poistettava);
            }
            lahtoruutu = -1;
        }
    }

    private int haePoistettavaSiirto(int minne, int poistettava) {
        for (int siirto : siirrot.haeSiirrot()) {
            if (siirto >= Math.abs(lahtoruutu - minne)) {
                poistettava = siirto;
            }
        }
        return poistettava;
    }

    /**
     * Tarkistaa eikö annettu voi Pelaaja siirtää mitään nappuloitaan.
     *
     * @return palauttaa true jos mitään nappuloita ei voi siirtää
     */
    public boolean eiVoiSiirtaa() {
        boolean voiSiirtaa = false;
        Pelaaja pelaaja = siirtojarjestys.get(vuoro);
        if (pelaaja.haeMaali() == 0) {
            voiSiirtaa = voikoPelaajaSiirtaa(-1);
        } else {
            voiSiirtaa = voikoPelaajaSiirtaa(1);
        }
        return !voiSiirtaa;
    }

    private boolean voikoPelaajaSiirtaa(int suunta) {
        boolean voiSiirtaa = false;
        Pelaaja pelaaja = siirtojarjestys.get(vuoro);
        if (pelilogiikka.pelaajanNappulaMaara(Math.abs(pelaaja.haeMaali() - 25), pelaaja) > 0) {
            voiSiirtaa = voikoRuutuunSiirtyaSyotyNappula(pelaaja, suunta, voiSiirtaa);
        } else {
            voiSiirtaa = voikoSiirtaaLaudalla(pelaaja, suunta, voiSiirtaa);
        }
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaLaudalla(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        if (!pelilogiikka.nappulatKotialueella(pelaaja)) {
            voiSiirtaa = voikoSiirtaaLaudallaNormaalisti(pelaaja, suunta, voiSiirtaa);
        } else if (pelilogiikka.nappulatKotialueella(pelaaja)) {
            voiSiirtaa = voikoSiirtaaKotialueella(pelaaja, voiSiirtaa);
        }
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaKotialueella(Pelaaja pelaaja, boolean voiSiirtaa) {
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
        return voiSiirtaa;
    }

    private boolean voikoSiirtaaLaudallaNormaalisti(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        for (int siirto : siirrot.haeSiirrot()) {
            for (int ruutu : pelilogiikka.pelaajaVoiSiirtaaRuuduista(pelaaja)) {
                if (pelilogiikka.pelaajaVoiSiirtaaRuutuihin(pelaaja, ruutu, siirrot.haeSiirrot()).contains(ruutu + suunta * siirto)) {
                    voiSiirtaa = true;
                    break;
                }
            }
        }
        return voiSiirtaa;
    }

    private boolean voikoRuutuunSiirtyaSyotyNappula(Pelaaja pelaaja, int suunta, boolean voiSiirtaa) {
        for (int siirto : siirrot.haeSiirrot()) {
            //System.out.println("siirto " + siirto);
            if (pelilogiikka.ruutuunVoiSiirtya((Math.abs(pelaaja.haeMaali() - 25) + suunta * siirto), pelaaja)) {
                voiSiirtaa = true;
                break;
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
    public Pelilogiikka haePelilogiikka() {
        return pelilogiikka;
    }

}
