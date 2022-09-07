package validation;

import modeles.Participant;

public class ValidateurParticipant implements Validateur<Participant> {
  @Override
  public Participant convertir(String valeur) throws ValidationException {
    if (valeur.length() == 0) {
      throw new ValidationException("Le nom de participant ne peut être vide");
    }
    return new Participant(valeur);
  }

}
