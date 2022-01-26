import { Component, OnInit } from '@angular/core';
import { UserViewGroup } from 'src/app/models/user-view-group.model';
import { GameGroupService } from 'src/app/_services/game-group.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-view-game-groups',
  templateUrl: './view-game-groups.component.html',
  styleUrls: ['./view-game-groups.component.css']
})
export class ViewGameGroupsComponent implements OnInit {

  groupSessions: UserViewGroup[] = [];

  constructor(
    private route: ActivatedRoute,
    private gameGroupService: GameGroupService,
    private location: Location) { }

  ngOnInit(): void {
    this.getGroupSessions();
  }

  id!:number;

  getGroupSessions(){
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.gameGroupService.getGroups(this.id)
      .subscribe(
        (data) => {
          this.groupSessions = data;
        },
        (error) => {
          console.log(error);
        }
      )
  }
  
  goBack(): void{
    this.location.back();
  }

}