package trackstrategy;

import entity.Session;

import java.util.Map;

public abstract class AbsTrackStrategy {

    public abstract Map<String, Session> createSession();

}
