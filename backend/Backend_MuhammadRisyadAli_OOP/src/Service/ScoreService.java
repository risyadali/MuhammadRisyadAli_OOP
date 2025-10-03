package Service;

import Model.Score;
import Model.Player;
import Repository.ScoreRepository;
import Repository.PlayerRepository;
import java.util.*;

public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public ScoreService(ScoreRepository scoreRepository,
                        PlayerRepository playerRepository,
                        PlayerService playerService) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    public Score createScore(Score score) {
        UUID playerId = score.getPlayerId();
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found!"));
        scoreRepository.save(score);
        playerService.updatePlayerStats(playerId, score.getValue(),
                score.getCoinsCollected(), score.getDistance());
        return score;
    }

    public Score getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId)
                .orElseThrow(() -> new RuntimeException("Score not found!"));
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
    }

    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }

    public Optional<Score> getHighestScoreByPlayerId(UUID playerId) {
        return scoreRepository.findHighestScoreByPlayerId(playerId);
    }

    public List<Score> getScoresAboveValue(int minValue) {
        return scoreRepository.findByValueGreaterThan(minValue);
    }

    public List<Score> getRecentScores() {
        return scoreRepository.findAllByOrderByCreatedAtDesc();
    }

    public int getTotalCoinsByPlayerId(UUID playerId) {
        return scoreRepository.getTotalCoinsByPlayerId(playerId);
    }

    public int getTotalDistanceByPlayerId(UUID playerId) {
        return scoreRepository.getTotalDistanceByPlayerId(playerId);
    }

    public Score updateScore(UUID scoreId, Score updatedScore) {
        // Note: This is a simplistic update. In a real implementation, you would copy fields
        // from updatedScore to the existing entity and save, or use setters. For now, we delete
        // the old and save the new (assuming updatedScore has the correct playerId but new scoreId).
        // To properly overwrite, add setters to Score class if needed.
        scoreRepository.deleteById(scoreId);
        scoreRepository.save(updatedScore);
        return updatedScore;
    }

    public void deleteScore(UUID scoreId) {
        scoreRepository.findById(scoreId)
                .orElseThrow(() -> new RuntimeException("Score not found!"));
        scoreRepository.deleteById(scoreId);
    }

    public void deleteScoresByPlayerId(UUID playerId) {
        scoreRepository.findByPlayerId(playerId)
                .forEach(s -> scoreRepository.deleteById(s.getScoreId()));
    }
}