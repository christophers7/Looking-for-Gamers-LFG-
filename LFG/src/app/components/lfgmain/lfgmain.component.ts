import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-lfgmain',
  templateUrl: './lfgmain.component.html',
  styleUrls: ['./lfgmain.component.css']
})
export class LFGMainComponent implements OnInit {


  panelNumber!: number;

  constructor(
    private router: Router, 
    private tokenStorage: TokenStorageService,
    private userService: UserService) { }

  currentUser: any;

  hostGroupPanel:boolean = false;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.viewHostedGroup();
  }

  viewHostedGroup():void{
    if(this.tokenStorage.getCreatedGroup()) this.hostGroupPanel = true;
  }

  gameId: number = 0;
  send(data:any){
    this.panelNumber = data.panelNumber;
    this.gameId = data.gId;
  }

  changePanel(data: any){
    this.panelNumber = data;
    console.log(this.panelNumber);
  }

  goToProfile(): void {
    const navigationDetails: string[] = ['/main/profile'];
    this.router.navigate(navigationDetails);
  }


  goToSession():void{
    const navigationDetails: string[] = ['/game/group/host'];
    this.router.navigate(navigationDetails);
  }

  logOut(): void {
    if(this.tokenStorage.getCreatedGroup()) this.userService.endSession();
    this.tokenStorage.signOut();
    this.router.navigate([''])
  }
}
