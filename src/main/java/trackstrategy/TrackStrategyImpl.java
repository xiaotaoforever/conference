package trackstrategy;

import entity.Const;
import entity.Session;
import sessiontyoe.impl.lunchSessionType;
import sessiontyoe.impl.networkingEventSessionType;
import sessiontyoe.impl.normalSessionType;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrackStrategyImpl extends AbsTrackStrategy {

    @Override
    public Map<String, Session> createSession() {
        Map<String, Session> map = new LinkedHashMap<>();
        map.put(Const.MORNING, new Session(LocalTime.parse("09:00"),Const.MORNING_DURATION).setSessionType(new normalSessionType()));
        map.put(Const.LUNCH, new Session(LocalTime.parse("12:30"),Const.LUNCH_DURATION).setSessionType(new lunchSessionType()));
        map.put(Const.AFTERNOON, new Session(LocalTime.parse("13:30"),Const.AFTERNOON_DURATION).setSessionType(new normalSessionType()));
        map.put(Const.NETWORKING_EVENT, new Session(LocalTime.parse("17:00"),0).setSessionType(new networkingEventSessionType()));
        return map;
    }


}
