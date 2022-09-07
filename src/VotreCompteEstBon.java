import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class VotreCompteEstBon {

  @Test
  public void testObjectifImpossible() {
    CompteEstBon tirage = new CompteEstBon(new Integer[] { 1, 2 }, 10);

    assertNull(tirage.getSolution());
  }

  @Test
  public void testSequenceSimple() {
    CompteEstBon tirage = new CompteEstBon(new Integer[] { 4, 100, 2 }, 46);

    assertArrayEquals(
        new String[] { "100 / 2 = 50", "50 - 4 = 46" },
        tirage.getSolution());
  }

  @Test
  public void testMultiTraitements() {
    CompteEstBon tirage = new CompteEstBon(new Integer[] { 4, 1, 101, 2 }, 33);
    String[] solution = tirage.getSolution();

    Arrays.sort(solution);
    assertArrayEquals(
        new String[] {
            "101 - 2 = 99",
            "4 - 1 = 3",
            "99 / 3 = 33"
        },
        solution);
  }

}
