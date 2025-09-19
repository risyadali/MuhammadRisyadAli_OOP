package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerClass implements ShowDetail {
   private UUID playerId;
   private String username;
   private int highScore;
   private int totalCoins;
   private int totalDistance;
   private LocalDateTime createdAt;

   public PlayerClass (String username) {
       this.playerId = UUID.randomUUID();
       this.username = username;
       this.highScore = 0;
       this.totalCoins = 0;
       this.totalDistance = 0;
       this.createdAt = LocalDateTime.now();
   }

    public UUID getPlayerId() {
        return playerId;
    }

    public void updateHighScore(int highScore) {
       if (newScore > this.highScore){
           this.highScore = highScore;
       }
    }

    public void addDistance(int coins) {
       this.totalCoins += coins;
    }

    @Override
    public void showDetail() {
        system.out.println("---Player Details---");
        system.out.println("Player ID:"+ playerId);
        system.out.println("username:"+username);
        system.out.println("High Score:"+highScore);
        system.out.println("Total Coins:"+totalCoins);
        system.out.println("Total Distance Travelled:"+totalDistance);
        system.out.println("Created At:"+createdAt);
        system.out.println();
    }
}
