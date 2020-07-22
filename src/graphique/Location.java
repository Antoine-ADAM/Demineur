package graphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Location {
	private int x,y;
	private static Paneau jp;
	private static int panelX,panelY;
	private static double RImage;
	private static ArrayList<ArrayList<Case>> Cases;
	Location(int x,int y){
		this.setX(x);
		this.setY(y);
	}
	public Location() {
		// TODO Auto-generated constructor stub
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int[] getCo(){
		return new int[]{getCx(),getCy()};
	}
	public int getCx(){
		return (int)((x-8)/(20*RImage));
	}
	public int getCy(){
		return (int)((y-30)/(20*RImage));
	}
	public boolean isValid(){
		int[] Co = getCo(); 
		if(Co[0]>=0&&Co[1]>=0&&Co[0]<=panelX&&Co[1]<=panelY){
			return true;
		}else{
			return false;
		}
	}
	public static void addImage(imageCase img, Case c, Graphics g){
		g.drawImage(Location.resize(img.getValue()),c.getX()*((int)Math.round(Location.getRImage()*20)),c.getY()*((int)Math.round(Location.getRImage()*20)),null);
	}
	public static int coordo(int i){
		return (int)Math.round(Location.getRImage()*i);
	}
	public Case getCaseCo(){
		final int[] i = getCo();
		return Cases.get(i[1]).get(i[0]);
	}
	public static void updateRimage(){
		final double Rpanel = (panelX+1)/(double)(panelY+1);
		System.out.println("Rpanel"+Rpanel);
		final Dimension dimWin = jp.getSize();
		System.out.println(((dimWin.getHeight()*Rpanel)/(panelY+1))+"}=={"+((dimWin.getWidth())/(panelX+1)));
		if(((dimWin.getHeight())/(panelY+1))<((dimWin.getWidth())/(panelX+1))){
			RImage =Math.round((dimWin.height*0.98)/(double)(20*(panelY+1))*1000)/1000.0; //-20 securiter a enlever
		}else{
			RImage=Math.round((dimWin.width*0.98)/(double)(20*(panelX+1))*1000)/1000.0;
		}
		System.out.println("RImage:"+RImage);
		System.out.println("y=>"+dimWin.getHeight()+"| x=>"+dimWin.getWidth());
	}
	public static BufferedImage resize(Image src) {
        BufferedImage result = new BufferedImage((int)Math.round(20*RImage),(int)Math.round(20*RImage), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(src, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();
        return result;
    }
	public static double getRImage() {
		return RImage;
	}
	public static void setRImage(double rImage) {
		RImage = rImage;
	}
	public static int getPanelY() {
		return panelY;
	}
	public static void setPanelY(int panelY) {
		Location.panelY = panelY;
	}
	public static int getPanelX() {
		return panelX;
	}
	public static void setPanelX(int panelX) {
		Location.panelX = panelX;
	}
	public static ArrayList<ArrayList<Case>> getCases() {
		return Cases;
	}
	public static void setCases(ArrayList<ArrayList<Case>> cases) {
		Cases = cases;
	}
	public static Paneau getJp() {
		return jp;
	}
	public static void setJp(Paneau jp) {
		Location.jp = jp;
	}
}
