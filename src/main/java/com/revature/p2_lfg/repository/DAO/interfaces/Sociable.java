package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.presentation.models.profile.UserSocialResponse;
import com.revature.p2_lfg.repository.entities.Socials;
import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;

public interface Sociable {

	Socials findPlatform(Socials platform);

    Socials getUserSocial(int userId, int gameId);

    Socials getSocial(SocialId socialId);
}
