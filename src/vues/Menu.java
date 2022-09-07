package vues;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Menu {
  private List<Element> elements;

  public Menu(Element... elements) {
    this.elements = Arrays.asList(elements);
  }

  public Iterable<Element> getElements() {
    return elements;
  }

  public Element getElement(int indice) {
    return elements.get(indice);
  }

  public static class Element {
    private String titre;
    private Runnable action;
    private Supplier<Boolean> activer;

    public Element(String titre, Runnable action, Supplier<Boolean> activer) {
      this.titre = titre;
      this.action = action;
      this.activer = activer;
    }

    public Element(String titre, Runnable action) {
      this(titre, action, () -> true);
    }

    public String toString() {
      return titre;
    }

    public void executer() {
      action.run();
    }

    public boolean estActif() {
      return activer.get().booleanValue();
    }
  }
}
