package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetJ extends Tetromino {
	
	public TetJ() {
		super(ColorConstants.BLUE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x-1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x+1, SpawningCoord.y));
	}

}
