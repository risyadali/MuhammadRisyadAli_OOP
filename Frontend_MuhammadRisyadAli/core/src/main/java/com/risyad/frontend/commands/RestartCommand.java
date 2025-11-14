package com.risyad.frontend.commands;

import com.risyad.frontend.Command;
import com.risyad.frontend.Player;

public class RestartCommand implements Command {
    private Player player;
    private GameManager gameManager;
    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }
    @Override
    public void execute() {
        player.reset();
        player.setScore(0);
        }

}
