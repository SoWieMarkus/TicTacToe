package markus.wieland.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.interact_listener.EndScreenInteractListener;
import markus.wieland.games.screen.view.EndScreenView;

public class TicTacToeEndScreen extends EndScreenView implements View.OnClickListener {

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
    public void onClick(View v) {
        ((EndScreenInteractListener) screenInteractListener).onClose(false);
    }

    @Override
    protected void onNewGameResult() {
        if (gameResult == null) return;
        if (!(gameResult instanceof TicTacToeGameResult)) throw new IllegalArgumentException();
        TicTacToeGameResult ticTacToeGameResult = (TicTacToeGameResult) gameResult;
        setBackgroundColor(Color.parseColor("#FF0000"));
        if (!ticTacToeGameResult.hasWinner()) {
            tvGameResultMessage.setText("Unentschieden!");
            return;
        }
        tvGameResultMessage.setText(ticTacToeGameResult.getWinner().getPlayerName());
    }

    @Override
    protected void onBuild() {
        findViewById(R.id.activity_tictactoe_end_screen_again).setOnClickListener(this);
        tvGameResultMessage = findViewById(R.id.activity_tictactoe_end_screen_text);
    }

}
