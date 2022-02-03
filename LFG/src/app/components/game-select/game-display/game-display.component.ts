import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user_data/user.service';
import { Game } from 'src/app/models/game.model';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit {

  games!: Game[];

  gId!: number;

  constructor(
    private userService: UserService  ) { }

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames() {
    this.userService.generateGames().subscribe(
      (data) => {
        this.games = data.gameSessionList;
      },
    )
  }
}
