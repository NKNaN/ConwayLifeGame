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
		//��ǰϸ��Ϊ���״̬ʱ������Χ�Ĵ��ϸ������2��ʱ��������2��������ϸ���������״̬����ģ����������ϡ�٣�
		if(this.status&&surrLivings<2)
			this.status = false;
		//��ǰϸ��Ϊ���״̬ʱ������Χ��2����3�����ϸ��ʱ����ϸ������ԭ����
		if(this.status&&(surrLivings==2||surrLivings==3))
			this.status = true;
		//��ǰϸ��Ϊ���״̬ʱ������Χ�г���3�����ϸ��ʱ����ϸ���������״̬����ģ�������������ࣩ
		if(this.status&&surrLivings>3)
			this.status = false;
		//��ǰϸ��Ϊ����״̬ʱ������Χ��3�����ϸ��ʱ����ϸ����ɴ��״̬����ģ�ֳⷱ��
		if((!this.status)&&surrLivings==3)
			this.status = true;
	}
	public void setStatusByHand(boolean status){ 
		this.status=status;
	}
	
}