package com.example.portailci.exposition.security;

import com.example.portailci.application.thematique.ManagementThematiqueImpl;
import com.example.portailci.security.AppAuthProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = ManagementThematiqueImpl.class)
@ExtendWith(SpringExtension.class)
public class AppAuthProvidesTest {
    @Autowired
    private AppAuthProvider appAuthProvider;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;



}
