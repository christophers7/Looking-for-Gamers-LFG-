package com.revature.p2_lfg.service.social;

import com.revature.p2_lfg.utility.SteamDev;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SteamUrl {
    String baseUrl;
    String method;
    String key;
    String appId;
    String userid;

    private SteamUrl(SteamUrlBuilder builder){
        this.baseUrl = builder.baseUrl.toString();
        this.method = builder.method.toString();
        this.key = builder.key.toString();
        this.appId = builder.appId;
        this.userid = builder.userId;
    }

    public static class SteamUrlBuilder{
        private final SteamDev baseUrl;
        private final SteamDev method;
        private SteamDev key;
        private String appId;
        private String userId;

        public SteamUrlBuilder(SteamDev baseUrl, SteamDev method){
            this.baseUrl = baseUrl;
            this.method = method;
        }

        public SteamUrlBuilder userId(String userId){
            this.userId = userId;
            return this;
        }
        public SteamUrlBuilder key(SteamDev key){
            this.key = key;
            return this;
        }

        public SteamUrl build(){
            SteamUrl steamUrl = new SteamUrl(this);
            validateSteamUrlObject(steamUrl);
            return steamUrl;
        }

        public SteamUrlBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        private void validateSteamUrlObject(SteamUrl steamUrl){
            //no validation yet
        }
    }
}
