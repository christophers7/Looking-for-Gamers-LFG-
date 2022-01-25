import { Group } from "./group.model"

export class User {

    private _id: number;
    private _username: string;
    private _firstName: string;
    private _lastName: string; 
    private _email: string;
    private _stats: string;
    
    private _appCount: number;
    private _inGroup: boolean;
    
    private _group: Group;
    

    constructor(id: number, username: string, firstName: string, lastName: string, email: string, 
            stats: string, appCount: number, inGroup: boolean, group: Group){
        this._id = id;
        this._username =username;
        this._firstName = firstName;
        this._lastName = lastName;
        this._email = email;
        this._stats = stats;
        this._appCount = appCount;
        this._inGroup = inGroup;
        this._group = group;
    }

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }


    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }


    public get firstName(): string {
        return this._firstName;
    }
    public set firstName(value: string) {
        this._firstName = value;
    }


    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }

    
    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }


    public get stats(): string {
        return this._stats;
    }
    public set stats(value: string) {
        this._stats = value;
    }


    get appCount() {
        return this._appCount;
    }
    set appCount(value: number) {
        this._appCount = value;
    }

    resetAppCount(): void {
        this.appCount = 0;
    }
    incrementAppCount(): void {
        this.appCount++;
    }
    decrementAppCount(): void {
        this.appCount--;
    }


    public get inGroup(): boolean {
        return this._inGroup;
    }
    public set inGroup(value: boolean) {
        this._inGroup = value;
    }

    
    public get group(): Group {
        return this._group;
    }
    public set group(value: Group) {
        this._group = value;
    }
}
