import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user_data/user.service';
import { Game } from 'src/app/models/game.model';
import { interval, startWith, Subscription, switchMap } from 'rxjs';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit , OnDestroy{
  games!: Game[];

  gId!: number;

  timeInterval!: Subscription;

  constructor(
    private userService: UserService  ) { }

  @HostListener('unloaded')
  ngOnDestroy(): void {
    this.timeInterval.unsubscribe();
  }

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames() {
    this.userService.generateGames().subscribe(
      (data) => {
        this.games = data.gameSessionList;
        this.getPollingGames();
      }
    )
  }


  getPollingGames(){
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.generateGames())
      ).subscribe(
        res => {
          if(this.checkUpdatedGames(res)) this.games = res.gameSessionList;
        },
        err => console.log("lol"))
  }

  checkUpdatedGames(data:any): boolean {
    let updatedGame: boolean = false;
    for(let i = 0; i < data.gameSessionList.length; i++){
      if(data.gameSessionList[i].sessions == this.games[i].sessions) updatedGame = true;
    }
    return updatedGame;
  }


}
