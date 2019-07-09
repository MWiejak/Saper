
package saper;

import java.util.*;


public class Plansza {

    private final int x;
    private final int y;
    private final int ileBomb;
    private Pole[][] zestaw;
    private Integer[] pierwsze;

    public Plansza(int p)   {

        if (p == 1) {
            this.x = 8;
            this.y = 8;
            this.ileBomb = 10;

        } else if (p == 2) {
            this.x = 16;
            this.y = 16;
            this.ileBomb = 40;
        } else {
            this.x = 30;
            this.y = 16;
            this.ileBomb = 99;
        }

        Pole[][] zestaw = new Pole[this.y][this.x];
        for (int i = 0; i < this.y; i++) {
            Pole[] wiersz = new Pole[this.x];
            for (int a = 0; a < this.x; a++) {
                wiersz[a] = new Pole(a,i);
            }
            zestaw[i] = wiersz;
        }
        this.zestaw = zestaw;
    }

    public void przydzielWartości() {
        HashSet wsp = new HashSet();
        while (wsp.size() < this.ileBomb) {
            int numx = (int) (Math.random() * this.x);
            int numy = (int) (Math.random() * this.y);
            ArrayList<Integer> r = new ArrayList<Integer>();
            if(numx!=pierwsze[1]&&numy!=pierwsze[0]) {
                r.add(numx);
                r.add(numy);
                wsp.add(r);
            }
        }
        List<List<Integer>> wsp1 = new ArrayList<>(wsp);
        for (List<Integer> w : wsp1) {
            zestaw[w.get(1)][w.get(0)].setWartość(9);
        }

        for (Pole[] wiersz : zestaw) for (Pole pole : wiersz) pole.setOtoczenie(new Otoczenie(pole, this));
        for (Pole[] wiersz : zestaw)
            for (Pole pole : wiersz) {
                if (pole.getWartosć() != 9) {
                    int licznik = 0;
                    for (Pole policzek : pole.getOtoczenie().getZestawik()) if (policzek.getWartosć() == 9) licznik++;
                    pole.setWartość(licznik);
                }
            }
    }



    @Override
    public String toString() {
        String napis = "";

        for (int i = 0; i < y; i++) {
            for (int a = 0; a < x; a++) {
                napis += zestaw[i][a];
            }
            napis += "\n";

        }
        return napis;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIleBomb() {
        return ileBomb;
    }

    public Pole[][] getZestaw() {
        return zestaw;
    }

    public void setZestaw(Pole[][] z) {
        this.zestaw = z;
    }

    public void setPierwsze(Integer[] pierwsze) {
        this.pierwsze = pierwsze;
    }
}
    
    
        

    

