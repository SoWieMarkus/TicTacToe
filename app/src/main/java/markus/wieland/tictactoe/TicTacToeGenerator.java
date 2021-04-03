package markus.wieland.tictactoe;

import markus.wieland.games.persistence.GameGenerator;

public class TicTacToeGenerator implements GameGenerator<TicTacToeGameState> {
    @Override
    public TicTacToeGameState generate() {
        return new TicTacToeGameState();
    }
}
