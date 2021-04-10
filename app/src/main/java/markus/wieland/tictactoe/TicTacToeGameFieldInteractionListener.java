package markus.wieland.tictactoe;

import markus.wieland.games.game.view.GameBoardInteractionListener;

public interface TicTacToeGameFieldInteractionListener extends GameBoardInteractionListener {

    void onClick(TicTacToeGameBoardFieldView ticTacToeGameBoardFieldView);

}
