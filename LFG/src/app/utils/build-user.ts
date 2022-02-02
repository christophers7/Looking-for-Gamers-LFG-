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
        if(data.email) user._email = data._email;

        return user;
    }

    static credentialBuilder(data:any){
        let id: number = data.id;
        let username:string = data.username;
        let password:string = data.password;

        let user: User = new User(id, username, password);

        if (data.firstName) {
            user._firstName = data.firstName;
        }
        if (data.lastName) {
            user._lastName = data.lastName;
        }
        if(data.username) user._username = data._username;

        if(data.password) user._password = data._password;

        if(data.email) user._email = data._email;

        return user;
    }

}
