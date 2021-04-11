package markus.wieland.tictactoe;

import android.os.Bundle;
import android.view.WindowManager;

import markus.wieland.games.GameActivity;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.game.Highscore;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.persistence.GameSaver;
import markus.wieland.games.screen.view.EndScreenView;
import markus.wieland.games.screen.view.StartScreenView;

public class MainActivity extends GameActivity<TicTacToeConfiguration, Highscore, TicTacToeGameState, TicTacToeGameResult, TicTacToe> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected StartScreenView initializeStartScreen() {
        return findViewById(R.id.activity_tictactoe_start_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected EndScreenView initializeEndScreen() {
        return findViewById(R.id.activity_tictactoe_end_screen);
    }

    @Override
    protected GameGenerator<TicTacToeGameState> initializeGenerator(GameConfiguration configuration) {
        TicTacToeConfiguration ticTacToeConfiguration = (TicTacToeConfiguration) configuration;
        return new TicTacToeGenerator(ticTacToeConfiguration, this);
    }

    @Override
    protected GameSaver<TicTacToeGameState, Highscore> initializeGameSaver() {
        return null;
    }

    @Override
    protected void initializeGame(TicTacToeGameState gameState) {
        game = new TicTacToe(this, findViewById(R.id.activity_tictactoe_game_board), gameState);
        game.start();
    }

}