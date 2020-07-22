package graphique;

public enum configIG {
	INTERMEDIAIR(400,400,16,16,40);
	public int dimFx,dimFy,dimJx,dimJy,bonbeN;
	configIG(int a,int b,int c,int d,int e) {
		this.dimFx=a;
		this.dimFy=b;
		this.dimJx=c;
		this.dimJy=d;
		this.bonbeN=e;
	}
	public int[] getValue() {
		return new int[] {dimFx,dimFy,dimJx,dimJy,bonbeN};
	}
}
