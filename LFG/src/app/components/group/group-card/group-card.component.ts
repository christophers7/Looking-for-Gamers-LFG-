import { Component, Input, OnInit } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { Group } from 'src/app/models/group.model';
import { UserViewGroup } from 'src/app/models/user-view-group.model';
import { GameService } from 'src/app/_services/game.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.css']
})
export class GroupCardComponent implements OnInit {

  @Input()
  groupSessions!: Group[];

  @Input()
  gameId!:number;

  game!:AvailableGames;

  constructor(private gameService:GameService, private userService: UserService) { }

  ngOnInit(): void {
    this.getGameInfo();
  }

  getGameInfo(){
    this.gameService.findGame(this.gameId).subscribe(
      (data) => {
        this.game = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  groupRequest(group: Group) {
    this.userService.requestToJoinGroup(group).subscribe(
      (data) => {
        // increase app counter
      }
    )
  }
}
