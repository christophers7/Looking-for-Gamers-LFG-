import { User } from "../models/user.model";

export default class BuildUser {

    constructor() { }

    static userBuilder(data: any) {
        let id: number = data.id;
        let username: string = data.username;
        let email: string = data.email;

        let user: User = new User(id, username, email);

        if (data.firstName) {
            user._firstName = data.firstName;
        }
        if (data.lastName) {
            user._lastName = data.lastName;
        }
        return user;
    }
}
