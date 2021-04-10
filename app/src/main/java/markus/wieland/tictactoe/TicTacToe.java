package markus.wieland.tictactoe;

import java.util.List;

import markus.wieland.games.ai.AIMove;
import markus.wieland.games.ai.pattern.PatternMatcher;
import markus.wieland.games.ai.pattern.PatternMatchingLine;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.games.game.MultiPlayerGame;
import markus.wieland.games.player.Player;
import markus.wieland.tictactoe.ai.TicTacToeAIMove;

public class TicTacToe extends MultiPlayerGame<TicTacToeGameState, TicTacToeGameResult> implements TicTacToeGameFieldInteractionListener {

    private final TicTacToeGameBoardView ticTacToeGameBoardView;

    public TicTacToe(GameEventListener<TicTacToeGameResult> gameEventListener, TicTacToeGameBoardView ticTacToeGameBoardView, TicTacToeGameState ticTacToeGameState) {
        super(gameEventListener);
        this.ticTacToeGameBoardView = ticTacToeGameBoardView;
        this.ticTacToeGameBoardView.setGameBoardInteractionListener(this);
        this.ticTacToeGameBoardView.loadGameState(ticTacToeGameState);
        PatternMatcher.getInstance().initialize(3, ticTacToeGameBoardView.getLines(), GameBoardField.FREE);
        this.playerManager = ticTacToeGameState.getPlayerManager();
        this.playerManager.shuffleOrder();
        this.ticTacToeGameBoardView.updatePlayers(playerManager.getPlayer(TicTacToeGameBoardFieldView.PLAYER_1), playerManager.getPlayer(TicTacToeGameBoardFieldView.PLAYER_2));
        this.nextPlayer();
    }

    @Override
    protected void performAIMove(AIMove aiMove) {
        TicTacToeAIMove ticTacToeAIMove = (TicTacToeAIMove) aiMove;
        ticTacToeGameBoardView.getGameBoard().get(ticTacToeAIMove.getX(), ticTacToeAIMove.getY()).callOnClick();
        ticTacToeGameBoardView.setEnabled(true);
    }

    @Override
    public TicTacToeGameState getGameState() {
        return new TicTacToeGameState(ticTacToeGameBoardView.getGameState(), playerManager);
    }

    @Override
    public TicTacToeGameResult getResult() {
        TicTacToeGameState gameState = getGameState();
        TicTacToeAIMove ticTacToeAIMove = new TicTacToeAIMove(0, 0,
                gameState.convert(),
                Difficulty.HARD, TicTacToeGameBoardFieldView.PLAYER_1, TicTacToeGameBoardFieldView.PLAYER_2);

        List<PatternMatchingLine> patternMatchingLines = ticTacToeAIMove.getPatternMatchingLines();

        if (checkWin(TicTacToeGameBoardFieldView.PLAYER_1, patternMatchingLines))
            return new TicTacToeGameResult(playerManager.getPlayer(TicTacToeGameBoardFieldView.PLAYER_1));
        if (checkWin(TicTacToeGameBoardFieldView.PLAYER_2, patternMatchingLines))
            return new TicTacToeGameResult(playerManager.getPlayer(TicTacToeGameBoardFieldView.PLAYER_2));
        if (ticTacToeGameBoardView.isCompleted()) return new TicTacToeGameResult(null);
        return null;
    }

    private boolean checkWin(int playerValue, List<PatternMatchingLine> patternMatchingLines) {
        return PatternMatcher.getInstance().getAmountOfMatchingPatterns(3, playerValue, patternMatchingLines) == 1;
    }

    @Override
    public void onClick(TicTacToeGameBoardFieldView ticTacToeGameBoardFieldView) {
        ticTacToeGameBoardFieldView.setValue(playerManager.getCurrentPlayer().getValue());
        ticTacToeGameBoardFieldView.update();
        TicTacToeGameResult gameResult = getResult();
        if (gameResult != null) {
            finish(gameResult);
            return;
        }
        nextPlayer();
    }

    @Override
    public void nextPlayer() {
        Player player = playerManager.next();
        if (player.hasAI()) {
            ticTacToeGameBoardView.setEnabled(false);
            performAIMove(player.move(getGameState()));
        }
    }


}
