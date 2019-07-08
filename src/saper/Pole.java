
package saper;

public class Pole {
    
    private final int x;
    private final int y;
    private int wartość=0;
    private  Otoczenie otoczenie;
    private boolean czyOdkryty;
    private boolean czyOflagowany;
    
   public Pole(int i , int iy ){
    x=i;
    y=iy;
    }
   
    @Override
   public String toString(){
       if (czyOflagowany==true) return "[*]";
       if (czyOdkryty==false) return "[ ]";
       if (wartość==9) return "[X]";
       return "["+wartość+"]";
    }

   public boolean czyOdkyty(){
       return czyOdkryty;
   }
   
    public boolean Odkryj(){
       czyOdkryty=true;
       return sprawdzPrzegrana();
   }

    public boolean sprawdzPrzegrana(){
        if (czyOdkryty==true && wartość==9) return true;
        return false;
    }

    public void setOtoczenie(Otoczenie o ){
       this.otoczenie=o;
    }

    public void setWartość(int x){
        this.wartość=x;
    }

    public boolean CzyOflagowany(){
       return this.czyOflagowany;
    }

    public void Oflaguj(){
       if( czyOflagowany==true) czyOflagowany=false;
       else if (czyOflagowany==false) czyOflagowany=true;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWartosć(){
        return wartość;
    }
    public Otoczenie getOtoczenie() {return otoczenie;}
}