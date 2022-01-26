import { Input } from "@angular/core";
import { GroupUser } from "./group-user.model";

export class Group {
    

    private _groupMembers: GroupUser[] = [];

    //groupId;
    //gameId;
    //max users;
    //description;
    //groupMembers: 

    constructor (private _groupId: number, private _gameId: number, private _maxUsers: number, private _description: string) {
        this._groupId = _groupId;
        this._gameId = _gameId;
        this._maxUsers = _maxUsers;
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


    public get maxUsers(): number {
        return this._maxUsers;
    }
    public set maxUsers(value: number) {
        this._maxUsers = value;
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
    addGroupMember(newMember: GroupUser) {
        if(this._groupMembers.length + 1 < this.maxUsers)
            this._groupMembers.push(newMember);
            if(this._groupMembers.length + 1 === this._maxUsers) {
                // tell server that group is full
                console.log("Group has filled!");
            } 
    }
}
