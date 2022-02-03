import { Social } from "../models/social.model";

export class SocialsBuilder {

    constructor() {}

    static buildSocials(data: any) {
        
        let userId: number = data.social.userid;
        let gameId: number = data.social.gameid;
        let gamertag: number = data.social.gamertag;
        let profileURL: string = data.steamProfile.response.players.player.profileurl;
        let avatar: string = data.steamProfile.response.players.player.avatar;
        let avatarMedium: string = data.steamProfile.response.players.player.avatarmedium;
        let avatarFull: string = data.steamProfile.response.players.player.avatarfull;
        let avatarHash: string = data.steamProfile.response.players.player.avatarhash;
        
        let socials: Social = new Social(userId, gameId, gamertag, profileURL, avatar, avatarMedium, avatarFull, avatarHash);
        return socials;
    }
}
