package entity;

import trackstrategy.AbsTrackStrategy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 会议对象
 */
public class Conference {

    private List<Track> trackList;
    private Dispatcher dispatcher;

    /**
     * 本次会议所有talk耗时
     */
    private int totalConsume;


    public List<Track> getTrackList() {
        return trackList;
    }

    public Conference setTrackList(List<Track> trackList) {
        this.trackList = trackList;
        return this;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public Conference setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        return this;
    }

    public int getTotalConsume() {
        return totalConsume;
    }

    public Conference setTotalConsume(int totalConsume) {
        this.totalConsume = totalConsume;
        return this;
    }

    /**
     * 计算需要的track数量
     * @param allTalkConsume 所有talk数量
     * @return track数
     */
    public int calculateTrackCount(int allTalkConsume) {
        double count = allTalkConsume / Const.TOTAL_DURATION;
        return (int) Math.ceil(count);
    }

    /**
     * 根据策略生成每个track
     * @param trackCount 要生成的track数量
     * @param strategy 每个track里构造session的策略
     * @return track集合
     */
    public List<Track> createTrack(int trackCount, AbsTrackStrategy strategy) {
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < trackCount; i++) {
            trackList.add(Track.TrackFactory.create(strategy));
        }
        return trackList;
    }

    public String formatOutput() {
        StringBuffer stringBuffer = new StringBuffer();
        int trackId = 1;
        for (Track track : trackList) {
            stringBuffer.append(String.format("Track %d:%n", trackId));
            for (Session session : track.getSessions().values()) {
                List<Talk> talks = session.getTalks();
                for (Talk talk : talks) {
                    LocalTime startTime = talk.getStartTime();
                    String title = talk.getTitle();
                    stringBuffer.append(String.format("%s %s %s%n", timeFormat(startTime), title, talk.isDisplayConsume() ? talk.getConsumeStr() : ""));
                }

            }
            trackId++;
            stringBuffer.append(String.format("%n"));
        }
        return stringBuffer.toString();
    }

    private String timeFormat(LocalTime startTime) {
        return DateTimeFormatter.ofPattern("hh:mm a").format(startTime);
    }

    /**
     * 派发talk到track集合的每个session中
     *
     * @param talkList
     * @return
     */
    public void dispatch(List<Talk> talkList) {
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
