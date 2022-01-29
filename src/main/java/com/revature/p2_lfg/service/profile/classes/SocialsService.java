package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.presentation.models.profile.*;
import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.repository.interfaces.SessionRepository;
import com.revature.p2_lfg.repository.interfaces.SocialsRepository;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.user.Socials;
import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.profile.interfaces.SocialsServiceable;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.utility.JWTInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("socialsService")
public class SocialsService implements SocialsServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SocialsRepository socialsRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private LoginRepository loginRepository;

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
        return socialsRepository.findById(new SocialId(userId, gameId)).orElse(null);
    }

    @Override
    public GroupSocialResponse getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT) {
        dLog.debug("Getting Group Socials");
        List<GroupUser> groupUsers = getGroupMembersOfSession(groupId);
        List<Socials> socials = new ArrayList<>();
        for (GroupUser groupUser : groupUsers) {
            if (groupUser.isInsideSession()){
                int userId = loginRepository.findIdByUsername(groupUser.getUsername());
                socials.add(socialsRepository.findById(new SocialId(userId, gameId)).orElse(null));
            }
        }
        return new GroupSocialResponse(socials);
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        List<Session> userInSession = sessionRepository.findAllByGroupId(groupId);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserId());
            groupUsers.add(new GroupUser(user.isPresent()? user.get().getUsername() : "NOT PRESENT", s.getGroupSession().getGroupId(), s.isInSession()));
        });
        return groupUsers;
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
