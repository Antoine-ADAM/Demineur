package graphique;

public class Case {
	private Boolean isBonbe, isDecouver, isFlag, isBackgrondBlack;
	private int BonbeN, x, y;
	public Case(int x,int y) {
		isBonbe=false;
		isBackgrondBlack=false;
		isFlag=false;
		isDecouver=false;//changer
		BonbeN=0;
		this.x=x;
		this.y=y;
	}
	public Boolean getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(Boolean isFlag) {
		this.isFlag = isFlag;
	}
	public Boolean getIsBonbe() {
		return isBonbe;
	}
	public void setIsBonbe(Boolean isBonbe) {
		this.isBonbe = isBonbe;
	}
	public Boolean getIsDecouver() {
		return isDecouver;
	}
	public void setIsDecouver(Boolean isDecouver) {
		this.isDecouver = isDecouver;
	}
	public Boolean getIsBackgrondBlack() {
		return isBackgrondBlack;
	}
	public void setIsBackgrondBlack(Boolean isBackgrondBlack) {
		this.isBackgrondBlack = isBackgrondBlack;
	}
	public int getBonbeN() {
		return BonbeN;
	}
	public void setBonbeN(int bonbeN) {
		BonbeN = bonbeN;
	}
	public void addBonbeN() {
		BonbeN++;
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
}
