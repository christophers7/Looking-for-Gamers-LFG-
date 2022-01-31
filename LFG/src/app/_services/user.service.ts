import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GroupUser } from '../models/group-user.model';
import { Group } from '../models/group.model';

const API_URL = 'http://localhost:8088/';

/*const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};*/

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  updateUser(data: any): Observable<any> {
    return this.http.post(API_URL + 'update', JSON.stringify(data)); 
  }

  generateGroupsForGame(gameId: number): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(gameId))
  }

  getGroup(group: Group): Observable<any> {
    return this.http.post(API_URL, JSON.stringify(group.groupID))
  }

  requestToJoinGroup(group: Group): Observable<any> {
    return this.http.post(API_URL ,JSON.stringify(group.groupID))
  }

  createGroup(group: Group): Observable<any> {
    return this.http.put(API_URL, JSON.stringify(group))
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
