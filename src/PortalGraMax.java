import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private void zakonczGre() {
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

class PomocnikGry {
    private static final String alfabet = "abcdef";
    private int dlugoscPlanszy = 7;
    private int wielkoscPlanszy = 49;
    private int[] plansza = new int[wielkoscPlanszy];
    private int iloscPortali = 0;

    public String pobierzDaneWejsciowe(String komunikat) {
        String daneWejsciowe = null;
        System.out.println(komunikat + " ");
        try {
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(System.in));
            daneWejsciowe = is.readLine();
            if (daneWejsciowe.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return daneWejsciowe.toLowerCase();
    }

    public ArrayList rozmiescPortal(int wielkoscPortalu) {
        ArrayList<String> zajetePola = new ArrayList<String>();
        String[] wspolrzedneLnc = new String[wielkoscPortalu];
        String pomoc = null;
        int[] wspolrzedne = new int[wielkoscPortalu];
        int prob = 0;
        boolean powodzenie = false;
        int polozenie = 0;

        iloscPortali++;
        int inkr = 1;
        if ((iloscPortali % 2) == 1) {
            inkr = dlugoscPlanszy;
        }
        while (!powodzenie & prob++ < 200) {
            polozenie = (int) (Math.random() * wielkoscPlanszy);
            int x = 0;
            powodzenie = true;
            while (powodzenie && x < wielkoscPortalu) {
                if (plansza[polozenie] == 0) {
                    wspolrzedne[x++] = polozenie;
                    polozenie += inkr;
                    if (polozenie >= wielkoscPlanszy) {
                        powodzenie = false;
                    }
                    if (x > 0 & (polozenie % dlugoscPlanszy == 0)) {
                        powodzenie = false;
                    } else {
                        powodzenie = false;
                    }
                }
            }
        }
            int x = 0;
            int wiersz = 0;
            int kolumna = 0;
            while (x < wielkoscPortalu) {
                plansza[wspolrzedne[x]] = 1;
                wiersz = (int) (wspolrzedne[x] / dlugoscPlanszy);
                kolumna = wspolrzedne[x] % dlugoscPlanszy;
                pomoc = String.valueOf(alfabet.charAt(kolumna));
                zajetePola.add(pomoc.concat(Integer.toString(wiersz)));
                x++;
            } return zajetePola;
        }
    }


