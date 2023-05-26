package com.maersk.multiplication.challenge;

public interface ChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     *
     * @param resultAttempt
     * @return the resulting Challenge Attempt object.
     */
    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);
}
