package com.exadel.util;

import com.exadel.mongodb.model.Feedback;
import org.apache.commons.codec.digest.DigestUtils;

public class FeedbackUtils {

    public static String sha256Hex(Feedback feedback) {
        if (feedback.getId() == null) {
            throw new RuntimeException("Feedback should be stored in db and have id");
        }
        String dataBall = getDataBall(feedback);
        return DigestUtils.sha256Hex(dataBall);
    }

    public static String getDataBall(Feedback feedback) {
        return feedback.getId() +
                feedback.getAuthorId() +
                feedback.getEntityId() +
                feedback.getDescription() +
                feedback.getScore();
    }
}
