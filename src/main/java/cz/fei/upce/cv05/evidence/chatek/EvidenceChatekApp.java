package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {
	
	static Scanner scanner = new Scanner(System.in);
	static final int MAX_VELIKOST_CHATKY = 10;
    public static void main(String[] args) {
        // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
        final int KONEC_PROGRAMU = 0;
        final int VYPIS_CHATEK = 1;
        final int VYPIS_KONKRETNI_CHATKU = 2;
        final int PRIDANI_NAVSTEVNIKU = 3;
        final int ODEBRANI_NAVSTEVNIKU = 4;
        final int CELKOVA_OBSAZENOST = 5;
        final int VYPIS_PRAZDNE_CHATKY = 6;
        final int VELIKOST_KEMPU = 5;
        

        

        // Definovani pole podle velikosti kempu (poctu chatek)
        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();
            
            switch (operace) {
                case VYPIS_CHATEK -> vypisChatek(chatky);

                case VYPIS_KONKRETNI_CHATKU -> vypisKonktretni(chatky);

                case PRIDANI_NAVSTEVNIKU -> pridani(chatky);

                case ODEBRANI_NAVSTEVNIKU -> odebrani(chatky);

                case CELKOVA_OBSAZENOST -> System.out.println("Celkovy pocet navstevniku "
                						+ "je: " + Obsazenost(chatky));
                
                case VYPIS_PRAZDNE_CHATKY -> System.out.println("Pocet prazdnych chatek je: " + pocetPrazdnych(chatky));

                case KONEC_PROGRAMU -> System.out.println("Konec programu");
                    
                default ->  System.err.println("Neplatna volba");
            }
        } while (operace != 0);
    }
    
    private static int cisloChatky() {
    	return (scanner.nextInt()-1);
    }
    
    private static int pocetNavstevniku() {
    	return scanner.nextInt();
    }
    
    private static int Obsazenost (int[]pole) {
    	int celkovyPocet=0;
    	for (int i=0;i<pole.length;i++) {
        	celkovyPocet+=pole[i];
        }
    	return celkovyPocet;
    }
    
    private static int pocetPrazdnych(int[]pole) {
    	int pocetPrazdnychChatek=0;
    	for (int i=0;i<pole.length;i++) {
        	if (pole[i]==0) {
        		pocetPrazdnychChatek++;
        	}
        }
    	return pocetPrazdnychChatek;
    }
    
    private static void vypisKonktretni(int[]pole) {
    	System.out.println("Zadej cislo chatky: ");
    	int cislo= cisloChatky();
    	if (podminkaCislo(cislo, pole)) {
    		System.out.println("Chatka: " + (cislo+1) + " = " + pole[cislo]);
    	}else {
    		System.err.println("Chatka neexistuje!");
    		return;
    	}
    	
    }
    
    private static boolean podminkaCislo(int cisloChatky, int[] pole) {
    	if (cisloChatky < 0 || cisloChatky >= pole.length) {
            System.err.println("Tato chatka neexistuje");
            return false; // Zacni novou iteraci cyklu
        }return true;

    }
    
    private static void vypisChatek(int[] pole) {
    	 for (int i = 0; i < pole.length; i++) {
             System.out.println("Chatka [" + (i + 1) + "] = " + pole[i]);
         }
    }
    
    private static void pridani(int[] pole) {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = cisloChatky();
        if (podminkaCislo(cisloChatky, pole)) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = pocetNavstevniku();

        if (podminkaCislo(pocetNavstevniku, pole)) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        if ((pole[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }
        pole[cisloChatky] = pocetNavstevniku + pole[cisloChatky];
    }
    
    private static void odebrani(int[] pole) {
    	System.out.println("Zadej cislo chatky: ");
        int cisloChatky = cisloChatky();
        if (cisloChatky < 0 || cisloChatky >= pole.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }
        System.out.println("Zadej pocet navstevniku co chces odebrat: ");
        int pocetOdebranych = pocetNavstevniku();
        if (podminkaCislo(pocetOdebranych, pole)) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }
        if (pole[cisloChatky]-pocetOdebranych < 0) {
        	System.err.println("Neni tam tolik navstevniku!");
        	return;
        }
        pole[cisloChatky]= pole[cisloChatky]-pocetOdebranych;
    }
    
    private static boolean podminkaVelikost(int pocetNavstevniku, int[] pole) {
    	if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
    		System.err.println("Neplatna hodnota pro pocet navstevniku");
    	}
    	return true;
    }
}
