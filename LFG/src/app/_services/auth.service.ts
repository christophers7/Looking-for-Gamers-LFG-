import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:6432/login/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    console.log(JSON.stringify({username: username, password: password}), httpOptions) 
    return this.http.post(AUTH_API + "check",  JSON.stringify({username: username, password: password}), httpOptions);  
    
  }

  register(username: string, firstname: string, lastname: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'new', JSON.stringify({
      username: username,
      firstName: firstname,
      lastName: lastname,
      email: email,
      password: password
    }), httpOptions);
  }

}
