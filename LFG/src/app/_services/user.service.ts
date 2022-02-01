import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AvailableGames } from '../models/available-games.model';
import { Game } from '../models/game.model';
import { GroupUser } from '../models/group-user.model';
import { Group } from '../models/group.model';

const API_URL = 'http://localhost:8088/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  updateUser(data: any): Observable<any> {
    return this.http.post(API_URL + 'update', JSON.stringify(data)/*, httpOptions*/); 
  }

  generateGames(): Observable<any> {
    return this.http.get(API_URL + "game/available")
  }

  getSelectedGame(gameId: number): Observable<any> {
    return this.http.get(API_URL + 'game/select?gameId=' + gameId)
  }

  generateGroupsForGame(gameId: number): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(gameId))
  }

  getGroup(group: Group): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(group.groupID))
  }

  requestToJoinGroup(group: Group): Observable<any> {
    return this.http.post(API_URL + 'group/join?groupId=' + group.groupID + '&gameId=' + group.gameId, JSON.stringify(group))
  }

  leaveGroup(group: Group): Observable<any> {
    return this.http.delete(API_URL + 'group/leave?groupId=' + group.groupID + '&gameId=' + group.gameId)
  }

  leaveAllWaitingList(): Observable<any> {
    return this.http.get(API_URL)
  }

  createGroup(group: Group): Observable<any> {
    return this.http.post(API_URL + 'group/host', JSON.stringify(group), httpOptions)
  }

  refreshGroupMemberList(group: Group): Observable<any> {
    return this.http.get(API_URL + 'group/refresh?groupId=' + group.groupID + '&gameId=' + group.gameId)
  }

  acceptApplicant(applicant: GroupUser): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(applicant.username))
  }

  denyApplicant(applicant: GroupUser): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(applicant.username))
  }

  removeFromGroup(removedMember: GroupUser): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(removedMember.username))
  }

}
