import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Equation {
  private static HashMap<Character, Operation> operations;

  static {
    operations = new HashMap<Character, Equation.Operation>();

    operations.put('*', new Operation(3, (a, b) -> a * b));
    operations.put('/', new Operation(3, (a, b) -> a / b));

    operations.put('+', new Operation(2, (a, b) -> a + b));
    operations.put('-', new Operation(2, (a, b) -> a - b));

    operations.put(')', new Operation(1));
    operations.put(CharacterIterator.DONE, new Operation(0));
  }

  public static double calculer(String equation) {
    return expression(new StringCharacterIterator(equation), 0);
  }

  private static double expression(CharacterIterator it, int priorite) {
    double total = operande(it);
    Operation op;

    while ((op = operationPrioritaire(it, priorite)) != null) {
      total = op.calculer(total, it);
    }
    return total;
  }

  private static Operation operationPrioritaire(CharacterIterator it, int priorite) {
    Operation op = operations.get(it.current());

    return op.getPriorite() > priorite ? op : null;
  }

  private static double operande(CharacterIterator it) {
    char caractere = it.current();

    it.next();
    return caractere == '(' ? expression(it, 0) : (double) (caractere - '0');
  }

  private static class Operation {
    private int priorite;
    private BinaryOperator<Double> operateur;

    public Operation(int priorite) {
      this(priorite, null);
    }

    public Operation(int priorite, BinaryOperator<Double> operateur) {
      this.priorite = priorite;
      this.operateur = operateur;
    }

    public double calculer(double total, CharacterIterator it) {
      it.next();
      return operateur == null
          ? total
          : operateur.apply(total, expression(it, getPriorite()));
    }

    public int getPriorite() {
      return priorite;
    }
  }
}
