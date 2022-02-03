import { Injectable } from '@angular/core';

const KEYS = {
  TOKEN_KEY: 'auth-token',
  USER_KEY:'auth-user',
  SELECTED_GAME: 'sel-game',
  CREATED_GROUP: 'host-group',
  JOINED_GROUPS: 'joined-groups',
}

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor(
) { }

  updateGroup(group:any):void{
    console.log(group)
    let storing = {
      _groupLead : group.hostUser,
      _groupDetails: group.sessionDetails,
      _groupMembers: group.groupMembers,
      _waitingUsers: group.waitingMembers
    }
    window.sessionStorage.removeItem(KEYS.CREATED_GROUP);
    window.sessionStorage.setItem(KEYS.CREATED_GROUP, JSON.stringify(storing))
  }

  addToWaitingRoom(group:any):void{
    let joinedGroups = this.getWaitingGroups();
    if(joinedGroups){
      joinedGroups.push(group);
      window.sessionStorage.setItem(KEYS.JOINED_GROUPS, JSON.stringify(joinedGroups));
    }else{
      let newGroup:any[] = [group];
      window.sessionStorage.setItem(KEYS.JOINED_GROUPS, JSON.stringify(newGroup));
    }
  }

  public getWaitingGroups(): any{
    const joinedGroups =  window.sessionStorage.getItem(KEYS.JOINED_GROUPS);
    if(joinedGroups) return JSON.parse(joinedGroups);
  }

  public addToGroups(group:any):void{
    if(this.checkIfMax() && this.checkIfDuplicate(group)){
      this.addToWaitingRoom(group);
      console.log("added");
    }else{
      console.log("fail");
    };
  }

  public leaveAllGroups():void{
    let joinedGroups = this.getWaitingGroups();
    if(joinedGroups){
      for(let i:number = 0; i < joinedGroups.length; i++){
        console.log(joinedGroups[i]);
        this.leaveGroup(joinedGroups[i].groupId);

      }
    }
  }

  public leaveGroup(groupId:number):void{
    console.log(`${groupId}`)      
    let joinedGroups = this.getWaitingGroups();

    for(let i:number = 0; i < joinedGroups.length; i++){
      console.log(joinedGroups[i])
      if(groupId == joinedGroups[i].groupId) {
        console.log("found it");
        joinedGroups.splice(i, 1);
        window.sessionStorage.setItem(KEYS.JOINED_GROUPS, JSON.stringify(joinedGroups));
        return;
      }
    } 
  }

  checkIfMax():boolean{
    let joinedGroups = this.getWaitingGroups();
    if(joinedGroups) return !(joinedGroups.length >= 3)
    return true;
  }

  checkIfDuplicate(group:any):boolean{
    let joinedGroups = this.getWaitingGroups();
    
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
    window.sessionStorage.removeItem(KEYS.CREATED_GROUP);
    window.sessionStorage.setItem(KEYS.CREATED_GROUP, JSON.stringify(group))
  }

  public getCreatedGroup():any{
    return window.sessionStorage.getItem(KEYS.CREATED_GROUP);
  }

  public removeCreatedGroup():any{
    window.sessionStorage.removeItem(KEYS.CREATED_GROUP);
  }

  public saveGame(game: any): void {
    window.sessionStorage.removeItem(KEYS.SELECTED_GAME);
    window.sessionStorage.setItem(KEYS.SELECTED_GAME, JSON.stringify(game));
  }

  public getGame(): any {
    const game = window.sessionStorage.getItem(KEYS.SELECTED_GAME);
    if (game) {
      return JSON.parse(game);
    }
    return {};
  }
}
