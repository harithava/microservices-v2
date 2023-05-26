package com.maersk.multiplication.challenge;

import com.maersk.multiplication.user.User;
import com.maersk.multiplication.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChallengeAttemptRepository challengeAttemptRepository;

    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(userRepository, challengeAttemptRepository);
        given(challengeAttemptRepository.save(any())).will(returnsFirstArg());
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptDTO attemptDTO = new
                ChallengeAttemptDTO(50, 60, "Hariharan", 3000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();
        verify(userRepository).save(new User("Hariharan"));
        verify(challengeAttemptRepository).save(resultAttempt);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        User existingUser = new User(1L, "Hariharan");
        given(userRepository.findByAlias("Hariharan")).willReturn(Optional.of(existingUser));
        ChallengeAttemptDTO attemptDTO = new
                ChallengeAttemptDTO(50, 60, "Hariharan", 3000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();
        then(resultAttempt.getUser()).isEqualTo(existingUser);
        verify(userRepository, never()).save(new User("Hariharan"));
        verify(challengeAttemptRepository).save(resultAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        ChallengeAttemptDTO attemptDTO = new
                ChallengeAttemptDTO(50, 60, "Hariharan", 5000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }


}