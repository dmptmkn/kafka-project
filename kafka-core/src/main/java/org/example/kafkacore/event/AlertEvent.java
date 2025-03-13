package org.example.kafkacore.event;

import java.io.Serializable;

public class AlertEvent implements Serializable {

    private final Integer alertId;
    private String stageId;
    private final String alertLevel;
    private final String alertMessage;

    public AlertEvent(Integer alertId,
                      String stageId,
                      String alertLevel,
                      String alertMessage) {
        this.alertId = alertId;
        this.stageId = stageId;
        this.alertLevel = alertLevel;
        this.alertMessage = alertMessage;
    }

    public Integer getAlertId() {
        return alertId;
    }

    public String getStageId() {
        return stageId;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    @Override
    public String toString() {
        return "AlertEvent{" +
               "alertId=" + alertId +
               ", stageId='" + stageId + '\'' +
               ", alertLevel='" + alertLevel + '\'' +
               ", alertMessage='" + alertMessage + '\'' +
               '}';
    }
}
