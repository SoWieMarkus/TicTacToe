package markus.wieland.tictactoe;

import markus.wieland.games.GameActivity;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.games.game.Highscore;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.persistence.GameSaver;
import markus.wieland.games.player.Player;
import markus.wieland.games.screen.EndScreen;
import markus.wieland.games.screen.StartScreen;
import markus.wieland.tictactoe.ai.TicTacToeAI;

public class MainActivity extends GameActivity<Highscore, TicTacToeGameState, TicTacToeGameResult, TicTacToe> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected StartScreen initializeStartScreen() {
        return null;
    }

    @Override
    protected EndScreen initializeEndScreen() {
        return null;
    }

    @Override
    protected GameGenerator<TicTacToeGameState> initializeGenerator(GameConfiguration configuration) {
        return new TicTacToeGenerator();
    }

    @Override
    protected GameSaver<TicTacToeGameState, Highscore> initializeGameSaver() {
        return null;
    }

    @Override
    protected void initializeGame(TicTacToeGameState gameState) {
        game = new TicTacToe(findViewById(R.id.background), gameState, this);
        game.registerPlayer(new Player(null, 0, "Player 1"));
        game.registerPlayer(new Player(new TicTacToeAI(1, Difficulty.EASY), 1, "Bot"));
    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGamePause() {

    }
}