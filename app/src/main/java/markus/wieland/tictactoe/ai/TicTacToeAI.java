package markus.wieland.tictactoe.ai;

import markus.wieland.games.ai.gridbased.GridGameAI;
import markus.wieland.games.ai.moverater.HighValueMoveRater;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.persistence.GameState;
import markus.wieland.tictactoe.TicTacToeGameState;


public class TicTacToeAI extends GridGameAI {

    private final int opponent;

    public TicTacToeAI(int player, int opponent, Difficulty difficulty) {
        super(new HighValueMoveRater(), player, difficulty);
        this.opponent = opponent;
    }

    @Override
    protected int[][] getCurrentGameState(GameState gameState) {
        return ((TicTacToeGameState) gameState).convert();
    }

    @Override
    protected TicTacToeAIMove buildMove(int x, int y, int[][] grid) {
        return new TicTacToeAIMove(x, y, grid, difficulty, player, opponent);
    }
}
