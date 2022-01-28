import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

// const AUTH_API = 'http://localhost:6432/login/';
const AUTH_API = 'http://localhost:3000/users'; // Temporary, until backend is connected

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  /**
   * Temporary, until backend is connected
   */
  login(username: string, password: string): Observable<any> {  
    return this.http.get<any>(AUTH_API);
  }
  /**
   * End temporary
   */

//  login(username: string, password: string): Observable<any> {
//    return this.http.post(AUTH_API + "check",  JSON.stringify({username: username, password: password}), httpOptions);  
//  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API, JSON.stringify({
    //return this.http.post(AUTH_API + 'new', JSON.stringify({
      username: username,
      email: email,
      password: password
    }), httpOptions);
  }

}
