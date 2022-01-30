import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from '../models/group.model';
import { User } from '../models/user.model';
import BuildUser from '../utils/build-user';

const API_URL = 'http://localhost:3000/users/'; // Temporary, until backend is connected

/*const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};*/

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  updateUser(data: any): Observable<any> {
    // let builtUser = BuildUser.userBuilder(data);
    return this.http.put(API_URL + data.id, JSON.stringify(data)/*, httpOptions*/);
    //return this.http.post(AUTH_API + 'update', JSON.stringify({JSON.stringify({data})); // Ensure endpoint is correct
  }

  createGroup(group: Group): Observable<any> {
    return this.http.put(API_URL, JSON.stringify(group))
  }

}
