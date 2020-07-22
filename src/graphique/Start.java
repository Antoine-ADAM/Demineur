package graphique;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Start {

	public static void main(String[] args) {
		mafenetre fen = new mafenetre();
		fen.setVisible(true);
	}

}
class Paneau extends JPanel{
	Jeux j;
	public Paneau() {
		j = new Jeux(this);
		j.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		j.setGraphics(g);
		j.updateGraphique();
		System.out.println("_______");
	}
	public void clickDroit(int x,int y) {
		j.placeFlag(x, y);
	}
	public void clickGauche(int x,int y) {
		j.decouvre(x, y);
	}
}
class mafenetre extends JFrame implements MouseListener{
	public mafenetre () {
		setTitle("Démineur");
		//setResizable(false);
		reSize(1000,500);
		addMouseListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new Paneau();
		Container c = getContentPane();
		c.add(pan);
		
		//c.setLayout(new FlowLayout());//attention++++++++++++++++++++++++++++++++
	}
	private Paneau pan;
	public void reSize(int x, int y) {
		setSize(x,y);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public void mouseClicked(MouseEvent ev) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent ev) {
		// TODO Auto-generated method stub
		switch(ev.getButton()) {
			case 1:
				pan.clickGauche(ev.getX(), ev.getY());
				break;
			case 3:
				pan.clickDroit(ev.getX(), ev.getY());
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
