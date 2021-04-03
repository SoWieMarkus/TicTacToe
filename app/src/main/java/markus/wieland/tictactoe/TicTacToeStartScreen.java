package markus.wieland.tictactoe;

import android.app.Activity;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.screen.StartScreen;

public class TicTacToeStartScreen extends StartScreen {

    private Difficulty difficulty;

    public TicTacToeStartScreen(Activity activity) {
        super(activity.findViewById(R.id.startMenu));
    }

    @Override
    protected TicTacToeConfiguration getConfiguration() {
        return new TicTacToeConfiguration(difficulty);
    }

    private void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        close();
    }
}
