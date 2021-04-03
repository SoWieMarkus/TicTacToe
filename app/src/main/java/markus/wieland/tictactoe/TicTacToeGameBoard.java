package markus.wieland.tictactoe;

import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.Line;
import markus.wieland.games.game.GameBoard;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.GameBoardInteractionListener;

public class TicTacToeGameBoard extends GameBoard<ConstraintLayout, TicTacToeGameBoardField, TicTacToeGameState> implements TicTacToeGameBoardFieldInteractionListener {

    public TicTacToeGameBoard(ConstraintLayout constraintLayout, GameBoardInteractionListener<TicTacToeGameBoardField> gameBoardInteractionListener) {
        super(3, 3, constraintLayout, gameBoardInteractionListener);
    }

    @Override
    protected void initializeLines() {
        for (int x = 0; x < 3; x++) {
            Line line = new Line();
            for (int y = 0; y < 3; y++) {
                line.add(new Coordinate(x, y));
            }
            lines.add(line);
        }

        for (int y = 0; y < 3; y++) {
            Line line = new Line();
            for (int x = 0; x < 3; x++) {
                line.add(new Coordinate(x, y));
            }
            lines.add(line);
        }

        Line diagonalTopLeftToBottomRight = new Line();
        diagonalTopLeftToBottomRight.add(new Coordinate(0, 0));
        diagonalTopLeftToBottomRight.add(new Coordinate(1, 1));
        diagonalTopLeftToBottomRight.add(new Coordinate(2, 2));
        lines.add(diagonalTopLeftToBottomRight);

        Line diagonalTopRightToBottomLeft = new Line();
        diagonalTopRightToBottomLeft.add(new Coordinate(0, 0));
        diagonalTopRightToBottomLeft.add(new Coordinate(1, 1));
        diagonalTopRightToBottomLeft.add(new Coordinate(2, 2));
        lines.add(diagonalTopRightToBottomLeft);
    }

    @Override
    protected void initializeFields() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                matrix.set(x, y, new TicTacToeGameBoardField(x, y, findViewById(layout.getContext(), "button" + y + "" + x), this));
            }
        }
    }

    @Override
    public boolean checkForWin(int player) {
        return false;
    }

    @Override
    public boolean checkForTie() {
        for (TicTacToeGameBoardField field : matrix) {
            if (field.getValue() == GameBoardField.FREE) return false;
        }
        return true;
    }

    @Override
    protected void loadGameState(TicTacToeGameState gameState) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                matrix.get(x, y).setValue(gameState.convert()[y][x]);
            }
        }
    }

    @Override
    public void onClick(int x, int y, TicTacToeGameBoardField field) {
        ((TicTacToeGameBoardFieldInteractionListener) gameBoardInteractionListener).onClick(x, y, field);
    }

    public void enableGameBoard(boolean enable){
        for (TicTacToeGameBoardField field : matrix) {
            field.getView().setEnabled(enable);
        }
    }

}
