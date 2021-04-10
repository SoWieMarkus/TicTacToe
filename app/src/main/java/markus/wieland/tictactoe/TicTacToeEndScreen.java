package markus.wieland.tictactoe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.player.Player;
import markus.wieland.games.screen.interact_listener.EndScreenInteractListener;
import markus.wieland.games.screen.view.EndScreenView;

public class TicTacToeEndScreen extends EndScreenView {

    private TextView tvGameResultMessage;

    public TicTacToeEndScreen(@NonNull Context context) {
        super(context);
    }

    public TicTacToeEndScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TicTacToeEndScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onNewGameResult() {
        if (gameResult == null) return;
        if (!(gameResult instanceof TicTacToeGameResult)) throw new IllegalArgumentException();
        TicTacToeGameResult ticTacToeGameResult = (TicTacToeGameResult) gameResult;

        Player winner = ticTacToeGameResult.getWinner();
        if (winner == null) {
            tvGameResultMessage.setText(R.string.tictactoe_draw);
            setBackgroundColor(getContext().getColor(R.color.start));
            return;
        }
        String winnerMessage = winner.getPlayerName() + " " + getContext().getString(R.string.tictactoe_wins);
        tvGameResultMessage.setText(winnerMessage);

        if (winner.hasAI()) {
            setBackgroundColor(getContext().getColor(R.color.lose));
            return;
        }

        setBackgroundColor(getContext().getColor(R.color.win));
    }

    @Override
    protected void onBuild() {
        findViewById(R.id.activity_tictactoe_end_screen_again).setOnClickListener(v -> ((EndScreenInteractListener) screenInteractListener).onClose(true));
        findViewById(R.id.activity_tictactoe_end_screen_back).setOnClickListener(v -> ((EndScreenInteractListener) screenInteractListener).onClose(false));
        tvGameResultMessage = findViewById(R.id.activity_tictactoe_end_screen_text);
    }

}
