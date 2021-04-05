package markus.wieland.tictactoe;

import markus.wieland.games.persistence.GameGenerator;

public class TicTacToeGenerator implements GameGenerator<TicTacToeGameState> {

    protected final TicTacToeConfiguration configuration;

    public TicTacToeGenerator(TicTacToeConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TicTacToeGameState generate() {
        return new TicTacToeGameState(configuration.isSinglePlayer());
    }
}
