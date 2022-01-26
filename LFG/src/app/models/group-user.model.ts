export class GroupUser {

    private _stats: string = "";
    private _inGroup: boolean = false;
    
    //username
    //groupId
    //social object
    //stats
    //insideSession

    constructor (private _id: number, private _username: string, ) {
        this._id = _id;
        this._username = _username;;
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

    public get inGroup(): boolean {
        return this._inGroup;
    }
    public set inGroup(value: boolean) {
        this._inGroup = value;
    }
}
