import { EventEmitter, Component, OnInit, Output } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameService } from 'src/app/_services/game.service';
import { Location } from '@angular/common';
import { UserService } from 'src/app/_services/user.service';
import { Game } from 'src/app/models/game.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit {
  
  games!: Game[];

  gId!: number;

  constructor(
    private gameService:GameService,
    private userService: UserService,
    private tokenStorage: TokenStorageService
    ) { }

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames(){
    this.userService.generateGames().subscribe(
      (data) => {
        console.log(data)
        this.games = data.gameSessionList;
      },
    )
  }

  @Output()
  emitter = new EventEmitter<{gId: number, panelNumber: number}>()

  send(data:any) {
    this.emitter.emit(data);
    console.log(data);
  }
}
