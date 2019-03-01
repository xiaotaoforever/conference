package entity;

import trackstrategy.AbsTrackStrategy;

import java.util.Map;

public class Track {

    private Map<String, Session> sessions;

    private Track(){}
    private Track(Map sessions) {
        this.sessions = sessions;
    }

    public Map<String, Session> getSessions() {
        return sessions;
    }

    public Track setSessions(Map<String, Session> sessions) {
        this.sessions = sessions;
        return this;
    }



    public static class TrackFactory {
        public static Track create(AbsTrackStrategy strategy){
            Track track = new Track();
            // 根据session策略组装session到track中
            Map<String, Session> sessions = strategy.createSession();
            track.setSessions(sessions);
            return track;
        }
    }


}
