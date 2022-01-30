import { EventEmitter, Component, Input, OnInit, Output } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameDisplayComponent } from '../game-display/game-display.component';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.css']
})
export class GameCardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input()
  game!: AvailableGames;
  
  @Output() 
  emitter = new EventEmitter<{gId: number, panelNumber: number}>()

  emit(gameId: number) {
    this.emitter.emit({gId: this.game.gameId, panelNumber: 2})
  }

}
