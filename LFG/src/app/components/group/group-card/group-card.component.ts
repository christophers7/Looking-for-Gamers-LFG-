import { Component, Input, OnInit } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { GameGroupHolders } from 'src/app/models/game-group-holders.model';
import { Game } from 'src/app/models/game.model';
import { GroupDetails } from 'src/app/models/group-details.model';
import { Group } from 'src/app/models/group.model';
import { UserViewGroup } from 'src/app/models/user-view-group.model';
import { GameService } from 'src/app/_services/game.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.css']
})
export class GroupCardComponent implements OnInit {

  @Input()
  groupSessions!: GroupDetails[];

  @Input()
  gameId!:number;

  game: any;

  constructor(private tokenStorage: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.getGameInfo();
    this.game = this.tokenStorage.getGame();
  }

  getGameInfo(){
  }

  groupRequest(group: GroupDetails) {
    this.userService.requestToJoinGroup(group).subscribe(
      (data) => {
        // increase app counter
      }
    )
  }
}
