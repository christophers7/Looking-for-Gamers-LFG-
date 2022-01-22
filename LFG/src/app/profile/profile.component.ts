import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    
  }

  modifyProfile(): void {
    const navigationDetails: string[] = ['/profile/modify'];
    this.router.navigate(navigationDetails);
  }

  goToMain(): void {
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }
}
