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
		System.out.println(String.format("%"+(motATrouver.length()-1)/2+"s", " ") + "Mot" + String.format("%"+(motATrouver.length())/2+"s", " ") // les String.format sont pour centrer en fonction de la ltaille du mot à trouver
							+ "|        Lettres Jouées        | err");
		System.out.println(motAAfficher + "  |  " + strJouees + "  |  0");
		
		Scanner sc = new Scanner(System.in);
		int erreurs = 0;
		String input = "";
		char lettreJouee = ' ';

		// début du jeu
		while(motAAfficher != motATrouver) {
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
			System.out.println(motAAfficher + "  |  " + strJouees + "  |  " + erreurs);

		}
		// fin du game
		System.out.println("\n  *\\o/*  Gagné avec " + erreurs + (erreurs > 1 ? " erreurs" : " erreur") + " !  *\\o/*");

		sc.close();
	}
}

