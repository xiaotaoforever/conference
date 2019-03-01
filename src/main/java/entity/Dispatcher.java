package entity;

import java.util.List;
import java.util.Map;

/**
 * talk 派发对象
 */
public class Dispatcher {

    private List<Talk> talkList;

    public List<Talk> getTalkList() {
        return talkList;
    }

    public Dispatcher setTalkList(List<Talk> talkList) {
        this.talkList = talkList;
        return this;
    }

    /**
     * 派发talk到track集合的每个session中
     *
     * @param trackList
     * @return
     */
    public void dispatch(List<Track> trackList) {
        talkList.sort((o1, o2) -> o2.getConsume() - o1.getConsume());
        for (Track track : trackList) {
            Map<String, Session> sessions = track.getSessions();
            for (String sessionName : sessions.keySet()) {
                Session session = sessions.get(sessionName);
                List<Talk> talks = session.createTalks(talkList);
                session.setTalks(talks);
            }
        }
    }
}
