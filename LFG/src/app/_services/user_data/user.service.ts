import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from 'src/app/models/group.model';
import { SessionStorageService } from '../sessions/session-storage.service';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8080/';

const ENDPOINTS = {
  LOGIN_CHECK: `${API_URL}login/check`,
  NEW_LOGIN: `${API_URL}login/new`,
  UPDATE_CREDENTIALS: `${API_URL}login/update`,
  USER_PROFILE: `${API_URL}user/profile`,
  UPDATE_PROFILE: `${API_URL}user/update`,
  AVAILABLE_GAMES: `${API_URL}game/available`,
  SELECTED_GAME: `${API_URL}game/select?gameId=`,
  JOIN_GROUP_GROUP_ID: `${API_URL}group/join?groupId=`,
  AND_GAME_ID: `&gameId=`,
  CHECK_GROUP_RESPONSE: `${API_URL}group/check?groupId=`,
  LEAVE_GROUP_GROUP_ID: `${API_URL}group/leave?groupId=`,
  CHECK_GROUP_MEMBERS: `${API_URL}group/member-check?groupId=`,
  LEAVE_ALL_GROUPS: `${API_URL}group/leave-all`,
  HOST_GROUP: `${API_URL}group/host`,
  HOST_CANCEL_GROUP: `${API_URL}group/cancel`,
  HOST_REFRESH_MEMBERS: `${API_URL}group/refresh?groupId=`,
  HOST_RESPOND: `${API_URL}group/respond`
}

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient, 
    private tokenStorage: TokenStorageService,
    private sessionStorage: SessionStorageService) { }

  updateUser(data: any): Observable<any> {return this.http.post(ENDPOINTS.UPDATE_PROFILE, JSON.stringify(data), httpOptions); }

  updateCredential(data: any): Observable<any> {return this.http.put(ENDPOINTS.UPDATE_CREDENTIALS, JSON.stringify(data), httpOptions); }

  generateGames(): Observable<any> {return this.http.get(ENDPOINTS.AVAILABLE_GAMES, httpOptions)}

  getSelectedGame(gameId: number): Observable<any> {return this.http.get(`${ENDPOINTS.SELECTED_GAME + gameId}`, httpOptions)}

  getGroup(group: Group): Observable<any> {return this.http.post(API_URL, JSON.stringify(group.groupId))}

  requestToJoinGroup(group: any): Observable<any> {
    return this.http.get(
      ENDPOINTS.JOIN_GROUP_GROUP_ID + group.groupid + 
      ENDPOINTS.AND_GAME_ID + group.game.gameid)
  }

  refreshWaitingList(groupId:any):Observable<any>{return this.http.get(`${ENDPOINTS.CHECK_GROUP_RESPONSE + groupId}`)}

  leaveGroup(group: Group): Observable<any> {
    return this.http.delete(
      ENDPOINTS.LEAVE_GROUP_GROUP_ID + group.groupId +
      ENDPOINTS.AND_GAME_ID + group.gameId)
  }

  refreshOtherMembers(groupId: any): Observable<any>{return this.http.get(`${ENDPOINTS.CHECK_GROUP_MEMBERS + groupId}`)}

  leaveAllWaitingList(): Observable<any> {return this.http.delete(ENDPOINTS.LEAVE_ALL_GROUPS,httpOptions)}

  createGroup(group: any): Observable<any> {return this.http.post(ENDPOINTS.HOST_GROUP, group, httpOptions)}

  cancelGroup(group: any): Observable<any>{return this.http.delete(ENDPOINTS.HOST_CANCEL_GROUP, this.cancelGroupOptions(group));}

  refreshGroupMemberList(group: any): Observable<any> {
    return this.http.get(
      ENDPOINTS.HOST_REFRESH_MEMBERS + group._groupId + 
      ENDPOINTS.AND_GAME_ID + group._gameId)
  }

  respondToUser(applicant: any): Observable<any> {return this.http.post(ENDPOINTS.HOST_RESPOND, JSON.stringify(applicant), httpOptions);}

  endSession():void{
    let group: Group = JSON.parse(this.sessionStorage.getCreatedGroup());
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

  cancelGroupOptions(group:any):any{
    return {
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
  }
}

function data(data: any): void {
  throw new Error('Function not implemented.');
}
