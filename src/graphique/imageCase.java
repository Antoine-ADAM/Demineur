package graphique;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum imageCase {
	BONBE(new ImageIcon(new Location().getClass().getResource("/demineur/bonbe.gif"))),
	FLAG(new ImageIcon(new Location().getClass().getResource("/demineur/flag.gif"))),
	FONT_A(new ImageIcon(new Location().getClass().getResource("/demineur/background_gris.gif"))),
	FONT_B(new ImageIcon(new Location().getClass().getResource("/demineur/background_gris_fonc.gif"))),
	FONT_C(new ImageIcon(new Location().getClass().getResource("/demineur/background_blanc.gif"))),
	FONT_D(new ImageIcon(new Location().getClass().getResource("/demineur/background_rouge.gif"))),
	CROIX(new ImageIcon(new Location().getClass().getResource("/demineur/croix.gif")));
	ImageIcon value;
	private imageCase(ImageIcon value){
		this.value=value;
	}
	public Image getValue() {
		return this.value.getImage();
	}
}
