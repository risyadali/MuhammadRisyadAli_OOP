package Repository;

import Model.Player;
import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepository extends BaseRepository<Player, UUID> {

    public Optional<Player> findByUsername(String username) {
        return data.values().stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst();
    }

    public List<Player> findTopPlayersByHighScore(int limit) {
        return data.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getHighScore(), p1.getHighScore()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Player> findByHighscoreGreaterThan(int minScore) {
        return data.values().stream()
                .filter(player -> player.getHighScore() > minScore)
                .collect(Collectors.toList());
    }

    public List<Player> findAllByOrderByTotalCoinsDesc() {
        return data.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalCoins(), p1.getTotalCoins()))
                .collect(Collectors.toList());
    }

    public List<Player> findAllByOrderByTotalDistanceTravelledDesc() {
        return data.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalDistanceTravelled(), p1.getTotalDistanceTravelled()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Player player) {
        UUID id = player.getId();
        data.put(id, player);
        if (!list.contains(player)) {
            list.add(player);
        }
    }

    @Override
    public UUID getId(Player entity) {
        return entity.getId();
    }
}