export class AvailableGames {

    constructor(
        private _gameId: number,
        private _gameName: string,
        private _gameUrl: string,
        private _currentGroups: number
    ){
        this._gameId = _gameId;
        this._gameName = _gameName;
        this._gameUrl = _gameUrl;
        this._currentGroups = _currentGroups;
    }

    public get gameId(): number {
        return this._gameId;
    }
    public set gameId(value: number) {
        this._gameId = value;
    }
    public get currentGroups(): number {
        return this._currentGroups;
    }
    public set currentGroups(value: number) {
        this._currentGroups = value;
    }
    public get gameUrl(): string {
        return this._gameUrl;
    }
    public set gameUrl(value: string) {
        this._gameUrl = value;
    }
    public get gameName(): string {
        return this._gameName;
    }
    public set gameName(value: string) {
        this._gameName = value;
    }

    //gameId: number
    //gameName: string
    //gameUrl: string
    //currentGroups: number


}
