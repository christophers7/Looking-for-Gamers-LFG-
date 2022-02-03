import { Component, OnInit } from '@angular/core';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(
    private routingAllocater: RoutingAllocatorService,
    private tokenStorage: TokenStorageService) { }

  currentUser: any;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
  }

  modifyProfile(): void { this.routingAllocater.modifyProfile(); }
  modifyCredential(): void { this.routingAllocater.modifyCredentials(); }
  modifySocials(): void { this.routingAllocater.modifySocials(); }
  goToMain(): void { this.routingAllocater.main(); }
}
