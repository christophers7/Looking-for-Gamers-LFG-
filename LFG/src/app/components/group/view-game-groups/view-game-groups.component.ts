import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameGroupHolders } from 'src/app/models/game-group-holders.model';
import { GroupDetails } from 'src/app/models/group-details.model';
import { Group } from 'src/app/models/group.model';
import { UserViewGroup } from 'src/app/models/user-view-group.model';
import { GameGroupService } from 'src/app/_services/game-group.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';


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
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.game = this.tokenStorage.getGame();
    // this.getGameGroups();
    this.getGroupSessions();
  }


  // getGameGroups():void{
  //   console.log(this.game.gameId);
  //   this.userService.generateGroupsForGame(this.game.gameId).subscribe(
  //     (data) => {
  //       this.groupSessions = data.selectedGameAvailableGroups
  //     }
  //       );
  // }


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
  

  hostViewOpen(check:boolean){
    this.hostingGroup = check;
    console.log(check);
  }

  newGroupCreated(group:any){
    console.log(group);
    this.group = group;
    console.log(group);
    this.hostViewOpen(true);
  }


  panelNumber!: number;

  @Output()
  panelNumberChange = new EventEmitter<number>();

  goBackToGameSelect(): void{
    this.panelNumber = 1;
    this.changePanel();
  }

  goToCreateGroup(): void {
    // this.panelNumber = 3;
    // this.changePanel();
    const navigationDetails:string[] = ['/game/group/create'];
    this.router.navigate(navigationDetails);
  }

  changePanel() {
    this.panelNumberChange.emit(this.panelNumber);
  }

  goToMain():void{
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }


  goToSession():void{
    const navigationDetails: string[] = ['/game/group/host'];
    this.router.navigate(navigationDetails);
  }

}
