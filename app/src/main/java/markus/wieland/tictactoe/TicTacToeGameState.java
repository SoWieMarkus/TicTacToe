package markus.wieland.tictactoe;

import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.grid.GridGameState;
import markus.wieland.games.player.PlayerManager;

public class TicTacToeGameState extends GridGameState<TicTacToeGameStateField> {

    private final PlayerManager playerManager;

    public TicTacToeGameState(SerializableMatrix<TicTacToeGameStateField> matrix, PlayerManager playerManager) {
        super(matrix);
        this.playerManager = playerManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public int[][] convert(){
        int[][] gameBoard = new int[TicTacToeGameBoardView.SIZE_Y][TicTacToeGameBoardView.SIZE_X];

        for (TicTacToeGameStateField field : matrix) {
            gameBoard[field.getCoordinate().getY()][field.getCoordinate().getX()] = field.getValue();
        }

        return gameBoard;
    }
}
