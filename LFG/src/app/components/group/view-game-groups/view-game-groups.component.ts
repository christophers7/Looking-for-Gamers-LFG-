import { Component, Input, OnInit } from '@angular/core';
import { GroupDetails } from 'src/app/models/group-details.model';
import { Group } from 'src/app/models/group.model';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';


@Component({
  selector: 'app-view-game-groups',
  templateUrl: './view-game-groups.component.html',
  styleUrls: ['./view-game-groups.component.css']
})
export class ViewGameGroupsComponent implements OnInit {

  groupSessions!: GroupDetails[];
  
  currentUser: any;
  game: any;

  constructor(
    private sessionStorage: SessionStorageService,
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private routingAllocation: RoutingAllocatorService
    ) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.game = this.sessionStorage.getGame();
    this.getGroupSessions();
  }

  hostingGroup: boolean = false;

  @Input()
  group!:Group;

  @Input()
  gameId!:number;

  getGroupSessions(){
    this.userService.getSelectedGame(this.game.gameId)
      .subscribe(
        (data) => {
          console.log(data)
          this.groupSessions = data.selectedGameAvailableGroups;
        },
        (error) => {
          console.log(error);
        }
      )
  }

  newGroupCreated(group:any){
    this.group = group;
  }

  goToCreateGroup(): void {
    this.routingAllocation.createGroup();
  }

  goToMain():void{
    this.routingAllocation.main();
  }


  goToSession():void{
    this.routingAllocation.hostSession();
  }

}
