package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.SocialResponse;
import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.repository.interfaces.SessionRepository;
import com.revature.p2_lfg.repository.interfaces.SocialsRepository;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.user.Socials;
import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.profile.exception.InvalidRequestException;
import com.revature.p2_lfg.service.profile.exception.InvalidUserIdException;
import com.revature.p2_lfg.service.profile.interfaces.SocialsServiceable;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.service.social.SteamService;
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
    private SteamService steamService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public SocialResponse getUserSocialResponse(JWTInfo parsedJWT, int gameId) {
        dLog.debug("Getting userSocial");
        try{
            return convertSocialToSocialResponse(getUserSocial(parsedJWT.getUserId(), gameId));
        }catch(Exception e){
            dLog.debug(e.getMessage(), e);
            return failedSocialResponse();
        }
    }

    private SocialResponse failedSocialResponse() {
        return new SocialResponse.SocialResponseBuilder(false, new Socials()).build();
    }

    private SocialResponse convertSocialToSocialResponse(Socials userSocial) {
        return new SocialResponse.SocialResponseBuilder(true, userSocial)
                .steamProfile(steamService.getSteamProfile(userSocial))
                .build();
    }

    public Socials getUserSocial(int userId, int gameId) {
        return socialsRepository.findById(new SocialId(userId, gameId)).orElseThrow(InvalidRequestException::new);
    }

    @Override
    public List<SocialResponse> getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT) {
        dLog.debug("Getting Group Socials");
        List<GroupUser> groupUsers = getGroupMembersOfSession(groupId);
        List<SocialResponse> socials = new ArrayList<>();
        for (GroupUser groupUser : groupUsers) {
            if (groupUser.isInsideSession()){
                int userId = loginRepository.findIdByUsername(groupUser.getUsername());
                Socials social = socialsRepository.findById(new SocialId(userId, gameId)).orElseThrow(InvalidUserIdException::new);
                socials.add( new SocialResponse.SocialResponseBuilder(true, social)
                        .steamProfile(steamService.getSteamProfile(social))
                        .build());
            }
        }
        return socials;
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        dLog.debug("Getting group users associated by group Id: " + groupId);
        List<Session> userInSession = sessionRepository.findAllByGroupId(groupId);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.isPresent()? user.get().getUsername() : "NOT PRESENT", s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    @Override
    public SocialResponse createUserSocial(CreateSocialRequest socialRequest, JWTInfo parsedJWT) {
        return convertSocialToSocialResponse(socialsRepository.save(new Socials(parsedJWT.getUserId(), socialRequest.getGameId(), socialRequest.getSocial())));
    }

    @Override
    public SocialResponse updateUserSocial(UpdateSocialRequest updateSocial, JWTInfo parsedJWT) {
        try{
            Socials storedSocial = socialsRepository.findById(new SocialId(parsedJWT.getUserId(), updateSocial.getGameId())).orElseThrow(InvalidRequestException::new);
            storedSocial.setGamertag(updateSocial.getSocial());
            return new SocialResponse.SocialResponseBuilder(true, socialsRepository.save(storedSocial)).build();
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
            return new SocialResponse.SocialResponseBuilder(false, new Socials()).build();
        }
    }

    @Override
    public boolean deleteSocial(DeleteSocialRequest deleteSocial, JWTInfo parsedJWT) {
        return false;
    }
}
