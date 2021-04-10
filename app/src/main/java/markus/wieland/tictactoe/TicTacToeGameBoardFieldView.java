package markus.wieland.tictactoe;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.grid.GridGameBoardFieldView;
import markus.wieland.games.game.view.GameStateField;

public class TicTacToeGameBoardFieldView extends androidx.appcompat.widget.AppCompatButton implements GridGameBoardFieldView {

    private Coordinate coordinate;
    private int value;

    public static final char[] SYMBOLS = new char[]{' ', 'X', 'O'};

    public static final int PLAYER_1 = 0;
    public static final int PLAYER_2 = 1;

    public TicTacToeGameBoardFieldView(@NonNull Context context) {
        super(context);
    }

    public TicTacToeGameBoardFieldView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TicTacToeGameBoardFieldView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void update() {
        setText(String.valueOf(SYMBOLS[value + 1]));
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == GameBoardField.FREE;
    }

    @Override
    public void load(GameStateField stateField) {
        TicTacToeGameStateField ticTacToeGameStateField = (TicTacToeGameStateField) stateField;
        this.coordinate = ticTacToeGameStateField.getCoordinate();
        this.value = ticTacToeGameStateField.getValue();
    }

    public TicTacToeGameStateField getGameStateField() {
        return new TicTacToeGameStateField(coordinate, value);
    }
}
