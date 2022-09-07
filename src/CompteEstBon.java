import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class CompteEstBon {
  private final static Operation[] operations = {
      new Operation('*', (a, b) -> a != 1 && b != 1 ? a * b : 0),
      new Operation('/', (a, b) -> b != 1 && a % b == 0 ? a / b : 0),
      new Operation('+', (a, b) -> a + b),
      new Operation('-', (a, b) -> a > b ? a - b : 0)
  };

  private LinkedList<Integer> nombres;
  private final int objectif;

  public CompteEstBon(Integer[] nombres, int objectif) {
    this.nombres = new LinkedList<Integer>(Arrays.asList(nombres));
    this.objectif = objectif;
  }

  public String[] getSolution() {
    return getSolution(0);
  }

  private String[] getSolution(int profondeur) {
    return pourChaqueNombre(
        terme1 -> pourChaqueNombre(
            terme2 -> essayerOperations(terme1, terme2, profondeur)));
  }

  private String[] essayerOperations(int terme1, int terme2, int profondeur) {
    String[] solution = null;

    for (Operation op : operations) {
      int total = op.calculer(terme1, terme2);

      if (total == objectif) {
        solution = new String[profondeur + 1];
      } else if (total != 0) {
        nombres.addLast(total);
        solution = getSolution(profondeur + 1);
        nombres.removeLast();
      }
      if (solution != null) {
        solution[profondeur] = String.format(
            "%d %c %d = %d",
            terme1, op.getSymbole(), terme2, total);
        break;
      }
    }
    return solution;
  }

  private String[] pourChaqueNombre(Function<Integer, String[]> traitement) {
    String[] solution = null;

    for (int i = 0; i < nombres.size() && solution == null; i++) {
      int valeur = nombres.remove(i);

      solution = traitement.apply(valeur);
      nombres.add(i, valeur);
    }
    return solution;
  }

  private static class Operation {
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
