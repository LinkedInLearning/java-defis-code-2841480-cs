import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class LesTraitementsBienParalleles {
  @Test
  public void testGrandTableauAleatoire() {
    Integer[] tableau = new Integer[5000000];
    Integer[] attendu = remplirDupliquerEtTrier(tableau);
    Tri tri = new TriParPivotRec(16);
    // Tri tri = new TriParPivotMultithreads(16);
    // Tri tri = new TriParPivotForkJoin(16);

    tri.trier(tableau);
    assertArrayEquals(attendu, tableau);
  }

  private Integer[] dupliquerEtTrier(Integer[] tableau) {
    Integer[] croissant = tableau.clone();

    Arrays.sort(croissant);
    return croissant;
  }

  private Integer[] remplirDupliquerEtTrier(Integer[] tableau) {
    int max = tableau.length;
    Random hasard = new Random(0);

    for (int i = 0; i < tableau.length; i++) {
      tableau[i] = hasard.nextInt(max);
    }
    return dupliquerEtTrier(tableau);
  }

}
