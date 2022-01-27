import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-lfgmain',
  templateUrl: './lfgmain.component.html',
  styleUrls: ['./lfgmain.component.css']
})
export class LFGMainComponent implements OnInit {

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  currentUser: any;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
  }

  goToProfile(): void {
    const navigationDetails: string[] = ['/main/profile'];
    this.router.navigate(navigationDetails);
  }

  viewGameList(): void{
    this.router.navigate(['/main/games'])
  }
}
