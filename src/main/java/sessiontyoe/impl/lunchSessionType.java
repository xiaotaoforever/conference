package sessiontyoe.impl;

import entity.Const;
import entity.Session;
import entity.Talk;
import sessiontyoe.SessionType;

import java.util.ArrayList;
import java.util.List;

public class lunchSessionType implements SessionType {
    @Override
    public List<Talk> createTalk(Session session, List<Talk> talkList) {
        List<Talk> talks = new ArrayList<>();
        Talk lunch = new Talk(Const.LUNCH, Const.LUNCH_DURATION, false);
        lunch.setStartTime(session.getBeginTime());
        talks.add(lunch);
        return talks;
    }
}
