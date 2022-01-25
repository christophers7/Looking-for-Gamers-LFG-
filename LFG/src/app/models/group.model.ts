import { GroupUser } from "./group-user.model";

export class Group {

    private _groupID: number;
    private _groupMembers: Array<GroupUser>;

    constructor (groupId: number, groupMemembers: Array<GroupUser>) {
        this._groupID = groupId;
        this._groupMembers = groupMemembers;
    }

    public get groupID(): number {
        return this._groupID;
    }
    public set groupID(value: number) {
        this._groupID = value;
    }

    public get groupMembers(): Array<GroupUser> {
        return this._groupMembers;
    }
    public set groupMembers(value: Array<GroupUser>) {
        this._groupMembers = value;
    }
    addGroupMember(newMember: GroupUser) {
        this._groupMembers.push(newMember);
    }
}
