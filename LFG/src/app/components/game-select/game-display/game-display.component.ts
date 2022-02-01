import { EventEmitter, Component, OnInit, Output } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameService } from 'src/app/_services/game.service';
import { Location } from '@angular/common';
import { UserService } from 'src/app/_services/user.service';
import { Game } from 'src/app/models/game.model';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit {
  
  games!: Game[];

  constructor(
    private gameService:GameService,
    private userService: UserService
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
     // (error) => {
      //  console.log(error);
     // }
    )
  }

  

  @Output()
  emitter = new EventEmitter<{gId: number, panelNumber: number}>()

  send(data:any) {
    this.emitter.emit(data)
  }
//  goBack(): void{
//    this.location.back();
//  }

}
