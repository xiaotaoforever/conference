package sessiontyoe.impl;

import entity.Session;
import entity.Talk;
import sessiontyoe.SessionType;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class normalSessionType implements SessionType {

    @Override
    public List<Talk> createTalk(Session session, List<Talk> talkList) {
        List<Talk> talks;
        int consume = session.getConsume();
        talks = new ArrayList<>();
        LocalTime lastStartTime = session.getBeginTime();
        Iterator<Talk> iterator = talkList.iterator();
        while (iterator.hasNext()) {
            Talk talk = iterator.next();
            if (talk.getConsume() <= consume) {
                talk.setStartTime(lastStartTime);
                talks.add(talk);
                consume -= talk.getConsume();
                iterator.remove();
                if (talk.getConsumeStr().contains("lightning") ) {
                    lastStartTime = lastStartTime.plus(talk.getConsume(), ChronoUnit.MINUTES);
                } else {
                    lastStartTime = lastStartTime.plus(talk.getConsume()+10, ChronoUnit.MINUTES);
                }


            }
        }
        return talks;
    }
}
