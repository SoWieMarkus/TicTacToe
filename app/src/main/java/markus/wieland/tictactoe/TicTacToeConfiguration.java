package markus.wieland.tictactoe;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameConfiguration;

public class TicTacToeConfiguration implements GameConfiguration {

    private final Difficulty difficulty;

    public TicTacToeConfiguration(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
