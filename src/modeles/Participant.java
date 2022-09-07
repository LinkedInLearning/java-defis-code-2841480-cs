package modeles;

public class Participant {
    private final String nom;
  
    public Participant(String nom) {
      this.nom = nom;
    }
  
    public String getNom() {
      return nom;
    }
  
    @Override
    public String toString() {
      return getNom();
    }
  
    @Override
    public boolean equals(Object obj) {
      return obj instanceof Participant
          && obj.toString().equals(nom);
    }
  
    @Override
    public int hashCode() {
      return nom.hashCode();
    }
  }
  