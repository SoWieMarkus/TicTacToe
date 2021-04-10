package markus.wieland.tictactoe;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.tictactoe.R;
import markus.wieland.tictactoe.TicTacToeConfiguration;

public class TicTacToeStartScreen extends StartScreenView {

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

    @Override
    protected TicTacToeConfiguration getConfiguration() {
        return new TicTacToeConfiguration(singlePlayer);
    }

    private void configureSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
        close();
    }

    @Override
    protected void onBuild() {
        singlePlayer = false;
        setBackgroundColor(getContext().getColor(R.color.start));
        findViewById(R.id.activity_tictactoe_start_screen_single_player).setOnClickListener(v -> configureSinglePlayer(true));
        findViewById(R.id.activity_tictactoe_start_screen_multiplayer).setOnClickListener(v -> configureSinglePlayer(false));
    }
}
