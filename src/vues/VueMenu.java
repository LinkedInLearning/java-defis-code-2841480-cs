package vues;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class VueMenu implements Vue {
  private Menu menu;

  public VueMenu(Menu menu) {
    this.menu = menu;
  }

  @Override
	public void rendre(OutputStreamWriter sortie, OutputStreamWriter erreur) throws IOException {
		int indice = 1;
		
		for(Menu.Element element : menu.getElements()) {
			sortie.write(
				element.estActif()
					? String.format( "%2d) %s\n", indice, element) 
					: String.format( " -) %s\n" , element
				)
			);
			indice++;
		}
		sortie.write("\n");
	}

}
