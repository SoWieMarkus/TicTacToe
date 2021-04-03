package markus.wieland.tictactoe;

import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.games.ai.AIMove;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.games.game.MultiPlayerGame;
import markus.wieland.tictactoe.ai.TicTacToeAIMove;

public class TicTacToe extends MultiPlayerGame<TicTacToeGameState, TicTacToeGameResult> implements TicTacToeGameBoardFieldInteractionListener {

    private final TicTacToeGameBoard ticTacToeGameBoard;

    public TicTacToe(ConstraintLayout constraintLayout, TicTacToeGameState gameState, GameEventListener<TicTacToeGameResult> gameEventListener) {
        super(gameEventListener);
        ticTacToeGameBoard = new TicTacToeGameBoard(constraintLayout, this);
        playerManager = gameState.getPlayerManager();
        ticTacToeGameBoard.loadGameState(gameState);
    }

    @Override
    protected void performAIMove(AIMove aiMove) {
        if (aiMove == null) return;
        if (!(aiMove instanceof TicTacToeAIMove))
            throw new IllegalArgumentException("Wrong AIMove type.");
        TicTacToeAIMove move = (TicTacToeAIMove) aiMove;
        onClick(move.getX(), move.getY(), ticTacToeGameBoard.getMatrix().get(move.getX(), move.getY()));
    }

    @Override
    public TicTacToeGameState getGameState() {
        return new TicTacToeGameState(playerManager, ticTacToeGameBoard.getMatrix());
    }

    @Override
    public void onClick(int x, int y, TicTacToeGameBoardField field) {
        field.setValue(playerManager.getCurrentPlayer().getValue());
        field.update();
        TicTacToeGameResult result = getResult();
        if (result != null)
            finish(result);
        nextPlayer();
    }

    @Override
    public void finish(TicTacToeGameResult ticTacToeGameResult) {
        super.finish(ticTacToeGameResult);
        enableGameBoard(false);
    }

    @Override
    public TicTacToeGameResult getResult() {
        if (ticTacToeGameBoard.checkForWin(playerManager.getCurrentPlayer().getValue()))
            return new TicTacToeGameResult(playerManager.getCurrentPlayer());
        if (ticTacToeGameBoard.checkForTie()) return new TicTacToeGameResult(null);
        return null;
    }

    public void enableGameBoard(boolean enable) {
        ticTacToeGameBoard.enableGameBoard(enable);
    }

}
