import java.util.ArrayList;

public class PortalGraMax {
    private PomocnikGry pomocnik = new PomocnikGry();
    private ArrayList<Portal> listaPortali = new ArrayList<Portal>();
    private int iloscRuchow = 0;


    public static void main(String[] args) {
        PortalGraMax gra = new PortalGraMax();
        gra.przygotujGre();
        gra.rozpocznijGre();

    }

    private void przygotujGre() {
        Portal pierwszy = new Portal();
        pierwszy.setNazwa("onet.pl");
        Portal drugi = new Portal();
        drugi.setNazwa("wp.pl");
        Portal trzeci = new Portal();
        trzeci.setNazwa("o2.pl");
        listaPortali.add(pierwszy);
        listaPortali.add(drugi);
        listaPortali.add(trzeci);

        System.out.println("Twoim celem jest zatopienie trzech portali");
        System.out.println("onet.pl, wp.pl, o2.pl");
        System.out.println("Postaraj sie je zatopić w jak najmniejszej ilosci ruchów");

        for (Portal rozmieszczanyPortal : listaPortali) {
            ArrayList<String> nowePolozenie = pomocnik.rozmiescPortal(3);
            rozmieszczanyPortal.setPolaPolozenia(nowePolozenie);
        }
    }

    private void rozpocznijGre() {
        while(!listaPortali.isEmpty()) {
            String ruchGracza = pomocnik.pobierzDaneWejsciowe("Podaj pole");
            sprawdzRuchGracza(ruchGracza);
        }
        zakonczGre();
    }

    private void sprawdzRuchGracza(String ruch) {
        iloscRuchow++;
        String wynik = "pudlo";

        for (Portal portalDoSprawdzenia : listaPortali) {
            wynik = portalDoSprawdzenia.sprawdz(ruch);
            if (wynik.equals("trafiony")) {
                break;
            }
        }
        System.out.println(wynik);
    }

    private void zakończGre() {
        System.out.println("Wszystkie portale zostały zatopione !Teraz Twoje informacje nie mają znaczenia");
        if (iloscRuchow <= 18) {
            System.out.println("Wykonales jedynie " + iloscRuchow + " ruchów");
            System.out.println("Wydostales sie, zanim Twoje informacje zatoneły");
        } else {
            System.out.println("Ale sie grzebales! Wykonales aż " + iloscRuchow + "ruchow");
            System.out.println("Teraz rybki pływaja pomiedzy Twoimi informacjami");
        }
    }
}

class Portal {
    private ArrayList<String> polaPolozenia;
    private String nazwa;

    public void setPolaPolozenia(ArrayList<String> ppol) {
        polaPolozenia = ppol;
    }
    public void setNazwa(String nzwPortalu) {
        nazwa = nzwPortalu;
    }
    public String sprawdz(String ruch){
        String wynik = "pudło";
        int indeks = polaPolozenia.indexOf(ruch);
        if (indeks >= 0) {
            polaPolozenia.remove(indeks);

            if(polaPolozenia.isEmpty()) {
                wynik = "zatopiony";
                System.out.println("Auc! Zatopiles portal " + nazwa + " : ( ");
            } else {
                wynik = "trafiony";
            }
        }
        return wynik;
    }
}