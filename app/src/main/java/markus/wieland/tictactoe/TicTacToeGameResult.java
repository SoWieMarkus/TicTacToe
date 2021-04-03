package markus.wieland.tictactoe;

import markus.wieland.games.game.GameResult;
import markus.wieland.games.player.Player;

public class TicTacToeGameResult implements GameResult {

    private final Player winner;

    public TicTacToeGameResult(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean hasWinner(){
        return winner != null;
    }
}
