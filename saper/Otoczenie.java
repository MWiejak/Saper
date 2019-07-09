package saper;

import java.util.ArrayList;

public class Otoczenie {
    private final ArrayList<Pole> zestawik=new ArrayList<>();

        public Otoczenie(Pole pole, Plansza plansza) {
            Pole[][]p1=plansza.getZestaw();
            int x=pole.getX();
            int y=pole.getY();
                    if (y - 1 >= 0) {
                        if (x - 1 >= 0) {
                            zestawik.add(p1[y - 1][x - 1]);
                        }
                        zestawik.add(p1[y - 1][x]);
                        if (x + 1 < plansza.getX()) {
                            zestawik.add(p1[y - 1][x + 1]);
                        }
                    }
                    if (x - 1 >= 0) {
                        zestawik.add(p1[y][x - 1]);
                    }
                    if (x + 1 < plansza.getX()) {
                        zestawik.add(p1[y][x + 1]);
                    }
                    if (y + 1 < plansza.getY()) {
                        if (x - 1 >= 0) {
                            zestawik.add(p1[y + 1][x - 1]);
                        }
                        zestawik.add(p1[y + 1][x]);
                        if (x + 1 < plansza.getX()) {
                            zestawik.add(p1[y + 1][x + 1]);
                        }
                    }
                }


        public ArrayList<Pole> getZestawik(){return zestawik;}
    }

