import Model.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("GamerPro123");
        Player player2 = new Player("SpeedRunner99");

        UUID idPlayer1 = player1.getPlayerId();
        UUID idPlayer2 = player2.getPlayerId();

        Score score1 = new Score(idPlayer1, 1500, 250, 5000);
        Score score2 = new Score(idPlayer2, 3200, 750, 12000);
        Score score3 = new Score(idPlayer1, 1800, 300, 6000);

        System.out.println("=== INITIAL PLAYER DATA ===");
        player1.showDetail();
        player2.showDetail();

        System.out.println("=== SCORE DATA ===");
        score1.showDetail();
        score2.showDetail();
        score3.showDetail();

        System.out.println("=== UPDATING PLAYER 1 ===");
        System.out.println("Updating player1 with score1 data...");
        player1.updateHighScore(score1.getValue());
        player1.addCoins(score1.getCoinsCollected());
        player1.addDistance(score1.getDistance());

        System.out.println("Updating player1 with score3 data...");
        player1.updateHighScore(score3.getValue());
        player1.addCoins(score3.getCoinsCollected());
        player1.addDistance(score3.getDistance());

        System.out.println("=== UPDATING PLAYER 2 ===");
        System.out.println("Updating player2 with score2 data...");
        player2.updateHighScore(score2.getValue());
        player2.addCoins(score2.getCoinsCollected());
        player2.addDistance(score2.getDistance());

        System.out.println("\n=== FINAL PLAYER DATA AFTER UPDATES ===");
        player1.showDetail();
        player2.showDetail();

        System.out.println("Program completed successfully!");
    }
}
