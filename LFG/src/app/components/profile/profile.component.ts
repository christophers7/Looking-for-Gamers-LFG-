import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private router: Router, private tokenStorage: TokenStorageService) { }

  currentUser: any;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
  }

  modifyProfile(): void {
    const navigationDetails: string[] = ['/main/profile/modify'];
    this.router.navigate(navigationDetails);
  }

  modifyCredential(): void {
    const navigationDetails: string[] = ['/main/credential/modify'];
    this.router.navigate(navigationDetails);
  }

  goToMain(): void {
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }
}
