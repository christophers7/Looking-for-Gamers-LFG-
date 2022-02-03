import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  joinedSession!: any;

  insideSession: boolean = false

  insideGroup!: any;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.insideSession = false;
    this.isInsideSession();
  }

  isInsideSession():void{
    this.joinedSession = JSON.parse(this.tokenStorage.getJoinedGroups());
    for(let i:number = 0; i < this.joinedSession.length; i++){
      this.userService.refreshWaitingList(this.joinedSession[i].groupId).subscribe(
        (data) => {
          if(data.success){
          this.insideSession = true;
          this.insideGroup = data;
          }
          }
        );
    }
  }

  goMainPage():void{
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }

  leaveSession():void{
    console.log(this.insideGroup);
    this.userService.leaveAllWaitingList().subscribe(
      (data) => {
        console.log(data)
        this.tokenStorage.leaveAllGroups();
      }
    );
        
    this.goMainPage();
  }


}
