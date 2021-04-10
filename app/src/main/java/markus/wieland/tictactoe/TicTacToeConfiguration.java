package markus.wieland.tictactoe;

import markus.wieland.games.game.GameConfiguration;

public class TicTacToeConfiguration implements GameConfiguration {

    private final boolean singlePlayer;

    public TicTacToeConfiguration(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }
}
