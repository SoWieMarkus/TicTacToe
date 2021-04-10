package markus.wieland.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import markus.wieland.games.ai.pattern.PatternMatcher;
import markus.wieland.games.ai.pattern.PatternMatchingLine;
import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.Line;
import markus.wieland.games.elements.Matrix;
import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.grid.GridGameBoardView;
import markus.wieland.games.persistence.GameState;
import markus.wieland.games.player.Player;

public class TicTacToeGameBoardView extends GridGameBoardView<TicTacToeGameBoardFieldView> implements View.OnClickListener {

    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 3;

    private TextView textViewPlayer1Name;
    private TextView textViewPlayer2Name;
    private TextView textViewPlayer1Value;
    private TextView textViewPlayer2Value;

    public TicTacToeGameBoardView(@NonNull Context context) {
        super(context);
    }

    public TicTacToeGameBoardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TicTacToeGameBoardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Matrix<TicTacToeGameBoardFieldView> getGameBoard() {
        return matrix;
    }

    @Override
    protected int getSizeX() {
        return SIZE_X;
    }

    @Override
    protected int getSizeY() {
        return SIZE_Y;
    }

    @Override
    protected void initializeLines() {
        for (int x = 0; x < SIZE_X; x++) {
            Line line = new Line();
            for (int y = 0; y < SIZE_Y; y++) {
                line.add(new Coordinate(x, y));
            }
            lines.add(line);
        }

        for (int y = 0; y < SIZE_Y; y++) {
            Line line = new Line();
            for (int x = 0; x < SIZE_X; x++) {
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
        diagonalTopRightToBottomLeft.add(new Coordinate(0, 2));
        diagonalTopRightToBottomLeft.add(new Coordinate(1, 1));
        diagonalTopRightToBottomLeft.add(new Coordinate(2, 0));
        lines.add(diagonalTopRightToBottomLeft);
    }

    @Override
    protected void initializeFields() {
        textViewPlayer2Value = findViewById(R.id.tictactoe_game_board_player_2_score);
        textViewPlayer2Name = findViewById(R.id.tictactoe_game_board_player_2_name);
        textViewPlayer1Value = findViewById(R.id.tictactoe_game_board_player_1_score);
        textViewPlayer1Name = findViewById(R.id.tictactoe_game_board_player_1_name);
    }

    public void showLines(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try{

                    for (Line line: lines) {
                        for (TicTacToeGameBoardFieldView view : matrix) {
                            view.setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        for (Coordinate coordinate : line) {
                            matrix.get(coordinate).setBackgroundColor(Color.parseColor("#ff0000"));
                        }
                        sleep(1000);
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected TicTacToeGameResult checkGameForFinish() {

        return null;
    }

    public boolean isCompleted() {
        for (TicTacToeGameBoardFieldView ticTacToeGameBoardFieldView : matrix) {
            if (ticTacToeGameBoardFieldView.isEmpty()) return false;
        }
        return true;
    }

    @Override
    protected void loadGameState(GameState gameState) {
        TicTacToeGameState ticTacToeGameState = (TicTacToeGameState) gameState;

        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                TicTacToeGameBoardFieldView fieldView = findViewById(getContext()
                        .getResources()
                        .getIdentifier("button" + y + "" + x, "id", getContext().getPackageName()));
                fieldView.setOnClickListener(this);
                matrix.set(new Coordinate(x, y), fieldView);
            }
        }

        for (TicTacToeGameStateField stateField : ticTacToeGameState) {
            matrix.get(stateField.getCoordinate()).load(stateField);
        }
    }

    public void updatePlayers(Player player1, Player player2) {
        textViewPlayer2Name.setText(player2.getPlayerName());
        textViewPlayer2Value.setText(String.valueOf(TicTacToeGameBoardFieldView.SYMBOLS[2]));
        textViewPlayer1Value.setText(String.valueOf(TicTacToeGameBoardFieldView.SYMBOLS[1]));
        textViewPlayer1Name.setText(player1.getPlayerName());
    }

    @Override
    public void onClick(View view) {
        TicTacToeGameBoardFieldView ticTacToeGameBoardFieldView = (TicTacToeGameBoardFieldView) view;
        if (ticTacToeGameBoardFieldView.isEmpty()) {
            ((TicTacToeGameFieldInteractionListener) gameBoardInteractionListener).onClick(ticTacToeGameBoardFieldView);
        }
    }

    public List<Line> getLines() {
        return lines;
    }

    public SerializableMatrix<TicTacToeGameStateField> getGameState() {
        SerializableMatrix<TicTacToeGameStateField> gameState = new SerializableMatrix<>(getSizeX(), getSizeY());
        for (TicTacToeGameBoardFieldView view : matrix) {
            gameState.set(view.getCoordinate(), view.getGameStateField());
        }
        return gameState;
    }
}
