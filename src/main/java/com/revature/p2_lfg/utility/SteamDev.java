package com.revature.p2_lfg.utility;

public enum SteamDev {

    BASE_URL("http://api.steampowered.com/"),
    SUMMARY("ISteamUser/GetPlayerSummaries/v0001/"),
    KEY("9?key=AEEA5F03CDDB51E2408BBAA26EB114F"),
    ACHIEVEMENTS("ISteamUserStats/GetPlayerAchievements/v0001/");

    private final String key;

    SteamDev(String keyValue){
        this.key = keyValue;
    }

    public String getKey(){
        return key;
    }

}
