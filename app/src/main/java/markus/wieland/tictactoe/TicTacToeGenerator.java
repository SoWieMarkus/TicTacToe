package markus.wieland.tictactoe;

import markus.wieland.games.persistence.GameGenerator;

public class TicTacToeGenerator extends GameGenerator<TicTacToeGameState> {

    public TicTacToeGenerator(TicTacToeConfiguration configuration) {
        super(configuration);
    }

    @Override
    public TicTacToeGameState generate() {
        return new TicTacToeGameState(((TicTacToeConfiguration)configuration).isSinglePlayer());
    }
}
