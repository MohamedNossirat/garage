package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (i == null) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}
		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	// Renvoie le dernier stationnement (ajout personnel).
	// Doit renvoyer une erreur si la voiture n'a aucun stationnement.
	public Stationnement getLastStationnement() {
		// Retenir la fonction isEmpty
		if (myStationnements.isEmpty()) {
			throw new IllegalArgumentException("La voiture n'a aucun stationnement enregistré");
		}
		return myStationnements.get(myStationnements.size() - 1);
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */

	public void entreAuGarage(Garage g) throws Exception {
		// Si la voiture est déjà dans un garage, on doit renvoyer une erreur.
		boolean a = false;
                try {
                    Stationnement dernierStationnement = this.getLastStationnement();
                    if  (dernierStationnement.estEnCours()) {
                        a = true; }
                    // On ne peut pas mettre un throw dans un try
                    // D'où le booléen a
                }
                catch(Exception e){
                }
                if (a) {
                    throw new Exception("La voiture est déjà dans un garage");
                }
                // On ajoute le nouveau stationnement
		Stationnement s = new Stationnement(this, g);
                myStationnements.add(s);
        }

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
	// Trouver le dernier stationnement de la voiture
            Stationnement dernierStationnement = getLastStationnement();
		if (dernierStationnement.estEnCours() == false) {
			throw new Exception("La voiture n'est pas dans un garage"); }
			// Terminer ce stationnement 
		dernierStationnement.terminer();
			// La ligne suivante est probablement inutile
            myStationnements.set(myStationnements.size() -1, dernierStationnement);
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	
	 public Set<Garage> garagesVisites() {
		// Le Set était déjà dans le code de l'énoncé.
		Set<Garage> listegarages = new HashSet<>();
		    for (Stationnement s : myStationnements) {
                listegarages.add(s.getGarage());
            }
            return listegarages;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
        // Vrai si le dernier stationnement est en cours
	public boolean estDansUnGarage() {
            // S'il n'y a eu aucun stationnement avant : faux
            if (myStationnements.isEmpty()) {
                return false;
            }
            // S'il y a eu au moins un stationnement avant,
            // on vérifie s'il est en cours
            else {
		Stationnement dernierStationnement = this.getLastStationnement();
		return dernierStationnement.estEnCours();
            }
    }

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
        // Pas terminé !
        
	public void imprimeStationnements(PrintStream out) {
            // Pour chaque élément Garage de la liste 
		for (Stationnement s : myStationnements) {
                    System.out.println(s.getGarage().toString());
                    System.out.println(s.toString());
                }
        }
	

}
