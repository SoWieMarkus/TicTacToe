package markus.wieland.tictactoe;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.grid.GridGameStateField;

public class TicTacToeGameStateField extends GridGameStateField {

    private final int value;

    public TicTacToeGameStateField(Coordinate coordinate, int value) {
        super(coordinate);
        this.value = value;
    }

    public TicTacToeGameStateField(Coordinate coordinate) {
        super(coordinate);
        this.value = GameBoardField.FREE;
    }

    public int getValue() {
        return value;
    }
}
