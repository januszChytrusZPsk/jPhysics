package pl.ProjektZespolowy.jPhysics;

public class WzoryV2 {
    /**
     * rozdzielić to na 2 ruchy
     *  - pionowy ( siła ciężkości + opór powietrza )
     *  - poziomy ( opór powietrza )
     *
     */

    private void nowaPredkoscPionowa(Strzala strzala, double czas){


        double predkoscPionowa =  strzala.getPredkoscY();

        strzala.setPredkoscY(predkoscPionowa-(9.8066 * czas));
    }


    public double nowaPredkoscPozioma(Strzala strzala){

        double predkoscPozioma= strzala.getPredkoscX() ;

        return predkoscPozioma;
    }



    public double pozycjaY(Strzala strzala, double czas) {

        // pozycjaY = predkosc* czas

        double drogaY = strzala.getPozychaY() + strzala.getPredkoscY()*czas;

        nowaPredkoscPionowa(strzala,czas);
        strzala.setPozychaY(drogaY);

        return drogaY;
    }

    public double pozycjaX(Strzala strzala,double czas){

        double drogaX = strzala.getPozycjaX() + strzala.getPredkoscX() * czas;

        nowaPredkoscPozioma(strzala);
        strzala.setPozycjaX(drogaX);

        return drogaX;
    }

    private double oporPowietrza(Strzala strzala, Wiatr wiatr, Powietrze powietrze){
        return 0.0;
    }
}
