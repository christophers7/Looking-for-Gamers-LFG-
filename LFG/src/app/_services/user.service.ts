import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AvailableGames } from '../models/available-games.model';
import { Game } from '../models/game.model';
import { GroupDetails } from '../models/group-details.model';
import { GroupUser } from '../models/group-user.model';
import { Group } from '../models/group.model';
import { UserViewGroup } from '../models/user-view-group.model';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  updateUser(data: any): Observable<any> {
    return this.http.post(API_URL + 'user/update', JSON.stringify(data), httpOptions); 
  }

  updateCredential(data: any): Observable<any> {
    return this.http.put(API_URL + 'login/update', JSON.stringify(data), httpOptions); 
  }

  generateGames(): Observable<any> {
    return this.http.get(API_URL + "game/available", httpOptions)
  }

  getSelectedGame(gameId: number): Observable<any> {
    return this.http.get(API_URL + 'game/select?gameId=' + gameId, httpOptions)
  }

  generateGroupsForGame(gameId: number): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(gameId))
  }

  getGroup(group: Group): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(group.groupId))
  }

  requestToJoinGroup(group: any): Observable<any> {
    return this.http.get(API_URL + 'group/join?groupId=' + group.groupid + '&gameId=' + group.game.gameid)
  }

  leaveGroup(group: Group): Observable<any> {
    return this.http.delete(API_URL + 'group/leave?groupId=' + group.groupId + '&gameId=' + group.gameId)
  }

  leaveAllWaitingList(): Observable<any> {
    return this.http.get(API_URL)
  }

  createGroup(group: any): Observable<any> {
    return this.http.post(API_URL + 'group/host', group, httpOptions)
  }

  cancelGroup(group: any): Observable<any>{
    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json',
      }),
      body: {
        groupId: group._groupId,
        gameId: group._gameId,
        groupLead: group._groupLead,
        groupDetails: group._groupDetails,
        groupMembers: group._groupMembers,
        waitingUsers: group._waitingUsers 
      },
    };
    return this.http.delete(API_URL + 'group/cancel', options);
  }

  refreshGroupMemberList(group: any): Observable<any> {
    return this.http.get(API_URL + 'group/refresh?groupId=' + group._groupId + '&gameId=' + group._gameId)
  }

  respondToUser(applicant: any): Observable<any> {
    return this.http.post(API_URL + 'group/respond', JSON.stringify(applicant), httpOptions);
  }


  endSession():void{
    let group: Group = JSON.parse(this.tokenStorage.getCreatedGroup());
    this.cancelGroup(group).subscribe(
    
      (date) => console.log(data)
    
      )
      console.log(group);
  }

  respondToApplicant(applicant:any, check:boolean): Observable<any>{
    let request = {
      groupId: applicant.groupId,
      username: applicant.username,
      insideSession: check
    }; 
    return this.respondToUser(request);
  }


}
function data(data: any): void {
  throw new Error('Function not implemented.');
}

