package com.maersk.multiplication.challenge;


import com.maersk.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt) {
        // Check if the attempt is correct
        boolean isCorrect = resultAttempt.guess() == resultAttempt.factorA() * resultAttempt.factorB();

        // We don't use identifiers for now
        User user = new User(null, resultAttempt.userAlias());

        // Builds the domain object, Null id for now.
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(null,
                user,
                resultAttempt.factorA(),
                resultAttempt.factorB(),
                resultAttempt.guess(),
                isCorrect);

        return challengeAttempt;
    }
}
