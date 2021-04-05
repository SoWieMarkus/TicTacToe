package markus.wieland.tictactoe.ai;

import java.util.List;

import markus.wieland.games.ai.gridbased.GridGameAIMove;
import markus.wieland.games.elements.Line;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameBoardField;

public class TicTacToeAIMove extends GridGameAIMove {

    public TicTacToeAIMove(int x, int y, int[][] grid, Difficulty difficulty, List<Line> lines, int player) {
        super(difficulty, player, x, y, lines, grid);
    }

    @Override
    public boolean isLegal() {
        return grid[y][x] == GameBoardField.FREE;
    }

    @Override
    protected long easyMove() {
        return random.nextInt(100);
    }

    @Override
    protected long mediumMove() {
        return easyMove();
    }

    @Override
    protected long hardMove() {
        return easyMove();
    }
}
