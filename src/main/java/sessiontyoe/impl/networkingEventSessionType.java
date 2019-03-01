package sessiontyoe.impl;

import entity.Const;
import entity.Session;
import entity.Talk;
import sessiontyoe.SessionType;

import java.util.ArrayList;
import java.util.List;

public class networkingEventSessionType implements SessionType {
    @Override
    public List<Talk> createTalk(Session session, List<Talk> talkList) {
        List<Talk> talks = new ArrayList<>();
        Talk networking = new Talk(Const.NETWORKING_EVENT, 0, false);
        networking.setStartTime(session.getBeginTime());
        talks.add(networking);
        return talks;
    }
}
