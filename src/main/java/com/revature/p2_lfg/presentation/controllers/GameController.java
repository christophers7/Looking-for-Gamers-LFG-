package com.revature.p2_lfg.presentation.controllers;


import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.service.game.classes.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController("gameController")
@RequestMapping("/game")
public class GameController {
    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private GameService gameService;

    @GetMapping("/available")
    public GameSessionInfoResponse getAvailableGames(){
        dLog.debug("Getting game sessions select list");
        return gameService.getCurrentGameSessionList();
    }

    @GetMapping("/select")
    public SelectedGameAvailableGroupsResponse getSelectedGameGroups(@RequestParam("gameId") int id){
        dLog.debug("Getting game group sessions by gameName: " + id);
        return gameService.getSelectedGameGroups(id);
    }

}
