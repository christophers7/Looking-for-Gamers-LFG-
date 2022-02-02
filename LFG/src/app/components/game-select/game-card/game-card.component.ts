import { EventEmitter, Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AvailableGames } from 'src/app/models/available-games.model';
import { Game } from 'src/app/models/game.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { GameDisplayComponent } from '../game-display/game-display.component';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.css']
})
export class GameCardComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
  }

  @Input()
  game!: Game;
  
  @Output() 
  emitter = new EventEmitter<{gId: number, panelNumber: number}>()

  emit(gameId: number) {
    let tempGame: AvailableGames = new AvailableGames(this.game.gameId, this.game.name, this.game.imgLink, this.game.sessions)
    this.tokenStorage.saveGame(tempGame);
    this.emitter.emit({gId: this.game.gameId, panelNumber: 2});
  }

  goToGroups():void{
    let tempGame: AvailableGames = new AvailableGames(this.game.gameId, this.game.name, this.game.imgLink, this.game.sessions)
    this.tokenStorage.saveGame(tempGame);
    this.goToGameGroups();
  }

  goToGameGroups(): void{
    const navigationDetails: string[] = ['game/group'];
    console.log(navigationDetails);
    this.router.navigate(navigationDetails);
  }

}
