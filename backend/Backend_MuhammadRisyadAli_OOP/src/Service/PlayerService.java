package Service;

import Model.Player;
import Repository.PlayerRepository;

import java.util.*;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public boolean existsByUsername(String username) {
        return playerRepository.existByUsername(username); // Use the repository method for consistency
    }

    public Player createPlayer(Player player) {
        if (existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already registered!");
        }
        playerRepository.save(player);
        return player;
    }

    public Player getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found!"));
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Player not found!"));
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existing = getPlayerById(playerId);
        if (!updatedPlayer.getUsername().equals(existing.getUsername())
                && existsByUsername(updatedPlayer.getUsername())) {
            throw new RuntimeException("Username already taken!");
        }
        if (!updatedPlayer.getUsername().equals(existing.getUsername())) {
            existing.setUsername(updatedPlayer.getUsername());
        }

        existing.updateHighScore(updatedPlayer.getHighScore());
        existing.setTotalCoins(updatedPlayer.getTotalCoins());
        existing.setTotalDistanceTravelled(updatedPlayer.getTotalDistanceTravelled());

        playerRepository.save(existing);
        return existing;
    }

    public void deletePlayer(UUID playerId) {
        playerRepository.deleteById(playerId);
    }

    public void deletePlayerByUsername(String username) {
        playerRepository.findByUsername(username).ifPresent(
                p -> playerRepository.deleteById(p.getPlayerId())
        );
    }

    public Player updatePlayerStats(UUID playerId, int scoreValue, int coinsCollected, int distanceTravelled) {
        Player player = getPlayerById(playerId);
        player.updateHighScore(scoreValue);
        player.addCoins(coinsCollected);
        player.addDistance(distanceTravelled);
        // Save the updated player
        playerRepository.save(player);
        return player;
    }

    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findAllByOrderByTotalDistanceTravelledDesc();
    }
}