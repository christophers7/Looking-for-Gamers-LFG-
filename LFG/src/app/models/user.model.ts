import { Group } from "./group.model"

export class User {

    private _firstName: string = "";
    private _lastName: string = ""; 
    private _stats: string = "";
    
    private _appCount: number = 0;
    private _inGroup: boolean = false;
    
    private _group!: Group;
    

    constructor(private _id: number,private _username: string, private _email: string){
        this._id = _id;
        this._username = _username;
        this._email = _email;
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
