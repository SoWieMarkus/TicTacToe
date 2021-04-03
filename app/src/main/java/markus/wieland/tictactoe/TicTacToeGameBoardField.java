package markus.wieland.tictactoe;

import android.view.View;
import android.widget.Button;

import markus.wieland.games.game.GameBoardField;

public class TicTacToeGameBoardField extends GameBoardField implements View.OnClickListener {

    private final int x;
    private final int y;

    private final TicTacToeGameBoardFieldInteractionListener listener;

    private static final char[] SYMBOLS = new char[]{' ', 'X', 'O'};

    public TicTacToeGameBoardField(int x, int y, View view, TicTacToeGameBoardFieldInteractionListener listener) {
        super(view);
        this.x = x;
        this.y = y;
        this.listener = listener;

        view.setOnClickListener(this);
    }

    public View getView() {
        return view;
    }

    @Override
    public void update() {
        Button gameBoardField = (Button) view;
        gameBoardField.setText(String.valueOf(SYMBOLS[getValue() + 1]));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void onClick(View v) {
        if (value != GameBoardField.FREE) return;
        listener.onClick(x, y, TicTacToeGameBoardField.this);
    }
}
