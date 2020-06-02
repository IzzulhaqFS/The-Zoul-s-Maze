package maze;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Maze extends JFrame {

	public static int rows = 20;
	public static int columns = 20;
	public static int panelSize = 25;
	public static int map[][] = new int[columns][rows];
	public static int endLevelLoc;
	Player p;
	Opponent o;
	int current;
	
	public Maze(String str) {
		loadMap(str);
		this.setResizable(false);
		this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
		this.setTitle("The Zoul's Maze");
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				revalidate();
				repaint();
				//Player Movement
				if(key == KeyEvent.VK_W) {
					p.moveUp();
					for(int i = 0; i < 2; i++) {
						current = calculate(p.x, p.y, o.x, o.y);
						if(calculate(p.x, p.y, o.x - 1, o.y) <= current && Maze.map[o.x - 1][o.y] == 1) o.moveLeft();
						else if(calculate(p.x, p.y, o.x, o.y - 1) <= current && Maze.map[o.x][o.y - 1] == 1) o.moveUp();
						else if(calculate(p.x, p.y, o.x, o.y + 1) <= current && Maze.map[o.x][o.y + 1] == 1) o.moveDown();
						else if(calculate(p.x, p.y, o.x + 1, o.y) <= current && Maze.map[o.x + 1][o.y] == 1) o.moveRight();
					}
				}
				if(key == KeyEvent.VK_A) {
					p.moveLeft();
					for(int i = 0; i < 2; i++) {
						current = calculate(p.x, p.y, o.x, o.y);
						if(calculate(p.x, p.y, o.x - 1, o.y) <= current && Maze.map[o.x - 1][o.y] == 1) o.moveLeft();
						else if(calculate(p.x, p.y, o.x, o.y - 1) <= current && Maze.map[o.x][o.y - 1] == 1) o.moveUp();
						else if(calculate(p.x, p.y, o.x, o.y + 1) <= current && Maze.map[o.x][o.y + 1] == 1) o.moveDown();
						else if(calculate(p.x, p.y, o.x + 1, o.y) <= current && Maze.map[o.x + 1][o.y] == 1) o.moveRight();
					}
				}
				if(key == KeyEvent.VK_S) {
					p.moveDown();
					for(int i = 0; i < 2; i++) {
						current = calculate(p.x, p.y, o.x, o.y);
						if(calculate(p.x, p.y, o.x - 1, o.y) <= current && Maze.map[o.x - 1][o.y] == 1) o.moveLeft();
						else if(calculate(p.x, p.y, o.x, o.y - 1) <= current && Maze.map[o.x][o.y - 1] == 1) o.moveUp();
						else if(calculate(p.x, p.y, o.x, o.y + 1) <= current && Maze.map[o.x][o.y + 1] == 1) o.moveDown();
						else if(calculate(p.x, p.y, o.x + 1, o.y) <= current && Maze.map[o.x + 1][o.y] == 1) o.moveRight();
					}
				}
				if(key == KeyEvent.VK_D) {
					p.moveRight();
					for(int i = 0; i < 2; i++) {
						current = calculate(p.x, p.y, o.x, o.y);
						if(calculate(p.x, p.y, o.x - 1, o.y) <= current && Maze.map[o.x - 1][o.y] == 1) o.moveLeft();
						else if(calculate(p.x, p.y, o.x, o.y - 1) <= current && Maze.map[o.x][o.y - 1] == 1) o.moveUp();
						else if(calculate(p.x, p.y, o.x, o.y + 1) <= current && Maze.map[o.x][o.y + 1] == 1) o.moveDown();
						else if(calculate(p.x, p.y, o.x + 1, o.y) <= current && Maze.map[o.x + 1][o.y] == 1) o.moveRight();
					}
				}
				
				//System.out.println("x = " + o.x + " y = " + o.y);
				//System.out.println("x = " + p.x + " y = " + p.y + " " + current);
					
				if(p.x == columns-1 && p.y == endLevelLoc) {
					JOptionPane.showMessageDialog(null, "Congratulations, You've Cleared the Maze", "End Game", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new MainMenu();
				}
				
				if(p.x == o.x && p.y == o.y) {
					JOptionPane.showMessageDialog(null, "Game Over", "End Game", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new MainMenu();
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Option", JOptionPane.YES_NO_OPTION);
				if(confirmation == JOptionPane.YES_OPTION) {
					dispose();
					new MainMenu();
				}
			}
		});
		
		this.setLocationRelativeTo(null);
		//Create Player
		p = new Player();
		p.setVisible(true);
		this.add(p);
		//Create Opponent
		o = new Opponent();
		o.setVisible(true);
		this.add(o);
		//Color Map
		for(int y = 0; y < columns;y++) {
			for(int x = 0; x < rows; x++) {
				Tile tile = new Tile(x, y);
				tile.setSize(panelSize, panelSize);
				tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
				if(map[x][y] == 0) {
					tile.setBackground(Color.GRAY);
				}else {
					tile.setBackground(Color.WHITE);
					tile.setWall(false);
					if(x == 0) {
						p.setLocation((x*panelSize)+23, (y*panelSize)+25);
						p.y = y;
					}
					if(x == columns-1) {
						endLevelLoc = y;
						o.setLocation((x*panelSize)+23, (y*panelSize)+25);
						o.x = x;
						o.y = y;
					}
				}
				
				tile.setVisible(true);
				this.add(tile);
			}
		}
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainMenu();
	}
	
	public void loadMap(String str){
        try{
            BufferedReader br = new BufferedReader(new FileReader(str));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String mapStr = sb.toString();
            
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                        //System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }
    }
	
	public int calculate(int px, int py, int ox, int oy) {
		int cx = px - ox;
		int cy = py - oy;
		
		return (cx * cx) + (cy * cy);
	}

}
