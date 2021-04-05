package markus.wieland.tictactoe;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.screen.view.StartScreenView;

public class TicTacToeStartScreen extends StartScreenView {

    private Difficulty difficulty;
    private boolean singlePlayer;

    public TicTacToeStartScreen(@NonNull Context context) {
        super(context);
    }

    public TicTacToeStartScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TicTacToeStartScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void configureSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        close();
    }

    @Override
    protected TicTacToeConfiguration getConfiguration() {
        return new TicTacToeConfiguration(difficulty, singlePlayer);
    }

    @Override
    protected void onBuild() {
        difficulty = Difficulty.HARD;
        singlePlayer = false;

        findViewById(R.id.activity_tictactoe_start_screen_single_player).setOnClickListener(v -> configureSinglePlayer(true));
        findViewById(R.id.activity_tictactoe_start_screen_multiplayer).setOnClickListener(v -> configureSinglePlayer(false));
    }
}
