import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modify-profile',
  templateUrl: './modify-profile.component.html',
  styleUrls: ['./modify-profile.component.css']
})
export class ModifyProfileComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToProfile(): void {
    const navigationDetails: string[] = ['/profile'];
    this.router.navigate(navigationDetails);
  }
}
