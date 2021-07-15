package lifegame.demo.cellpainter;

public class Cell2D{
	public static final int cellLength = 9;
	private int positionX;
	private int positionY;
	private int surrLivings;
	private int surrDeads;
	private boolean status;// true->living; false->dead
	
	public Cell2D(int positionX, int positionY, int surrLivings, int surrDeads) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.surrLivings = surrLivings;
		this.surrDeads = surrDeads;
		this.status = false;
		
	}
	public Cell2D() {
		super();
	}

	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getSurrLivings() {
		return surrLivings;
	}
	public void setSurrLivings(int surrLivings) {
		this.surrLivings = surrLivings;
	}
	public int getSurrDeads() {
		return surrDeads;
	}
	public void setSurrDeads(int surrDeads) {
		this.surrDeads = surrDeads;
	}
	public boolean isLiving() {
		return status;
	}
	public void setStatus() {
		//当前细胞为存活状态时，当周围的存活细胞低于2个时（不包含2个），该细胞变成死亡状态。（模拟生命数量稀少）
		if(this.status&&surrLivings<2)
			this.status = false;
		//当前细胞为存活状态时，当周围有2个或3个存活细胞时，该细胞保持原样。
		if(this.status&&(surrLivings==2||surrLivings==3))
			this.status = true;
		//当前细胞为存活状态时，当周围有超过3个存活细胞时，该细胞变成死亡状态。（模拟生命数量过多）
		if(this.status&&surrLivings>3)
			this.status = false;
		//当前细胞为死亡状态时，当周围有3个存活细胞时，该细胞变成存活状态。（模拟繁殖）
		if((!this.status)&&surrLivings==3)
			this.status = true;
	}
	public void setStatusByHand(boolean status){ 
		this.status=status;
	}
	
}