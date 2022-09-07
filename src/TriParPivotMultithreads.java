public class TriParPivotMultithreads implements Tri {
	private int maxProfondeur;

	public TriParPivotMultithreads(int maxTaches) {
		this.maxProfondeur = 0;
		for (; maxTaches > 1; maxTaches /= 2) {
			this.maxProfondeur++;
		}
	}

	public <T extends Comparable<? super T>> void trier(T[] tableau) {
		trier(tableau, 0, tableau.length, 0);
	}

	private <T extends Comparable<? super T>> void trier(T[] tableau, int premier, int dernier, int profondeur) {
		if (premier >= dernier) {
			return;
		}
		int pos = premier;

		for (int fin = dernier - 1; pos < fin;) {
			permuter(
					tableau, pos + 1,
					tableau[pos + 1].compareTo(tableau[pos]) < 0 ? pos++ : fin--);
		}

		final int profondeurSuivante = profondeur + 1;
		final int positionPivot = pos;
		boolean creerTache = profondeur < maxProfondeur;
		Thread tache = null;

		if (creerTache) {
			tache = new Thread(() -> trier(tableau, premier, positionPivot, profondeurSuivante));
			tache.start();
		} else {
			trier(tableau, premier, positionPivot, profondeurSuivante);
		}
		trier(tableau, pos + 1, dernier, profondeurSuivante);
		if (creerTache)
			try {
				tache.join();
			} catch (InterruptedException e) {
				// TODO : loguer - processus interrompu
			}
	}

	private static <T> void permuter(T[] tableau, int indice1, int indice2) {
		T tmp = tableau[indice1];

		tableau[indice1] = tableau[indice2];
		tableau[indice2] = tmp;
	}
}
