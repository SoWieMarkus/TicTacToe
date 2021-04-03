package markus.wieland.tictactoe;

import markus.wieland.games.game.GameBoardInteractionListener;

public interface TicTacToeGameBoardFieldInteractionListener extends GameBoardInteractionListener<TicTacToeGameBoardField> {

    void onClick(int x, int y, TicTacToeGameBoardField field);

}
