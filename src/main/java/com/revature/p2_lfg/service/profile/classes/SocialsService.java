package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.presentation.models.profile.*;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDao;
import com.revature.p2_lfg.repository.DAO.implementation.SocialsDao;
import com.revature.p2_lfg.repository.DAO.implementation.UserCredentialsDao;
import com.revature.p2_lfg.repository.entities.Session;
import com.revature.p2_lfg.repository.entities.Socials;
import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import com.revature.p2_lfg.service.profile.interfaces.SocialsServiceable;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.utility.JWTInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("socialsService")
public class SocialsService implements SocialsServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SocialsDao socialsDao;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private UserCredentialsDao userCredentialsDao;

    @Override
    public UserSocialResponse getUserSocialResponse(JWTInfo parsedJWT, int gameId) {
        dLog.debug("Getting userSocial");
        return convertSocialToUserSocialResponse(getUserSocial(parsedJWT.getUserId(), gameId));
    }

    private UserSocialResponse convertSocialToUserSocialResponse(Socials userSocial) {
        return new UserSocialResponse(
              true,
              userSocial
        );
    }

    private Socials getUserSocial(int userId, int gameId) {
        return socialsDao.getUserSocial(userId, gameId);
    }

    @Override
    public GroupSocialResponse getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT) {
        dLog.debug("Getting Group Socials");
        List<GroupUser> groupUsers = sessionDao.getGroupMembersByGroupId(groupId);
        List<Socials> socials = new ArrayList<>();
        for (GroupUser groupUser : groupUsers) {
            if (groupUser.isInsideSession()){
                int userId = userCredentialsDao.getUserWithUsername(groupUser.getUsername()).getUserID();
                socials.add(socialsDao.getSocial(new SocialId(userId, gameId)));
            }
        }
        return new GroupSocialResponse(socials);
    }

    @Override
    public UserSocialResponse createUserSocial(CreateSocialRequest socialRequest, JWTInfo parsedJWT) {
        return null;
    }

    @Override
    public UserSocialResponse updateUserSocial(UpdateSocialRequest updateSocial, JWTInfo parsedJWT) {
        return null;
    }

    @Override
    public boolean deleteSocial(DeleteSocialRequest deleteSocial, JWTInfo parsedJWT) {
        return false;
    }
}
