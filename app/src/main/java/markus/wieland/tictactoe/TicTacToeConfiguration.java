package markus.wieland.tictactoe;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameConfiguration;

public class TicTacToeConfiguration implements GameConfiguration {

    private final Difficulty difficulty;
    private final boolean singlePlayer;

    public TicTacToeConfiguration(Difficulty difficulty, boolean singlePlayer) {
        this.difficulty = difficulty;
        this.singlePlayer = singlePlayer;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }
}
