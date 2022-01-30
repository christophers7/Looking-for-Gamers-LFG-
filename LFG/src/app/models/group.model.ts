import { Input } from "@angular/core";
import { GroupUser } from "./group-user.model";
import { WaitingForGroup } from "./waiting-for-group.model";

export class Group {
    
    private _groupMembers: GroupUser[] = [];
    private _waitingUsers: GroupUser[] = [];
    // Add tag(s) when they're implemented

    constructor (
        private _groupId: number, 
        private _gameId: number, 
        private _groupLead: GroupUser,
        private _maxUsers: number, 
        private _currentSize: number,
        private _description: string
    ){
        this._groupId = _groupId;
        this._gameId = _gameId;
        this._groupLead = _groupLead;
        this._maxUsers = _maxUsers;
        this._currentSize = _currentSize;
        this._description = _description;
    }

    public get groupID(): number {
        return this._groupId;
    }
    public set groupID(value: number) {
        this._groupId = value;
    }


    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }


    public get groupLead(): GroupUser {
        return this._groupLead;
    }
    public set groupLead(value: GroupUser) {
        this._groupLead = value;
    }


    public get maxUsers(): number {
        return this._maxUsers;
    }
    public set maxUsers(value: number) {
        this._maxUsers = value;
    }

    public get currentSize(): number {
        return this._currentSize;
    }
    public set currentSize(value: number) {
        this._currentSize = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }


    public get groupMembers(): Array<GroupUser> {
        return this._groupMembers;
    }
    public set groupMembers(value: GroupUser[]) {
        this._groupMembers = value;
    }
    
    public get waitingUsers(): Array<GroupUser> {
        return this._waitingUsers;
    }
    public set waitingUsers(value: GroupUser[]) {
        this._waitingUsers = value;
    }

    addGroupMember(newMember: GroupUser) {
        if(this._groupMembers.length < this.maxUsers)
            this.waitingUsers.find(x => x !== newMember);
            this._groupMembers.push(newMember);
            // Tell server to add member to group and update waiting list
            if(this._groupMembers.length + 1 === this._maxUsers) {
                // tell server that group is full
                console.log("Group has filled!");
            } 
    }

    removeGroupMember(currentMember: GroupUser) {
        this._groupMembers.filter(x => x !== currentMember);
        // Tell server that member has been removed
    }

    // This is for other users interterested in joining the group
    addUserToWaitingList(interestedUser: GroupUser) {
        // Tell backend to add user to list and update host list
        this._waitingUsers.push(interestedUser);
    }

    // For both the host declining and other users canceling
    removeUserFromWaitingList(uninterestedUser: GroupUser) {
        this._waitingUsers.find(x => x !== uninterestedUser);
        // Tell server that user is no longer interested/has been declined
    }

    // Might not use these two methods as it may be better to get value change update from server
    incrementGroupSize() {
        this._currentSize++
    }
    decrementGroupSize() {
        this._currentSize--
    }

}
