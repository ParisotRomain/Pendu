package Pendu;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// fichier avec la liste des mots :
		URL url = Main.class.getResource("mots.txt");
		File fichier = new File(url.getPath());

		// on crée une liste avec les lettres possible et une avec les lettres jouées
		String strAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String strJouees   = "..........................";	// au début du jeu, on n'a joué aucune lettre
		char[] alphabet = strAlphabet.toCharArray();
		char[] jouees   = strJouees  .toCharArray();


		// on récupère un mot aléatoire
		String motATrouver = MotAleatoireFichier.getFrom(fichier).toLowerCase();
		// pour enlever les accents du mot à trouver
		char[] avecAccents = "àâéèêîïôùû".toCharArray();
		char[] sansAccents = "aaeeeiiouu".toCharArray();
		for(int i=0; i<avecAccents.length; i++)
			motATrouver = motATrouver.replace(avecAccents[i], sansAccents[i]);

		// on génère le mot à afficher en remplacant les lettres non jouées par '.'
		String motAAfficher = String.valueOf(motATrouver);
		for(int i=0; i<alphabet.length; i++)
			motAAfficher = motAAfficher.replace(alphabet[i], jouees[i]);

		// affichage initial
//		System.out.println(String.format("%"+(motATrouver.length()-1)/2+"s", " ") + "Mot" + String.format("%"+(motATrouver.length())/2+"s", " ") // les String.format sont pour centrer en fonction de la ltaille du mot à trouver
//							+ "|        Lettres Jouées        | err");
//		System.out.println(motAAfficher + "  |  " + strJouees + "  |  0");
		System.out.println(" " + strJouees.substring( 0, 4) +   " |                          ");
		System.out.println(" " + strJouees.substring( 4, 8) +   " |            v             ");
		System.out.println(" " + strJouees.substring( 8,12) +   " |                   v  __  ");
		System.out.println(" " + strJouees.substring(12,16) +   " |      V              ( ~) ");
		System.out.println(" " + strJouees.substring(16,20) +   " |                    (_~__)");
		System.out.println(" " + strJouees.substring(20,24) +   " |                      ||  ");
		System.out.println(" " + strJouees.substring(24,26) + "   | wwwwwwwwwwwwwwwwwwwwwwwww");
		System.out.println("");
		System.out.println(String.format("%"+(30-motAAfficher.length())/2+"s", " ") + "?  " + motAAfficher + "  ?");

		Scanner sc = new Scanner(System.in);
		Integer erreurs = 0;
		String input = "";
		char lettreJouee = ' ';

		// début du jeu
		while((motAAfficher != motATrouver)&&(erreurs < 8)) {
			// on récupère la lettre jouée
			boolean lettreOk = false;
			while (!lettreOk) {	// tant que la lettre n'est pas valide
				input = sc.nextLine();
				lettreJouee = (input.length() > 0 ? input.charAt(0) : ' ');	// on vérifie qu'au moins un caractère a été entré sinon on met ' '

				if (strAlphabet.indexOf(lettreJouee) == -1) {	// la lettre ne fait pas partie de l'alphabet
					System.out.println("Lettre invalide !");
				} else if (strJouees.indexOf(lettreJouee) != -1) {	// la lettre a déjà été jouée
					System.out.println("Lettre déjà jouée");
				} else {	// la lettre est correcte
					lettreOk = true;
				}
			}


			// on modifie la liste des lettres jouées pour ajouter la lettre jouée au bon index
			int indJoue = strAlphabet.indexOf(lettreJouee);
			strJouees = strJouees.substring(0, indJoue) + lettreJouee + strJouees.substring(indJoue+1);
			jouees = strJouees.toCharArray();


			if (motATrouver.indexOf(lettreJouee) != -1) { // le mot contient la lettre jouée
				// on met à jour le mot à afficher
				motAAfficher = String.valueOf(motATrouver);
				for(int i=0; i<alphabet.length; i++)
					motAAfficher = motAAfficher.replace(alphabet[i], jouees[i]);
			} else { // le mot ne contient pas la lettre jouée
				erreurs++;
			}


			// affichage
			switch (erreurs) {
			case 0 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |                          ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |            v             ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |                   v  __  ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      V              ( ~) ");
				System.out.println(" " + strJouees.substring(16,20) +   " |                    (_~__)");
				System.out.println(" " + strJouees.substring(20,24) +   " |                      ||  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | wwwwwwwwwwwwwwwwwwwwwwwww");
				break;
			case 1 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |                v         ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |        v            .    ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |                      __  ");
				System.out.println(" " + strJouees.substring(12,16) +   " |                     ( ~) ");
				System.out.println(" " + strJouees.substring(16,20) +   " |                    (_~__)");
				System.out.println(" " + strJouees.substring(20,24) +   " |                      ||  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | w==========wwwwwwwwwwwwww");
				break;
			case 2 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |                          ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      ||       v          ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||              __  ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||             ( ~) ");
				System.out.println(" " + strJouees.substring(16,20) +   " |      ||            (_~__)");
				System.out.println(" " + strJouees.substring(20,24) +   " |      ||              ||  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========_wwwwwwwwwwwww");
				break;
			case 3 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |                          ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      || /                ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/             __  ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||             (__) ");
				System.out.println(" " + strJouees.substring(16,20) +   " |     /||             ~||/ ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||              ||  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========____wwwwwwwwww");
				break;
			case 4 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |      ,===========        ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      || /                ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/                 ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||              ::  ");
				System.out.println(" " + strJouees.substring(16,20) +   " |     /||             ~||/ ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||              ||  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========______wwwwwwww");
				break;
			case 5 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      || /     |          ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/                 ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||                  ");
				System.out.println(" " + strJouees.substring(16,20) +   " |     /||                  ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||                  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========_______wwwwwww");
				break;
			case 6 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      || /     |          ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/      O          ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||       |          ");
				System.out.println(" " + strJouees.substring(16,20) +   " |     /||                  ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||                  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========__________wwww");
				break;
			case 7 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |      || /     |          ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/      O          ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||      /|\\         ");
				System.out.println(" " + strJouees.substring(16,20) +   " |     /||                  ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||                  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========______________");
				break;
			case 8 :
				System.out.println(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        ");
				System.out.println(" " + strJouees.substring( 4, 8) +   " |   ~  || /     |     ~    ");
				System.out.println(" " + strJouees.substring( 8,12) +   " |      ||/      O        ~ ");
				System.out.println(" " + strJouees.substring(12,16) +   " |      ||   ~  /|\\         ");
				System.out.println(" " + strJouees.substring(16,20) +   " | ~   /||      /|    ~     ");
				System.out.println(" " + strJouees.substring(20,24) +   " |    //||  ~            ~  ");
				System.out.println(" " + strJouees.substring(24,26) + "   | _==========______________");
				break;
			}
			
			System.out.println("");
			//System.out.println(motAAfficher + "  |  " + strJouees + "  |  " + erreurs);
			System.out.println(String.format("%"+(30-motAAfficher.length())/2+"s", " ") + "?  " + motAAfficher + "  ?");
		}
		
		// fin du game
		if (erreurs < 8) {
			//		System.out.println("\n  *\\o_             " + String.format("%"+(erreurs.toString().length())+"s", " ") + "              _o/*");
			//		System.out.println("   /  *  Gagné avec " + erreurs + (erreurs > 1 ? " erreurs" : " erreur ") + " !  *  \\");
			//		System.out.println("  <\\                 " + String.format("%"+(erreurs.toString().length())+"s", " ") + "              /> ");
			//		System.out.println();
			System.out.println("\n   _o/*         " + String.format("%"+(erreurs.toString().length())+"s", " ") + "              *\\o_ ");
			System.out.println("  *  \\  Gagné avec " + erreurs + (erreurs > 1 ? " erreurs" : " erreur ") + " !  /  *");
			System.out.println("     />           " + String.format("%"+(erreurs.toString().length())+"s", " ") + "            <\\ ");
		} else {
			System.out.println(" Perdu...  Le mot était " + motATrouver);
		}
		sc.close();
	}
}

/*
 *  v   ,========Y==        
 *      || /     |     v    
 *      ||/      O      __  
 *      ||      /|\    ( ~) 
 *     /||      /|    (_~__)
 *    //||              ||  
 * w==========wwwwwwwwwwwwww
 */