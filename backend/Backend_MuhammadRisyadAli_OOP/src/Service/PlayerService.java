package Service;
import Repository.PlayerRepository;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerService {
    private PlayerRepository playerRepository;
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository
    }

    public boolean ExistByUsername(String username) {
        return PlayerRepository.findByUsername()
    }
}
