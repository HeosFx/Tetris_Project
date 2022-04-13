package grid;

import java.util.ArrayList;
import java.util.List;

import tiles.Tile;
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
		
		protected Tile[] getLine() {
			return this.line;
		}
		
		protected void setLine(Tile[] line) {
			this.line = line;
		}
	}
	
	
	final private int height= 20, width = 10;
	private List<Line> table;
	
	
	public Grid() {
		table = new ArrayList<Line>();
		for (int i = 0; i < this.height; i++) {
			table.add(new Line(width));
		}
	}
	
	public Tile getTile(Vector vect) {
		return table.get(vect.getY()).getLine()[vect.getX()];
	}
	
	public Tile getTile(int posHeight, int posWidth) {
		return table.get(posHeight).getLine()[posWidth];
	}
	
	public void display() {
		
	}
}
