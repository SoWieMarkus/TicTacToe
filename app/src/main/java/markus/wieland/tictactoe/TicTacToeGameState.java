package markus.wieland.tictactoe;

import markus.wieland.games.elements.Matrix;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.persistence.GameState;
import markus.wieland.games.player.Player;
import markus.wieland.games.player.PlayerManager;
import markus.wieland.tictactoe.ai.TicTacToeAI;

public class TicTacToeGameState implements GameState {

    private final PlayerManager playerManager;
    private final int[][] field;

    public TicTacToeGameState(PlayerManager playerManager, Matrix<TicTacToeGameBoardField> matrix) {
        this.playerManager = playerManager;
        this.field = new int[3][3];
        for (TicTacToeGameBoardField ticTacToeGameBoardField : matrix) {
            field[ticTacToeGameBoardField.getY()][ticTacToeGameBoardField.getX()] = ticTacToeGameBoardField.getValue();
        }
    }

    public TicTacToeGameState(boolean singlePlayer) {
        this.playerManager = new PlayerManager();
        this.field = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = GameBoardField.FREE;
            }
        }

        playerManager.register(new Player(null, 0, "Player 1"));
        playerManager.register(new Player(singlePlayer ? new TicTacToeAI(1, Difficulty.HARD) : null,
                1
                , singlePlayer ? "Computer" : "Player 2"));
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public int[][] convert() {
        return field;
    }

}
