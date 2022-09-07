import java.util.function.BinaryOperator;

public class CompteEstBon {
  private final static Operation[] operations={
		new Operation('*', (a, b) -> a != 1 && b != 1     ? a * b : 0 ),
		new Operation('/', (a, b) -> b != 1 && a % b == 0 ? a / b : 0 ),
		new Operation('+', (a, b) -> a + b),
		new Operation('-', (a, b) -> a > b ? a - b : 0)
	};

  private final Tirage tirage;

  public CompteEstBon(Tirage tirage) {
    this.tirage = tirage;
  }

  public void afficherSolutions() {
		for(int i = 0; i<tirage.taille(); i++) {
			int terme1 = tirage.retirer(i);
			
			for(int j = 0; j<tirage.taille(); j++) {
				int terme2 = tirage.retirer(j);
				
				essayerOperations(terme1, terme2);
				tirage.ajouter(j, terme2);
			}
			tirage.ajouter(i, terme1);
		}
	}

  private void essayerOperations(int terme1, int terme2) {
		for(Operation op : operations) {
			int total = op.calculer(terme1, terme2);
			
			if(total == tirage.getObjectif()) {
				// TODO : Afficher votre solution ici
				System.out.println("Solution trouvée");
			}
			else if(total != 0) {
				tirage.ajouterFin(total);
				afficherSolutions();
				tirage.retirerFin();
			}
		}
	}

  private static class Operation
  {
    private final char symbole;
    private final BinaryOperator<Integer> operateur;

    public Operation(char symbole, BinaryOperator<Integer> operateur) {
			this.symbole = symbole;
			this.operateur = operateur;
		}

    public int getSymbole() {
      return symbole;
    }

    public int calculer(int op1, int op2) {
			return operateur.apply(op1, op2);
		}
  }
}
