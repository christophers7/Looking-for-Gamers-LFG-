package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.presentation.models.profile.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.UpdateSocialRequest;
import com.revature.p2_lfg.utility.JWTInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SocialsServiceTest {

    @Autowired
    private SocialsService underTest;

}