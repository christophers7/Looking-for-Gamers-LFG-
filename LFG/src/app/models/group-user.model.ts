export class GroupUser {

    //username
    //groupId
    //social object
    //stats
    //insideSession

    private _stats: string = "";
    private _inGroup: boolean = false;
    
    constructor (
        private _username: string, 
    ){
        this._username = _username;;
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
