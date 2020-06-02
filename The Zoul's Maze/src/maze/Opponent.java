package maze;

import java.awt.Color;

import javax.swing.JPanel;

public class Opponent extends JPanel {

	int x, y;
	//Player p;
	
	public Opponent() {
		this.setBackground(Color.RED);
		this.setSize(Maze.panelSize, Maze.panelSize);
	}
	
	public void moveLeft() {
		if(x > 0 && Maze.map[x-1][y] == 1) {
			this.setLocation(this.getX()-25, this.getY());
			x--;
		}
	}
	public void moveRight() {
		if(x < Maze.columns-1 && Maze.map[x+1][y] == 1) {
			this.setLocation(this.getX()+25, this.getY());
			x++;
		}
	}
	public void moveUp() {
		if(y > 0 && Maze.map[x][y-1] == 1) {
			this.setLocation(this.getX(), this.getY()-25);
			y--;
		}
	}
	public void moveDown() {
		if(y < Maze.rows-1 && Maze.map[x][y+1] == 1) {
			this.setLocation(this.getX(), this.getY()+25);
			y++;
		}
	}
	
//	public void HillClimbing(int m, int n) {
//		int i = x-m;
//		int j = y-n;
//		
//		int current = (i*i)+(j*j);
//		int a,b,c,d;
//		a = Calculate(i-1, j);
//		b = Calculate(i, j-1);
//		c = Calculate(i+1, j);
//		d = Calculate(i, j+1);
//		
//		if(a <= current) {
//			moveRight();
//		}
//		else if(b <= current) {
//			moveDown();
//		}
//		else if(c <= current) {
//			moveLeft();
//		}
//		else if(d <= current) {
//			moveUp();
//		}
//	}
//	
//	public int Calculate(int a, int b) {
//		return ((a*a)+(b*b));
//	}
}
