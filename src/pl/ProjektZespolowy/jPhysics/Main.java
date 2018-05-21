package pl.ProjektZespolowy.jPhysics;


public class Main {

	public static void main(String[] args) {
		Strzala strzala = new Strzala(860 ,45 , 0, 0, Groty.NORMALNA);
        Strzala strzala1 = new Strzala(0.1 ,0 , 0, 0, Groty.SZYBKA);
        Strzala strzala2 = new Strzala(0.1 ,0 , 0, 0, Groty.WOLNA);
		Powietrze powietrze = new Powietrze();
		Wiatr wiatr = new Wiatr();
//		Wzory wzory = new Wzory();

        wiatr.setSilaWiatru(0);



        WzoryV2 wzoryV2 = new WzoryV2();
//        System.out.println(wzoryV2.pion(strzala,1));
        System.out.println(strzala.getPredkoscX());
        System.out.println(strzala.getPredkoscY());
        System.out.println("=========++++-----------++++==========");

        double czas = 1;

        for(int i = 0; i<500; i++) {
            if(strzala.getPozychaY()<0)
                break;
            System.out.println(wzoryV2.pozycjaY(strzala, czas));
            System.out.println(wzoryV2.pozycjaX(strzala, czas));
            System.out.println("");

        }
//		System.out.println(wzory.silaOporuPowietrza(strzala, powietrze));
//        System.out.println(wzory.silaWiatru(strzala, powietrze,wiatr));
//        System.out.println(wzory.silaWypadkowa(strzala, powietrze,wiatr));
//        System.out.println(wzory.otrzymajNachylenie(strzala, powietrze,wiatr));


/*

        System.out.println("Strzała NORMALNA");
		for(int i = 1; i < 101; i++){

			System.out.println("  " + wzory.otrzymajDrogeX(strzala, powietrze, wiatr, i) + ",  "
                     + wzory.otrzymajDrogeY(strzala, powietrze, wiatr, 1) + ",  "
                     + Math.toDegrees(strzala.getNachylenie())
                     );
		}
*/
	}
	
}
