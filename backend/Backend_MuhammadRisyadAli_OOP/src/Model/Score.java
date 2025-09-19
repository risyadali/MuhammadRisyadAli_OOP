package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerClass implements ShowDetail {
    private UUID playerId;
    private UUID ScoreId;
    private int highScore;
    private int coinsCollected;
    private int totalDistance;
    private LocalDateTime AchievedAt;

    public Score (UUID PlayerID, int highScore, int coinsCollected, int totalDistance {
        this.ScoreId = UUID.randomUUID();
        this.playerId = playerId;
        this.highScore = highScore;
        this.coinsCollected = coinsCollected;
        this.totalDistance = totalDistance;
        this.AchievedAt = LocalDateTime.now();
    }

   public int getValue(){
        return highScore;
   }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    @Override
    public void showDetail() {
        system.out.println("---Player Details---");
        system.out.println("Score ID:"+ ScoreId);
        system.out.println(playerId:"+ playerId);
        system.out.println("High Score:"+highScore);
        system.out.println("Total Coins:"+coinsCollected);
        system.out.println("Distance:"+totalDistance);
        system.out.println("Achieved At:"+ AchievedAt);
        system.out.println();
    }
}
