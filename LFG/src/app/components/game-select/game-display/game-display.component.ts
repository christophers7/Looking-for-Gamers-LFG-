import { Component, OnInit } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameService } from 'src/app/_services/game.service';

@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit {

  constructor(private gameService:GameService) { }

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames(){
    this.gameService.findAllGames().subscribe(
      (data) => {
        this.games = data;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  games:AvailableGames[] = [];

}
