package com.risyad.backend.service;

import com.risyad.backend.model.Score;
import com.risyad.backend.repository.ScoreRepository;
import com.risyad.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @Transactional
    public Score createScore(Score score) {
        UUID playerId = score.getPlayerId();
        if (playerId == null  !playerRepository.existsById(playerId)) {
            throw new RuntimeException("player_not_found");
        }
        if (score.getCoinsCollected() == null) score.setCoinsCollected(0);
        if (score.getDistanceTravelled() == null) score.setDistanceTravelled(0);
        Score saved = scoreRepository.save(score);
        playerService.updatePlayerStats(saved.getPlayerId(), saved.getValue(), saved.getCoinsCollected(), saved.getDistanceTravelled());
        return saved;
    }

    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
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
        List<Score> list = scoreRepository.findTopScores(limit);
        if (list == null) return Collections.emptyList();
        if (limit <= 0  list.size() <= limit) return list;
        return list.subList(0, limit);
    }
}