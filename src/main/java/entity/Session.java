package entity;

import sessiontyoe.SessionType;

import java.time.LocalTime;
import java.util.List;

/**
 * 会话
 */
public class Session {
    /**
     * 会话开始时间
     */
    private LocalTime beginTime;

    private int consume;

    private List<Talk> talks;
    private SessionType sessionType;
    public Session() {
    }

    public Session(LocalTime beginTime, int consume) {
        this.beginTime = beginTime;
        this.consume = consume;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public Session setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public int getConsume() {
        return consume;
    }

    public Session setConsume(int consume) {
        this.consume = consume;
        return this;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public Session setTalks(List<Talk> talks) {
        this.talks = talks;
        return this;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public Session setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
        return this;
    }

    /**
     *
     * @param talkList
     * @return
     */
    public List<Talk> createTalks(List<Talk> talkList) {
        SessionType sessionType = getSessionType();
        if (sessionType == null) {
            throw new IllegalArgumentException("session没有设置SessionType属性！");
        }
        List<Talk> talks = sessionType.createTalk(this, talkList);
        return talks;
    }

}
