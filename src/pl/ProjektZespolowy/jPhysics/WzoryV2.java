package pl.ProjektZespolowy.jPhysics;

import static java.lang.Math.*;


public class WzoryV2 {

    private void nowaPredkoscPionowa(Strzala strzala, Powietrze powietrze, double czas){


        double predkoscPionowa =  strzala.getPredkoscY();
        double ciazenie = 9.8066 * czas;
        double opor = czas * oporPowietrzaPion(strzala,powietrze);
        if(predkoscPionowa<0){
            strzala.setPredkoscY(predkoscPionowa - ciazenie + opor);
        }
        else {
            strzala.setPredkoscY(predkoscPionowa - ciazenie - opor);
        }
    }


    private void nowaPredkoscPozioma(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){

        double predkoscPozioma= strzala.getPredkoscX();
        double wia = czas * oporWiatruPoziom(strzala,powietrze,wiatr);
        double opor = czas * oporPowietrzaPoziom(strzala,powietrze);

        strzala.setPredkoscX(predkoscPozioma  - opor - wia);

    }



    private double pozycjaY(Strzala strzala, Powietrze powietrze, double czas) {

        double poz = strzala.getPozycjaY();
        double drogaY = strzala.getPozycjaY() + strzala.getPredkoscY()*czas;


        nowaPredkoscPionowa(strzala,powietrze,czas);
        strzala.setPozycjaY(drogaY);

        return poz;
    }

    private double pozycjaX(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){

        double poz = strzala.getPozycjaX();
        double drogaX = strzala.getPozycjaX() + strzala.getPredkoscX() * czas;

        nowaPredkoscPozioma(strzala,powietrze,wiatr,czas);
        strzala.setPozycjaX(drogaX);

        return poz;
    }

    public double[] nowapozycja(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){
        double[] wsp = new double[2];
        nowyKat(strzala);
        wsp[0]=pozycjaX(strzala,powietrze,wiatr,czas);
        wsp[1]=pozycjaY(strzala,powietrze,czas);

        return wsp;
    }

    private void nowyKat(Strzala strzala){
        double kat=0;
        double X = strzala.getPredkoscX();
        double Y = strzala.getPredkoscY();
        if(X<0){
            if(Y<0){
                kat=-180+toDegrees(atan(Y/X));
            }
            if(Y>0){
                kat=180+toDegrees(atan(Y/X));
            }
            if(Y==0){
                kat=180;
            }
        }

        if(X>0){
            if(Y!=0){
                kat=toDegrees(atan(Y/X));
            }
            if(X==0){
                kat=0;
            }
        }
        if(Y>0)
            if(X==0)
                kat=90;
        if(Y<0)
            if(X==0)
                kat=-90;

        strzala.setNachylenie(kat);
    }

    private double oporPowietrzaPion(Strzala strzala, Powietrze powietrze){

         double sila = 0.5 * powietrze.getGestoscPowietrza()*strzala.getOporStrzaly()*strzala.getPowierzchniaCzolowa()*pow(strzala.getPredkoscY(),2);
         double przyspieszenie = sila/strzala.getMasa();

         return przyspieszenie;
    }
    private double oporPowietrzaPoziom(Strzala strzala, Powietrze powietrze){

        double sila = 0.5 * powietrze.getGestoscPowietrza()*strzala.getOporStrzaly()*strzala.getPowierzchniaCzolowa()*pow(strzala.getPredkoscX(),2);
        double przyspieszenie = sila/strzala.getMasa();
        return przyspieszenie;
    }

    private double oporWiatruPoziom(Strzala strzala, Powietrze powietrze, Wiatr wiatr){

        double sila;

        if(wiatr.getSilaWiatru()!=0)
            sila = ( 0.5 * powietrze.getGestoscPowietrza() * ( strzala.getPowierzchniaCzolowa() + sin(strzala.getNachylenie()) * strzala.getPowierzchniaBoczna() ) * pow( (strzala.getPredkoscX() - wiatr.getSilaWiatru()),2) );
        else
            sila = 0;
        sila=abs(sila);
        if( (strzala.getPredkoscX()-wiatr.getSilaWiatru()) < 0 )
            sila = sila*(-1);

        double przyspieszenie = sila/strzala.getMasa();
        System.out.println("        p:" +przyspieszenie);
        return przyspieszenie;
    }

//    public double rk4(double x0, double xn, double y0, Drogi drogi){
//
//        double h = 0.1;
//        double k1,k2,k3,k4;
//        double n = (xn*10-x0*10)/(h*10);
//
//        double[][] xi = new double[(int)n+1][1];
//        double[][] yi = new double[(int)n+1][1];
//
//        xi[0][0]  = x0;
//        yi[0][0]  = y0;
//
//        for(int i = 1; i<n+1 ; i++){
//
//            k1 = h * drogi.droga( xi[i-1][0], yi[i-1][0]);
//            k2 = h * drogi.droga( xi[i-1][0] + 0.5 * h, yi[i-1][0] + 0.5 * k1);
//            k3 = h * drogi.droga( xi[i-1][0] + 0.5 * h, yi[i-1][0] + 0.5 * k2);
//            k4 = h * drogi.droga( xi[i-1][0] + h, yi[i-1][0] + k3 );
//
//            xi[i][0] = xi[i-1][0] + h;
//            yi[i][0] = yi[i-1][0] + ((1.0/6.0) * (k1 + (2*k2) + (2*k3) + k4));
//
//        }
//        return yi[(int)n][0];
//    }
//
//    public enum Drogi {
//        PION {
//            public double droga(double x, double y) {
//               return y-(9.8066 * x);
//                // return x + sin( (y+1)/ sqrt(13));
//            }
//        },
//        POZIOM {
//            public double droga(double x, double y) {
//                return 2*x*y;
//            }
//        };
//
//        public abstract double droga(double x, double y);
//    }
//
}
