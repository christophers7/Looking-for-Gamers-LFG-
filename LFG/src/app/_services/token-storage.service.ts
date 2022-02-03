import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';
import { Injectable, ɵɵsetComponentScope } from '@angular/core';
import { Game } from '../models/game.model';
import { User } from '../models/user.model';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const SELECTED_GAME = 'sel-game';
const CREATED_GROUP = 'host-group';
const JOINED_GROUPS = 'joined-groups'

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  joinedGroups: any[] = [];

  signOut(): void {
    window.sessionStorage.clear();    
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    window.sessionStorage.setItem(JOINED_GROUPS, JSON.stringify(this.joinedGroups));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public getJoinedGroups(): any{
    return window.sessionStorage.getItem(JOINED_GROUPS);
  }

  addGroupToJoinedGroups(group:any):void{
    let joinedGroups = JSON.parse(this.getJoinedGroups());
    if(joinedGroups){
      joinedGroups.push(group);
      window.sessionStorage.setItem(JOINED_GROUPS, JSON.stringify(joinedGroups));
    }else{
      let newGroup:any[] = [group];
      window.sessionStorage.setItem(JOINED_GROUPS, JSON.stringify(newGroup));
    }
  }

  public addToJoinedGroup(group:any):void{
    if(this.checkIfMax() && this.checkIfDuplicate(group)){
      this.addGroupToJoinedGroups(group);
      console.log("added");
    }else{
      console.log("fail");
    };
  }

  public leaveAllGroups():void{
    let joinedGroups = JSON.parse(this.getJoinedGroups());
    if(joinedGroups){
      for(let i:number = 0; i < joinedGroups.length; i++){
        console.log(joinedGroups[i]);
        this.leaveGroup(joinedGroups[i].groupId);

      }
    }
  }

  public leaveGroup(groupId:number):void{
    console.log(`${groupId}`)      
    let joinedGroups = JSON.parse(this.getJoinedGroups());

    for(let i:number = 0; i < joinedGroups.length; i++){
      console.log(joinedGroups[i])
      if(groupId == joinedGroups[i].groupId) {
        console.log("found it");
        joinedGroups.splice(i, 1);
        window.sessionStorage.setItem(JOINED_GROUPS, JSON.stringify(joinedGroups));
        return;
      }
    } 
  }

  checkIfMax():boolean{
    let joinedGroups = JSON.parse(this.getJoinedGroups());
    if(joinedGroups) return !(joinedGroups.length >= 3)
    return true;
  }

  checkIfDuplicate(group:any):boolean{
    let joinedGroups = JSON.parse(this.getJoinedGroups());
    
    for(let i:number = 0; i < joinedGroups.length; i++){
      console.log(joinedGroups[i])
      if(group.groupId == joinedGroups[i].groupId || group.groupid == joinedGroups[i].groupId) {
        console.log("duplicate");
        return false;
      }
    } 
    return true;
  }


  public saveCreatedGroup(group: any): void{
    window.sessionStorage.removeItem(CREATED_GROUP);
    window.sessionStorage.setItem(CREATED_GROUP, JSON.stringify(group))
  }

  public getCreatedGroup():any{
    return window.sessionStorage.getItem(CREATED_GROUP);
  }

  public removeCreatedGroup():any{
    window.sessionStorage.removeItem(CREATED_GROUP);
  }

  public saveGame(game: any): void {
    window.sessionStorage.removeItem(SELECTED_GAME);
    window.sessionStorage.setItem(SELECTED_GAME, JSON.stringify(game));
  }

  public getGame(): any {
    const game = window.sessionStorage.getItem(SELECTED_GAME);
    if (game) {
      return JSON.parse(game);
    }

    return {};
  }
}
