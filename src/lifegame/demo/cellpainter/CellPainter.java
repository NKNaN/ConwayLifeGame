package lifegame.demo.cellpainter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class CellPainter {
	public static void main(String[] args) {
		EnvFrame environment = new EnvFrame("Convey's 2D Environment of Life 1.0");
		environment.loadEnv();
		windowClose(environment);
		
	}
	public static void windowClose(JFrame f){
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
