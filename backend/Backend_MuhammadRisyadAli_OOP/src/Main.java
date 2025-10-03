package Model;

import Repository.PlayerRepository;
import Repository.ScoreRepository;
import Service.PlayerService;
import Service.ScoreService;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepository = new PlayerRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        PlayerService playerService = new PlayerService(playerRepository);
        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository, playerService);

        System.out.println("=== CS 4 ===\n");

        Player player1 = new Player("NanaBanana");
        Player player2 = new Player("Yingko");
        Player player3 = new Player("LegdontWork");

        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        playerService.createPlayer(player3);

        System.out.println("Players created!\n");
        System.out.println("All Players (Initial Status):");
        playerService.getAllPlayers().forEach(Player::showDetail);

        scoreService.createScore(new Score(player1.getPlayerId(), 1500, 50, 3000));
        scoreService.createScore(new Score(player1.getPlayerId(), 2000, 75, 4500));
        scoreService.createScore(new Score(player2.getPlayerId(), 1800, 60, 3500));
        scoreService.createScore(new Score(player3.getPlayerId(), 1200, 40, 2500));
        scoreService.createScore(new Score(player3.getPlayerId(), 2500, 90, 5000));

        System.out.println("Scores created!\n");

        System.out.println("Player Stats After Scores:");
        playerService.getAllPlayers().forEach(Player::showDetail);

        System.out.println("Top 2 players by high score:");
        playerService.getLeaderboardByHighScore(2).forEach(Player::showDetail);

        System.out.println("All scores for " + player1.getUsername() + ":");
        scoreService.getScoresByPlayerId(player1.getPlayerId()).forEach(Score::showDetail);

        System.out.println("Top 3 scores overall:");
        scoreService.getLeaderboard(3).forEach(Score::showDetail);

        System.out.println("Searching for player 'NanaBanana':");
        try {
            Player found = playerService.getPlayerByUsername("NanaBanana");
            found.showDetail();
        } catch (RuntimeException e) {
            System.out.println("Player not found!");
        }

        System.out.println("Totals for " + player3.getUsername() + ":");
        System.out.println("Total Coins: " + scoreService.getTotalCoinsByPlayerId(player3.getPlayerId()));
        System.out.println("Total Distance: " + scoreService.getTotalDistanceByPlayerId(player3.getPlayerId()));
        System.out.println();

        System.out.println("Recent scores (ordered by creation time):");
        scoreService.getRecentScores().forEach(Score::showDetail);
    }
}