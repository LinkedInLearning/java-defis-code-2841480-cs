import java.util.function.Consumer;

import modeles.Categorie;
import modeles.Participant;
import modeles.Course;
import validation.ValidateurMenu;
import validation.ValidateurParticipant;
import validation.ValidationException;
import vues.Menu;
import vues.Vue;
import vues.VueClassement;
import vues.VueErreur;
import vues.VueMenu;
import vues.VueObjet;

public class EparpillerFaconPuzzle
{

	private final static Console console = new Console();
	private static boolean fini = false;

	public static void main(String [] args) {
		Course course = new Course(args.length > 0 ? new Categorie(args[0]) : null);

		Vue vueGagnant    = new VueObjet	   (() -> course.getGagnant   ());
		Vue vueClassement = new VueClassement(() -> course.getClassement()); 

		Menu menu = new Menu(
			new Menu.Element("Inscrire quelqu'un", () -> traiterParticipant(p -> course.inscrire(p)), () -> inscriptionEnCours( course )),
			new Menu.Element("Démarrer la course", () -> course.demarrer()										 			 , () -> inscriptionEnCours( course )),			
			new Menu.Element("Noter une arrivée" , () -> traiterParticipant(p -> course.passerLaLigneDArrivee(p))
																																															       , () -> course.getClassement()!=null),
			new Menu.Element("Classement"	, () -> console.afficher( vueClassement ), () -> courseTerminee( course )),
			new Menu.Element("Gagnant-e"	  , () -> console.afficher( vueGagnant    ), () -> courseTerminee( course )),			
			new Menu.Element("Quitter"		  , () -> fini=true)
		);
		Vue vueMenu = new VueMenu(menu);
		ValidateurMenu validMenu = new ValidateurMenu(menu);
		
		while( !fini ) {
			try {
				console.afficher( vueMenu );
				menu.getElement(console.lire(validMenu).intValue()).executer();						
			}
			catch(ValidationException e) {
				console.afficher(new VueErreur(e.getMessage()));
			}				
		}
		console.afficher(new VueObjet(()->"Bye"));
		console.fermer();
	}

	private static void traiterParticipant(Consumer<Participant> traitement) {
		try {
			traitement.accept(console.lire(new ValidateurParticipant()));
		}
		catch(ValidationException e) {
			console.afficher(new VueErreur(e.getMessage()));
		}
	}

	private static boolean inscriptionEnCours(Course c) {
		return c.getClassement()==null;
	}

	private static boolean courseTerminee(Course c) {
		return c.getClassement()!=null && c.getClassement().iterator().hasNext();
	}
}
