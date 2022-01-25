export class GroupUser {

    private _id: number;
    private _username: string;
    private _stats: string;
    
    //username
    //groupId
    //social object
    //stats
    //insideSession

    constructor (id: number, username: string, stats: string) {
        this._id = id;
        this._username = username;
        this._stats = stats;
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

    public get stats(): string {
        return this._stats;
    }
    public set stats(value: string) {
        this._stats = value;
    }
}
