package scenes.gamescene;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;
import tiles.*;
import utils.ColorConstants;
import utils.Vector;

public class Grid {
	
	public class Line {
		
		private Tile[] line;
		
		public Line() {
			line = new Tile[10];
		}
		
		public Line(int width) {
			line = new Tile[width];
		}
		
		public void initialize(int width) {
			for (int i = 0; i < width; i++) {
				line[i] = new NullTile();
			}
		}
		
		protected Tile[] getLine() {
			return this.line;
		}
		
		protected void setLine(Tile[] line) {
			this.line = line;
		}
	}
	
	final public int height= 20, width = 10;
	private List<Line> table;
	
	public Grid() {
		table = new ArrayList<Line>();
		for (int i = 0; i < this.height+3; i++) {
			table.add(i, new Line(width));
		}
		for (Line line : table) {
			line.initialize(width);
		}
	}
	
	public Tile getTile(Vector vect) {
		return table.get(vect.getY()).getLine()[vect.getX()];
	}
	
	public Tile getTile(int posWidth, int posHeight) {
		return table.get(posHeight).getLine()[posWidth];
	}
	
	public void setTile(int posWidth, int posHeight, Tile tile) {
		table.get(posHeight).getLine()[posWidth] = tile;
	}
	
	/**
	 * Method used to check if some grid's lines are full and if the player has lost
	 * @param scoreManager: ScoreManager -> parameter used to update the score
	 * @return isLost: boolean
	 */
	public boolean checkLines(ScoreManager scoreManager) {
		int nbLines = 0;
		int index = 0;
		List<Integer> toSuppr = new ArrayList<Integer>();
		boolean isFull = true;
		boolean isLost = false;
		
		for (Line l: table) {
			// Check the 20 firsts lines
			if (index < 20) {
				for (int i = 0; i < this.width; i++) {
					if (l.getLine()[i].isNull()) {
						isFull = false;
					}
				}
				if (isFull) {
					nbLines++;
					toSuppr.add(index);
				}
				isFull = true;
				index++;
			// Check the 3 additional lines
			} else {
				for (int i = 0; i < this.width; i++) {
					if ( l.getLine()[i].isNull() == false) {
						isLost = true;
					}
				}
			}
		}

		if (!toSuppr.isEmpty()) {
			for (int i = toSuppr.size()-1; i >= 0; i--) {
				Line newLine = new Line(this.width);
				newLine.initialize(width);
				int idx = toSuppr.get(i); 
				table.remove(idx);
				table.add(22-i,newLine);
			}
		}
		
		scoreManager.update(nbLines);
		return isLost;
	}
	
	public void display(PApplet w) {
		w.push();
		
		w.noStroke();
		
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		w.fill(r,g,b);
		w.rect(0, 0, Tile.SIZE*this.width+10, Tile.SIZE*this.height+10);
		
		w.translate(5f, 5f);
		
		w.translate(0, 19*Tile.SIZE);
		
		int index = 0;
		for (Line line : table) {
			if (index < 20) {
				for (int i = 0; i < this.width; i++) {
					line.getLine()[i].display(w);;
					w.translate(Tile.SIZE, 0);
				}
				w.translate(-width*Tile.SIZE, -Tile.SIZE);
			}
			index++;
		}
		
		w.pop();
	}
}
