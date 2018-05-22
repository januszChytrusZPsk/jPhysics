package pl.ProjektZespolowy.jPhysics;

public class Main {

	public static void main(String[] args) {
		Strzala strzala = new Strzala(0 ,90, 0, 100, Groty.SZYBKA);
		Powietrze powietrze = new Powietrze();
		Wiatr wiatr = new Wiatr();

        wiatr.setSilaWiatru(-10);

        Wzory wzory = new Wzory();

        System.out.println(strzala.getPredkoscX());
        System.out.println(strzala.getPredkoscY());

        double czas = 0.01;


        double[] pp ;


        for(; ; ) {
            pp = wzory.nowapozycja(strzala,powietrze,wiatr,czas);

            System.out.println(pp[0]+ ";" +pp[1]+ " nach:" + strzala.getNachylenie() );
            if(pp[1]<0)
                break;

        }

	}

}
