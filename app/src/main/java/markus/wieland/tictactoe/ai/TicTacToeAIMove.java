package markus.wieland.tictactoe.ai;

import java.util.List;

import markus.wieland.games.ai.gridbased.GridGameAIMove;
import markus.wieland.games.ai.pattern.PatternMatcher;
import markus.wieland.games.ai.pattern.PatternMatchingLine;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameBoardField;

public class TicTacToeAIMove extends GridGameAIMove {

    private final int opponent;

    public TicTacToeAIMove(int x, int y, int[][] grid, Difficulty difficulty, int player, int opponent) {
        super(difficulty, player, x, y, grid);
        this.opponent = opponent;
    }

    @Override
    public boolean isLegal() {
        return grid[y][x] == GameBoardField.FREE;
    }

    @Override
    public void executeMove() {
        grid[y][x] = player;
    }

    @Override
    protected long easyMove() {
        return random.nextInt(100);
    }

    @Override
    protected long mediumMove() {
        List<PatternMatchingLine> lines = getPatternMatchingLines();
        int win = PatternMatcher.getInstance().getAmountOfMatchingPatterns(3, player, lines);
        int amountTwoInARow = PatternMatcher.getInstance().getAmountOfMatchingPatterns(2, player, lines);
        return win * 100000 + (long) amountTwoInARow * 100;
    }

    @Override
    protected long hardMove() {
        List<PatternMatchingLine> lines = getPatternMatchingLines();
        int win = PatternMatcher.getInstance().getAmountOfMatchingPatterns(3, player, lines);
        int amountTwoInARow = PatternMatcher.getInstance().getAmountOfMatchingPatterns(2, player, lines);

        int amountTwoInARowOfOpponent = PatternMatcher.getInstance().getAmountOfMatchingPatterns(2, opponent, lines);

        return win * 100000 + (long) (amountTwoInARow - (2 * amountTwoInARowOfOpponent)) * 100;
    }

    @Override
    public List<PatternMatchingLine> getPatternMatchingLines() {
        return super.getPatternMatchingLines();
    }
}
