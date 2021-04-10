package markus.wieland.tictactoe;

import android.content.Context;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.player.Player;
import markus.wieland.games.player.PlayerManager;
import markus.wieland.tictactoe.ai.TicTacToeAI;


public class TicTacToeGenerator extends GameGenerator<TicTacToeGameState> {

    private final Context context;

    public TicTacToeGenerator(GameConfiguration configuration, Context context) {
        super(configuration);
        this.context = context;
    }

    @Override
    public TicTacToeConfiguration getConfiguration() {
        return (TicTacToeConfiguration) configuration;
    }

    @Override
    public TicTacToeGameState generate() {
        PlayerManager playerManager = new PlayerManager();
        SerializableMatrix<TicTacToeGameStateField> matrix = new SerializableMatrix<>(3, 3);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                matrix.set(coordinate, new TicTacToeGameStateField(coordinate));
            }
        }

        playerManager.register(new Player(null, TicTacToeGameBoardFieldView.PLAYER_1, context.getString(R.string.tictactoe_player1)));
        playerManager.register(new Player(getConfiguration().isSinglePlayer()
                ? new TicTacToeAI(TicTacToeGameBoardFieldView.PLAYER_2, TicTacToeGameBoardFieldView.PLAYER_1, Difficulty.HARD)
                : null, TicTacToeGameBoardFieldView.PLAYER_2, getConfiguration().isSinglePlayer() ? context.getString(R.string.tictactoe_bot) : context.getString(R.string.tictactoe_player2)));

        return new TicTacToeGameState(matrix, playerManager);
    }
}
