import static org.junit.Assert.*;
import org.junit.Test;

public class SavezVousCompter {

  @Test
  public void testUnMot() {
    assertEquals(1, ComptageMots.compter("Bonjour"));
  }

  @Test
  public void testUnePhraseSimple() {
    assertEquals(4, ComptageMots.compter("Bonjour tout le monde"));
  }

  @Test
  public void testPhraseAvecPonctuations() {
    assertEquals(10, ComptageMots.compter("Hélas ! Et, sans ton sourire,\nQue ferai-je du matin ?"));
  }

  @Test
  public void testChaineVide() {
    assertEquals(0, ComptageMots.compter(""));
  }

  @Test
  public void testChaineAvecEspacements() {
    assertEquals(0, ComptageMots.compter(" \n\t\r "));
  }

}
