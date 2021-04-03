package markus.wieland.tictactoe.ai;

import markus.wieland.games.ai.gridbased.GridGameAI;
import markus.wieland.games.ai.moverater.HighValueMoveRater;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.persistence.GameState;
import markus.wieland.tictactoe.TicTacToeGameState;

public class TicTacToeAI extends GridGameAI {

    public TicTacToeAI(int player, Difficulty difficulty) {
        super(new HighValueMoveRater(), player, difficulty);
    }

    @Override
    protected int[][] getCurrentGameState(GameState ticTacToeGameState) {
        return ((TicTacToeGameState) ticTacToeGameState).convert();
    }

    @Override
    protected TicTacToeAIMove buildMove(int x, int y, int[][] grid) {
        return new TicTacToeAIMove(x, y, grid, difficulty, lines, player);
    }
}
