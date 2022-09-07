import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class DesamorcerLaBombe {

	@Test
	public void testTableauVide() {
		Integer [] tableau = {  };
		TriParPivot tri = new TriParPivot();
		
		tri.trier(tableau);
		assertArrayEquals(new Integer[] {}, tableau);
	}

	@Test
	public void testTableauALenvers() {
		Integer [] tableau = new Integer[50000];
		Integer [] attendu = remplirALEnversDupliquerEtTrier(tableau);
		TriParPivot tri = new TriParPivot();
		
		tri.trier(tableau);
		assertArrayEquals(attendu, tableau);
	}
	
	@Test
	public void testGrandTableauAleatoire() {
		Integer [] tableau = new Integer[100000];
		Integer [] attendu = remplirDupliquerEtTrier(tableau);
		TriParPivot tri = new TriParPivot();
		
		tri.trier(tableau);
		assertArrayEquals(attendu, tableau);
	}
	
	private Integer[] dupliquerEtTrier(Integer[] tableau) {
		Integer[] croissant = tableau.clone();
		
		Arrays.sort( croissant );
		return croissant;
	}

	private Integer[] remplirALEnversDupliquerEtTrier(Integer[] tableau) {
		for(int i=0; i<tableau.length; i++) {
			tableau[i] = tableau.length - i;
		}
		return dupliquerEtTrier(tableau);
	}		
	
	private Integer[] remplirDupliquerEtTrier(Integer[] tableau) {
		int max = tableau.length;
		Random hasard = new Random(0); 
		
		for(int i=0; i<tableau.length; i++) {
			tableau[i] = hasard.nextInt(max);
		}
		return dupliquerEtTrier(tableau);
	}

}
