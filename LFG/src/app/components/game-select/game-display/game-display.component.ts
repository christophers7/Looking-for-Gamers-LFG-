import { EventEmitter, Component, OnInit, Output } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameService } from 'src/app/_services/game.service';
import { Location } from '@angular/common';
import { UserService } from 'src/app/_services/user.service';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit {
  
  games: AvailableGames[] = [];

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
        this.games = data;
      },
      (error) => {
        console.log(error);
      }
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
