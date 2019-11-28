package saper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gra {

    private int poziom;
    private Plansza p;

    public Gra() {
        System.out.println("Podaj poziom trudności. Wpisz 1,2 lub 3");
        Scanner skanerek= new Scanner(System.in);
        String poziomik=skanerek.nextLine();
        while (!poziomik.matches("[1,2,3]")){
            System.out.println("Podano zły napis. Spróbuj ponownie");
            poziomik=skanerek.nextLine();
        }
        this.poziom=Integer.parseInt(poziomik);
    }

    public boolean czyPoprawnyNapis(String decyzja){
        if (!decyzja.matches("\\d{1,}\\s\\d{1,}\\s[of]")) return false;
        else if (Integer.parseInt(dajwspolrzedne(decyzja)[0])>p.getX()-1||Integer.parseInt(dajwspolrzedne(decyzja)[1])>p.getY()-1) return false;
        return true;
    }

    public String[] dajwspolrzedne(String decyzja){

        Pattern iksowy = Pattern.compile("\\d{1,}\\s");
        Matcher iksowy1= iksowy.matcher(decyzja);

        String winx="";

        while (iksowy1.find()) winx+=iksowy1.group();
        return winx.split("\\s");
    }


    public void pierwszyRuch() {

        Pole[][] p1 = p.getZestaw();
        
        int oflagowane=0;
        
        for (Pole[] wiersz:p1) for (Pole pole:wiersz) if (pole.CzyOflagowany()) oflagowane++;
        
        System.out.println(p);

        System.out.println("Wpisz współrzędne(oddzielone spacją) i zdecyduj: Odkryć o, Oflagować f");

        Scanner skaner = new Scanner(System.in);
        String decyzja = skaner.nextLine();

        while (!czyPoprawnyNapis(decyzja)){
            System.out.println(p+"\n"+"Wprowadzono zły napis. Spróbuj ponownie");
            decyzja=skaner.nextLine();
        }

        int x1 = Integer.parseInt(dajwspolrzedne(decyzja)[0]);
        int y1 = Integer.parseInt(dajwspolrzedne(decyzja)[1]);
        Integer[] pierwsze_pole = new Integer[2];
        
        if (decyzja.endsWith("o")) {

            pierwsze_pole[0] = y1;
            pierwsze_pole[1] = x1;

            p.setPierwsze(pierwsze_pole);
            p.przydzielWartości();
            //p1=p.getZestaw();

            odkrywanie(p1[y1][x1]);

            p.setZestaw(p1);
            System.out.println(p);
            System.out.println("Oflagowane pola: " + oflagowane);

        }

        if (decyzja.endsWith("f")){

            p1[y1][x1].Oflaguj();
            if (p1[y1][x1].CzyOflagowany()) oflagowane++ ;
            else oflagowane--;

            p.setZestaw(p1);
            System.out.println("Oflagowane pola: "+oflagowane);
            pierwszyRuch();
        }
    }

    public void rozgrywka()  {

        p = new Plansza(this.poziom);
        pierwszyRuch();

        int oflagowane = 0;
        Scanner skaner = new Scanner(System.in);
        boolean przegrana = false;
        boolean wygrana = false;
        Pole[][] p1 =p.getZestaw();

        for (Pole[] wiersz:p1) for (Pole pole:wiersz) if (pole.CzyOflagowany()) oflagowane++;

            while (przegrana == false && wygrana == false) {
                System.out.println("Wpisz współrzędne(oddzielone spacją) i zdecyduj: Odkryć o, Oflagować f");
                String decyzja1 = skaner.nextLine();

                while (!czyPoprawnyNapis(decyzja1)) {
                    System.out.println("Podano zły napis. Spróbuj ponownie");
                    decyzja1 = skaner.nextLine();
                }

                int x = Integer.parseInt(dajwspolrzedne(decyzja1)[0]);
                int y = Integer.parseInt(dajwspolrzedne(decyzja1)[1]);

                if (decyzja1.endsWith("o")) {
                    if (p1[y][x].getWartosć() == 9) {
                        p1[y][x].Odkryj();
                        przegrana = true;
                    } else odkrywanie(p1[y][x]);

                } else if (decyzja1.endsWith("f")) {
                    p1[y][x].Oflaguj();
                    if (p1[y][x].CzyOflagowany()) oflagowane++;
                    else oflagowane--;
                }

                p.setZestaw(p1);
                System.out.println(p);
                System.out.println("Oflagowane pola: " + oflagowane);


                if (oflagowane == p.getIleBomb()) {
                    boolean warunek = true;
                    search:
                    for (Pole[] wiersz : p.getZestaw()) {
                        for (Pole pole : wiersz) {
                            if (pole.getWartosć() != 9 && !pole.czyOdkyty()) {
                                warunek = false;
                                break search;
                            }
                        }
                    }
                    if (warunek) wygrana = true;
                }
            }

        if (przegrana) System.out.println("przegrałeś!");
        if (wygrana) System.out.println("Wygrałeś!");

        System.out.println("Czy chcesz zagrać jeszcze raz? 0 - nie, 1  - tak ");
        String jeszczeraz=skaner.nextLine();
        if (jeszczeraz.equals("1")) rozgrywka();

    }

    public void odkrywanie(Pole pole) {
        if (pole.getWartosć() != 0) {
            pole.Odkryj();
        } else if (pole.czyOdkyty() == false) {
            pole.Odkryj();
            for (Pole p : pole.getOtoczenie().getZestawik()) {
                odkrywanie(p);
            }

        }
    }
}


