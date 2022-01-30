export class UserViewGroup {

    constructor(
        private _groupId: number,
        private _gameId: number,
        private _maxUsers: number,
        private _currentUsers: number,
        private _description: string
    ){
        this.groupId = _groupId;
        this.gameId = _gameId;
        this.maxUsers = _maxUsers;
        this.currentUsers = _currentUsers;
        this.description = _description;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get currentUsers(): number {
        return this._currentUsers;
    }
    public set currentUsers(value: number) {
        this._currentUsers = value;
    }
    public get maxUsers(): number {
        return this._maxUsers;
    }
    public set maxUsers(value: number) {
        this._maxUsers = value;
    }
    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }
    public get groupId(): number {
        return this._groupId;
    }
    public set groupId(value: number) {
        this._groupId = value;
    }
}
