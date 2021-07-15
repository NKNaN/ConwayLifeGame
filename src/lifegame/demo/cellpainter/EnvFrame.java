package lifegame.demo.cellpainter;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("all")
public class EnvFrame extends JFrame implements ActionListener{
	private boolean begin = false;

	private boolean clearCells = false;
	
	private Cell2D[][] cellList = new Cell2D[160][90];
	
	public EnvFrame(String title){
		super(title);
	}
	public void loadEnv(){
		// initialize the frame
		this.setBounds(200,200,1700,900);
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		this.add(new PaintPanel());
		this.add(new SelectionPanel());
		this.setVisible(true);
		this.setResizable(false);
		
		// initialize all the grids to Cell2D(the outside bounds of grids are set to be unavailable for life)
		for(int x=1,i=0;x<1600;x+=10,i++){
			for(int y=1,j=0;y<900;y+=10,j++){
				cellList[i][j] = new Cell2D(x,y,0,8);
			}
		}
		// initialize the Adam Cells(by MouseLitsener)
//		cellList[5][32].setStatusByHand(true);
//		cellList[6][32].setStatusByHand(true);
//		cellList[4][32].setStatusByHand(true);
//		cellList[8][53].setStatusByHand(true);
//		cellList[9][53].setStatusByHand(true);
//		cellList[10][53].setStatusByHand(true);
//		cellList[9][54].setStatusByHand(true);
//		cellList[10][54].setStatusByHand(true);
//		cellList[11][54].setStatusByHand(true);
		timer.start();
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {

		if(begin){
			for(int i=1;i<159;i++){
				for(int j=1;j<89;j++){
					int surrLivingCount = countSurrLivings(i,j,cellList);
//					if(surrLivingCount!=0) System.out.println(""+i+", "+j+": "+surrLivingCount+" --- "+cellList[i][j].isLiving());
					cellList[i][j].setSurrLivings(surrLivingCount);
					cellList[i][j].setSurrDeads(8-surrLivingCount);
				}
			}
			for(int i=1;i<159;i++){
				for(int j=1;j<89;j++){
					cellList[i][j].setStatus();
				}
			}
			repaint();
		}
		
		if(clearCells&&!begin){
//			System.out.println("here__________________");
			for(int i=1;i<159;i++){
				for(int j=1;j<89;j++){
					cellList[i][j].setStatusByHand(false);;
				}
			}
			repaint();
			clearCells = !clearCells;
		}
		timer.start();
	}
	
	public int countSurrLivings(int a, int b, Cell2D[][] cList){
		int count=0;
		Cell2D c = cList[a][b];
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(i==0&&j==0)
					continue;
				if(cList[a+i][b+j].isLiving())
					count++;
			}
		}
		return count;
	}
	
	Timer timer = new Timer(100, this);
	
	private class PaintPanel extends JPanel{
		public PaintPanel(){
			setBounds(70,0,1600,900);
			setBackground(Color.WHITE);
			this.addMouseListener(new Listener());
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// paint grids
			g.setColor(Color.GRAY);
			// vertical grids
			for(int x=10;x<=1600;x+=10)
				g.drawLine(x, 0, x, 900);
			// horizontal grids
			for(int y=10;y<=900;y+=10)
				g.drawLine(0, y, 1600, y);
			
			for(int i=1;i<159;i++){
				for(int j=1;j<89;j++){
					if(cellList[i][j].isLiving()){
						g.setColor(Color.BLACK);
					}else{
						g.setColor(Color.WHITE);
					}
					g.fillRect(cellList[i][j].getPositionX(), cellList[i][j].getPositionY(), Cell2D.cellLength, Cell2D.cellLength);
				}
			}
			
//			System.out.println("Here!!!!!!!!!!!!!!!");
		}
		
	}
	private class SelectionPanel extends JPanel{
		public SelectionPanel(){
			super(new GridLayout(3,1));
			this.setBounds(0,0,50,900);
			this.setBackground(Color.GRAY);
			Button b1 = new Button("Start");
			b1.setFont(new Font("Courier New",Font.BOLD,12));
			b1.addActionListener(new ButtonListener());
			Button b2 = new Button("Stop");
			b2.setFont(new Font("Courier New",Font.BOLD,12));
			b2.addActionListener(new ButtonListener());
			Button b4 = new Button("Clear");
			b4.setFont(new Font("Courier New",Font.BOLD,12));
			b4.addActionListener(new ButtonListener());
			this.add(b1);
			this.add(b2);
			this.add(b4);
		}
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if("Start".equals(e.getActionCommand())){
				begin = true;
			}else if("Stop".equals(e.getActionCommand())){
				begin = false;
//				beginToSet = true;
//				mousePoints.clear();
			}else if("Set".equals(e.getActionCommand())){
//				beginToSet = false;
			}else if("Clear".equals(e.getActionCommand())){
				clearCells = true;
			}
		}

	}
	
	
	private class Listener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println(e.getX());
//			System.out.println(e.getY());
			int i = e.getX()/10;
			int j = e.getY()/10;
			if(i>=0 && i<160 && j>=0 && j<90)
				cellList[i][j].setStatusByHand(!cellList[i][j].isLiving());
//			System.out.println("adammmmmmmmmmmmmm");
			repaint();
		}
		
	}
}
