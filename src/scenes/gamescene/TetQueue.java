package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import processing.core.PFont;
import tetrominos.*;
import tiles.Tile;
import utils.ColorConstants;

public class TetQueue {
	private Queue<Tetromino> queue;
	private int size;
	
	public TetQueue(int size) {
		this.size = size;
		queue = new LinkedList<>();
		queue.addAll(generate7Bag());
	}

	/**
	 * Returns the next Tetromino in the queue and removes it from the queue.
	 * @return the Tetromino in the front of the queue.
	 */
	public Tetromino getNext() {
		Tetromino nextTet = queue.remove();
		if (queue.size() < this.size) {
			queue.addAll(generate7Bag());
		}
		return nextTet;
	}
	
	/**
	 * Generates a new set containing one of each of the seven Tetrominos, in a random order 
	 * @return A HashSet of 7 Tetrominos
	 */
	public static HashSet<Tetromino> generate7Bag() {
		HashSet<Tetromino> bag = new HashSet<>();
		bag.add(new TetI());
		bag.add(new TetJ());
		bag.add(new TetL());
		bag.add(new TetO());
		bag.add(new TetS());
		bag.add(new TetT());
		bag.add(new TetZ());
		
		return bag;
	}
	

	/**
	 * Displays on the screen the next Tetrominos in the queue. The number of pieces to display is defined by the "size" variable.
	 * @param w The PApplet to display the queue on
	 */
	public void display(PApplet w) {
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
				
		w.push();
		w.noStroke();
		
		w.fill(r,g,b);
		w.rect(0, 0, 4*Tile.SIZE + 25, (3*this.size + 1)*Tile.SIZE+30, 0, 9, 9, 0);
		
		w.fill(0);
		w.rect(0, 25, 4*Tile.SIZE + 20, (3*this.size + 1)*Tile.SIZE, 0, 9, 9, 0);
		
		
		//display the text
		w.textFont(font, 16);
		w.fill(0);
		w.text("NEXT", 42, 20);
		
		
		//display the shapes
		w.translate(Tile.SIZE + 10, 3*Tile.SIZE+5);
		
		int i=0;
		for (Tetromino t : this.queue) {
			if (i >= this.size) break;
			t.displayHolder(w);
			w.translate(0, 3*Tile.SIZE);
			i ++;
		}
		
		w.pop();
	}
	
}
