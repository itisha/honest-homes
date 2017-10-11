package com.exadel.util;

import com.exadel.mongodb.model.Feedback;
import org.apache.commons.codec.digest.DigestUtils;

public class FeedbackUtils {

    public static String sha256Hex(Feedback feedback) {
        if (feedback.getId() == null) {
            throw new RuntimeException("Feedback should be stored in db and have id");
        }
        String dataBall = feedback.getId() +
                feedback.getAuthorId() +
                feedback.getEntityId() +
                feedback.getDescription() +
                feedback.getScore();
        return DigestUtils.sha256Hex(dataBall);
    }
}
