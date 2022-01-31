package com.revature.p2_lfg.service.profile.interfaces;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.GroupSocialResponse;
import com.revature.p2_lfg.presentation.models.profile.responses.UserSocialResponse;
import com.revature.p2_lfg.utility.JWTInfo;

public interface SocialsServiceable {
    UserSocialResponse getUserSocialResponse(JWTInfo parsedJWT, int gameId);

    GroupSocialResponse getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT);

    UserSocialResponse createUserSocial(CreateSocialRequest socialRequest, JWTInfo parsedJWT);

    UserSocialResponse updateUserSocial(UpdateSocialRequest updateSocial, JWTInfo parsedJWT);

    boolean deleteSocial(DeleteSocialRequest deleteSocial, JWTInfo parsedJWT);
}
