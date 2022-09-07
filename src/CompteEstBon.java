import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.BinaryOperator;

public class CompteEstBon extends RecursiveAction {
	private final static int PARALLELISME = 16;private final static Operation[] operations={
		new Operation('*', (a, b) -> a != 1 && b != 1     ? a * b : 0 ),
		new Operation('/', (a, b) -> b != 1 && a % b == 0 ? a / b : 0 ),
		new Operation('+', (a, b) -> a + b),
		new Operation('-', (a, b) -> a > b ? a - b : 0)
	};

	private final Tirage tirage;
	private final String solution;

	public CompteEstBon(Tirage tirage) {
		this(tirage, "");
	}

	private CompteEstBon(Tirage tirage, String solution) {
		this.tirage = tirage;
		this.solution = solution;
	}

	public void afficherSolutions() {
		ForkJoinPool pool = new ForkJoinPool(PARALLELISME);

		pool.invoke(this);
	}

	@Override
	protected void compute() {
		for(int i = 0; i<tirage.taille(); i++) {
			for(int j = 0; j<tirage.taille(); j++) {
				if(i!=j) {
					essayerOperations(i, j);
				}
			}
		}	
	}

	private void essayerOperations(int indiceTerme1, int indiceTerme2) {
		List<CompteEstBon> pistes = new ArrayList<>();
		
		for(Operation op : operations) {
			int total = op.calculer(
				tirage.get(indiceTerme1),
				tirage.get(indiceTerme2)
			);
			String nouvelleSolution = String.format(
				"%s%d %c %d = %d\n",
				solution,
				tirage.get(indiceTerme1),
				op.getSymbole(),
				tirage.get(indiceTerme2),
				total
			);
			if(total == tirage.getObjectif()) {				
				System.out.println(nouvelleSolution);
			}
			else if(total != 0) {
				pistes.add(new CompteEstBon(
					tirage.modifier(indiceTerme1, indiceTerme2, total), 
					nouvelleSolution
				));
			}
		}
		invokeAll(pistes);
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

	private static final long serialVersionUID = 1L;
}
