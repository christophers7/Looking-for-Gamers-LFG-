import { Component, Input, OnInit } from '@angular/core';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-host-view',
  templateUrl: './host-view.component.html',
  styleUrls: ['./host-view.component.css']
})
export class HostViewComponent implements OnInit {

  currentUser: any;

  @Input()
  group!: Group;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
  }

  checkUsername(value: string) {
    if(value === this.currentUser._username) {
      return false;
    }
    else {
      return true;
    }
  }
}
