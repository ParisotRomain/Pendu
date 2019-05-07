package Pendu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class MotAleatoireFichier {
	public static String getFrom(File fichier) {
		
		String monMot = null;

		try {
			// On ouvre le fichier contenant la liste de mots
			Scanner sc = new Scanner(fichier);

			// On parcours tout le fichier pour conter le nombre de mots
			int nbrMots = 0;
			while(sc.hasNext()) {
				sc.next();
				nbrMots++;
			}
			sc.close();

			if (nbrMots == 0)
				return "Hello";
			
			// On choisit un indice al�atoire
			Random rand = new Random();
			int index = rand.nextInt(nbrMots);

			// On va r�cup�rer le mot � l'indice choisit
			sc = new Scanner(fichier);
			for (int i = 0; i < index; i++) {
				sc.next();
			}
			monMot = sc.next();
			sc.close();

			// On affiche le mot r�cup�r�
			return (monMot);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;

	}
}