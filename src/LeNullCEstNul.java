import java.util.Scanner;

public class LeNullCEstNul {
  public static void main(String [] args) {
		String [] menu = new String[] {
			"Inscrire un nouveau participant",
			"Démarrer la course",
			"Arrivée d'un participant",
			"Classement",
			"Gagnant-e",
			"Quitter"
		};
		Scanner clavier = new Scanner(System.in);
		boolean fini = false;
		
		Categorie categorie = args.length > 0 
			? new Categorie(args[0]) 
			: Categorie.HORS_CATEGORIE;
		Course course = new Course(categorie);
		
		while( !fini ) {
			int choix;
			
			do {
				for(int i=0; i<menu.length; i++) {
					System.out.printf("%d) %s\n", i+1, menu[i]);
				}
				System.out.printf("Votre choix (1-%d) : ", menu.length);
				try {
					choix = Integer.parseInt(clavier.next());						
				}
				catch(NumberFormatException e) {
					choix = 0;
				}				
			}
			while(choix < 1 || menu.length < choix);
			
			System.out.println();
			switch(choix) {
			case 1 : inscrire  (course, clavier	); break;
			case 2 : demarrer  (course			    ); break;
			case 3 : arrivee   (course, clavier	); break;
			case 4 : classement(course			    ); break;
			case 5 : vainqueur (course			    ); break;
			case 6 : fini = true; 
			}
		}
		System.out.println("Bye");
		clavier.close();
	}

  public static void inscrire(Course course, Scanner clavier) {
    System.out.print("Nom du participant : ");
    try {
      course.inscrire(new Participant(clavier.next()));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public static void demarrer(Course course) {
		try {
			course.demarrer();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}	
	}

  public static void arrivee(Course course, Scanner clavier) {
		System.out.print("Nom du participant : ");
		try {
			course.passerLaLigneDArrivee(new Participant(clavier.next()));
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}

  public static void classement(Course course) {
		switch(course.getEtat()) {
		case Course.INSCRIPTION:
			System.err.println("Inscriptions en cours");
			break;
		case Course.EN_COURS:
			System.err.println("Course non terminée");
			break;
		case Course.ARRIVEE:
			System.out.println("CLASSEMENT :");
			int position = 1;
			
			for(Participant participant : course.getClassement()) {
				System.out.printf("%4d) %s\n", position++, participant);				
			}
			System.out.println();
			break;
		}
	}

  public static void vainqueur(Course course) {
		if(course.getEtat() != Course.ARRIVEE) {
			System.err.println("Inscriptions ou course en cours");
		}
		else {
			System.out.println(course.getGagnant());
		}
	}
}
