import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-lfgmain',
  templateUrl: './lfgmain.component.html',
  styleUrls: ['./lfgmain.component.css']
})
export class LFGMainComponent implements OnInit {


  panelNumber!: number;

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  currentUser: any;



  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();


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


  logOut(): void {
    this.tokenStorage.signOut();
    this.router.navigate([''])
  }
}
