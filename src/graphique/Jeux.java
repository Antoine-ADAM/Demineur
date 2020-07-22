package graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Jeux {
	private Paneau jp;
	private int Stade;
	private Graphics g;
	private int panelX, panelY, bonbeN;
	private Case aideS1;
	private int CaseRestante;
	private int Ncase;// a enlever !
	private ArrayList<ArrayList<Case>> Case = new ArrayList<>();
	public Jeux(Paneau jp) {
		this.setJp(jp);
		this.g = jp.getGraphics();
		this.panelX=39;
		this.panelY=15;
		this.bonbeN=99;
		this.Stade=0;
		Location.setPanelX(this.panelX);
		Location.setPanelY(this.panelY);
		Location.setJp(jp);
	}
	public void start() {
		Stade=1;
		jp.setSize(panelX*20, panelY*20);
		Ncase = (panelX+1)*(panelY+1);
		CaseRestante = Ncase;
		//creation dalle
		for(int y = 0; y<=panelY;y++) {
			ArrayList<Case> v = new ArrayList<>();
			for(int x=0;x<=panelX;x++) {
				Case c = new Case(x,y);
				if(y%2==0){
					if(x%2==0) {
						c.setIsBackgrondBlack(true);
					}
				}else {
					if(x%2!=0) {
						c.setIsBackgrondBlack(true);
					}
				}
				v.add(c);
			}
			Case.add(v);
		}
		Location.setCases(Case);
		Location.setRImage(1);
		// creation bonbe !
		for(int i = 0;i<=bonbeN;i++) {
			int x = (int) (Math.random()*(panelX+1));
			int y = (int) (Math.random()*(panelY+1));
			Case v = getCaseCo(x, y);
			if(v.getIsBonbe()) {
				i--;
			}else {
				v.setIsBonbe(true);
				for(int ix=x-1;ix<=x+1;ix++) {
					for(int iy=y-1;iy<=y+1;iy++) {
						if(iy >=0 && ix >= 0 && iy <=panelY && ix <=panelX) {
							getCaseCo(ix, iy).addBonbeN();
						}	
					}
				}
			}
		}
		//aide de debut
		ArrayList<Case> possible = new ArrayList<>();
		for(ArrayList<Case> iaa : Case){
			for(Case iab : iaa){
				if(iab.getBonbeN()==0)possible.add(iab);
			}
		}
		aideS1 = possible.get((int)(Math.random()*possible.size()));
		//updateGraphique();
		jp.updateUI();
		Stade=2;
	}
	private void removeCase(Case c){
		if(c.getIsDecouver() || Stade==3)return;
		CaseRestante--;
		c.setIsDecouver(true);
		if(CaseRestante==(bonbeN+1))Fin(true);
		System.out.println("Case restante:"+(CaseRestante-(bonbeN+1))+"/"+(Ncase-(bonbeN+1))+" | x:"+c.getX()+",y:"+c.getY());
	}
	public void updateGraphique() {
		if(Stade == 3) {
			
		}
		Location.updateRimage();
		Iterator<ArrayList<Case>> ic = Case.iterator();
		//jp.removeAll();
		g.setFont(new Font("Eras Bold ITC", Font.BOLD, Location.coordo(20)));
		while(ic.hasNext()) {
			for(Case c : ic.next()) {
				if(c.getIsDecouver()) {
					Location.addImage(imageCase.FONT_C, c,g);
					if(!c.getIsBonbe()) {
						if(c.getBonbeN() != 0) {
							switch(c.getBonbeN()) {
								case 1:
									g.setColor(Color.BLUE);
									break;
								case 2:
									g.setColor(Color.green);
									break;
								case 3:
									g.setColor(Color.RED);
									break;
								case 4:
									g.setColor(Color.blue);
									break;
								case 5:
									g.setColor(Color.RED);
									break;
								case 6:
									g.setColor(Color.black);
									break;
								case 7:
									g.setColor(Color.black);
									break;
								case 8:
									g.setColor(Color.black);
									break;
							}
							g.drawString(c.getBonbeN()+"",c.getX()*Location.coordo(20)+Location.coordo(5),c.getY()*Location.coordo(20)+Location.coordo(17));
						}
					}else {//a enlever le else
						Location.addImage(imageCase.FONT_D, c,g);
						Location.addImage(imageCase.BONBE, c,g);
					}
				}else {
					if(c.getIsBackgrondBlack()) {
						Location.addImage(imageCase.FONT_B, c,g);
						//System.out.println((int)Math.round(c.getY()*20*Location.getRImage()));
						//System.out.println(c.getY()*20*Location.getRImage());
					}else {
						Location.addImage(imageCase.FONT_A, c,g);
					}
					if(c.equals(aideS1) && Stade == 2){
						g.setColor(Color.blue);
						g.drawString("➤",c.getX()*Location.coordo(20)+Location.coordo(2),c.getY()*Location.coordo(20)+Location.coordo(17));
					}
					if(c.getIsFlag()) {
						Location.addImage(imageCase.FLAG, c,g);
						if(Stade==3&&!c.getIsBonbe()) {
							Location.addImage(imageCase.CROIX, c,g);
						}
					}else if(Stade==3&&c.getIsBonbe()){
						Location.addImage(imageCase.BONBE, c,g);
					}
				}		
			}
		}
	}
	public void placeFlag(int ix,int iy) {
		Location co = new Location(ix, iy);
		if(!co.isValid() || Stade==3)return;
		Case c = co.getCaseCo();
		if(c.getIsFlag()) {
			c.setIsFlag(false);
		}else {
			c.setIsFlag(true);
		}
		jp.updateUI();
	}
	public void decouvre(int ax,int ay) {
		System.out.println("a");
		Location co = new Location(ax, ay);
		if(!co.isValid()||Stade==3)return;
		System.out.println("b");
		Case c = co.getCaseCo();
		if(c.getIsFlag())return;// /!\ Important /!\
		System.out.println("c");
		if(c.getIsBonbe()) {
			Fin(false);
			c.setIsDecouver(true);
		}
		if(!c.getIsDecouver()) {
			System.out.println("decouvre");
			removeCase(c);
			if(c.getBonbeN() == 0) {
				System.out.println("auto");
				ArrayList<Case> buffer = new ArrayList<>();
				buffer.add(c);
				while(!buffer.isEmpty()) {
					System.out.println(buffer.size());
					ArrayList<Case> buffer2 = new ArrayList<>();
					for(Case p : buffer) {
						for(int ix=p.getX()-1;ix<=p.getX()+1;ix++) {
							for(int iy=p.getY()-1;iy<=p.getY()+1;iy++) {
								if(iy >=0 && ix >= 0 && iy <=panelY && ix <=panelX) {
									Case ca = getCaseCo(ix, iy);
									if(ca.getBonbeN() == 0&&!buffer2.contains(ca)) {
										buffer2.add(ca);
									}
									removeCase(ca);
									ca.setIsFlag(false);
								}	
							}
						}
					}
					if(buffer.size() == buffer2.size()) {
						buffer.clear();
					}else {
						buffer = buffer2;
					}
				}
			}
		}else {
			System.out.println("autoDecouvre");
			int vf=0;
			ArrayList<Case> buffer = new ArrayList<>();
			for(int ix=c.getX()-1;ix<=c.getX()+1;ix++) {
				for(int iy=c.getY()-1;iy<=c.getY()+1;iy++) {
					if(iy >=0 && ix >= 0 && iy <=panelY && ix <=panelX) {
						Case vc=getCaseCo(ix, iy);
						if(vc.getIsFlag() && !vc.getIsDecouver()){
							vf++;
							System.out.println("Flag: x:"+vc.getX()+",y:"+vc.getY());
						}
						if(!vc.getIsDecouver()) buffer.add(vc);
					}	
				}
			}
			System.out.println("Flag:"+vf+"/"+c.getBonbeN());
			if(vf==c.getBonbeN()) {
				System.out.println("Buffer:"+buffer.size());
				for(Case i :buffer) {
					decouvre(i);
				}
			}
		}
		jp.updateUI();
	}
	public void decouvre(Case c) {
		if(c.getIsFlag())return;// /!\ Important /!\
		if(c.getIsBonbe()) {
			Fin(false);
			c.setIsDecouver(true);
		}
		if(!c.getIsDecouver()) {
			System.out.println("decouvre");
			removeCase(c);
			if(c.getBonbeN() == 0) {
				System.out.println("auto");
				ArrayList<Case> buffer = new ArrayList<>();
				buffer.add(c);
				while(!buffer.isEmpty()) {
					System.out.println(buffer.size());
					ArrayList<Case> buffer2 = new ArrayList<>();
					for(Case p : buffer) {
						for(int ix=p.getX()-1;ix<=p.getX()+1;ix++) {
							for(int iy=p.getY()-1;iy<=p.getY()+1;iy++) {
								if(iy >=0 && ix >= 0 && iy <=panelY && ix <=panelX) {
									Case ca = getCaseCo(ix, iy);
									if(ca.getBonbeN() == 0&&!buffer2.contains(ca)) {
										buffer2.add(ca);
									}
									removeCase(ca);
									ca.setIsFlag(false);
								}	
							}
						}
					}
					if(buffer.size() == buffer2.size()) {
						buffer.clear();
					}else {
						buffer = buffer2;
					}
				}
			}
		}else {
			System.out.println("autoDecouvre");
			int vf=0;
			ArrayList<Case> buffer = new ArrayList<>();
			for(int ix=c.getX()-1;ix<=c.getX()+1;ix++) {
				for(int iy=c.getY()-1;iy<=c.getY()+1;iy++) {
					if(iy >=0 && ix >= 0 && iy <=panelY && ix <=panelX) {
						Case vc=getCaseCo(ix, iy);
						if(vc.getIsFlag() && !vc.getIsDecouver()){
							vf++;
							System.out.println("Flag: x:"+vc.getX()+",y:"+vc.getY());
						}
						if(!vc.getIsDecouver()) buffer.add(vc);
					}	
				}
			}
			System.out.println("Flag:"+vf+"/"+c.getBonbeN());
			if(vf==c.getBonbeN()) {
				System.out.println("Buffer:"+buffer.size());
				for(Case i :buffer) {
					decouvre(i);
				}
			}
		}
		jp.updateUI();
	}
	public void Fin(boolean gg) {
		Stade=3;
		if(gg){
			System.out.println("GG");
		}else{
			System.out.println("Perdu!");
		}
	}
	public Case getCaseCo(int x,int y) {
		return Case.get(y).get(x);
	}
	public void setGraphics(Graphics f) {
		this.g=f;
	}
	public Paneau getJp() {
		return jp;
	}
	public void setJp(Paneau jp) {
		this.jp = jp;
	}
}
