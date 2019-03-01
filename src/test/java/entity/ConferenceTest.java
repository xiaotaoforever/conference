package entity;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trackstrategy.AbsTrackStrategy;
import trackstrategy.TrackStrategyImpl;
import util.FileTool;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class ConferenceTest {
    Conference conference;
    AbsTrackStrategy strategy;

    @Before
    public void setUp() throws Exception {
        conference = new Conference();
        strategy = new TrackStrategyImpl();
    }

    @Test
    public void calculateTrackCount() {
        int trackCount = conference.calculateTrackCount(1000);
        Assert.assertThat(trackCount, is(3));
        trackCount = conference.calculateTrackCount(800);
        Assert.assertThat(trackCount, is(2));
    }

    @Test
    public void createTrack() {
        List<Track> tracks = conference.createTrack(2, strategy);
        Assert.assertThat(tracks.size(), is(2));
        Assert.assertThat(tracks.get(0).getSessions().size(), is(4));
    }

    @Test
    public void formatOutput() {
        //1.文件解析，生成talk列表
        List<String> talksStr = FileTool.readFile(Const.TALKS_INPUT_FILE);
        List<Talk> talkList = Talk.parseTalkStr(talksStr);
        int allTalkConsume = 0;
        for (Talk talk : talkList) {
            allTalkConsume += talk.getConsume();
        }
        //2.根据列表生成tracklist
        conference.setTrackList(conference.createTrack(conference.calculateTrackCount(allTalkConsume), new TrackStrategyImpl()));
        //3.生成conference，并通过调度将talks分配进每个track,组装进conference
        conference.setDispatcher(new Dispatcher().setTalkList(talkList));
        conference.getDispatcher().dispatch(conference.getTrackList());
        System.out.println(conference.formatOutput());
        String output = conference.formatOutput();
        Assert.assertThat(output, StringContains.containsString("Track 1"));
        Assert.assertThat(output, StringContains.containsString("LUNCH"));
        Assert.assertThat(output, StringContains.containsString("Rails for Python Developers lightning"));
        Assert.assertThat(output, StringContains.containsString("下午 NETWORKING EVENT"));
        String expected = "Track 1:\n" +
                "09:00 上午 Writing Fast Tests Against Enterprise Rails 60min\n" +
                "10:00 上午 Communicating Over Distance 60min\n" +
                "11:00 上午 Rails Magic 60min\n" +
                "12:00 下午 LUNCH \n" +
                "01:00 下午 Ruby on Rails: Why We Should Move On 60min\n" +
                "02:00 下午 Ruby on Rails Legacy App Maintenance 60min\n" +
                "03:00 下午 Overdoing it in Python 45min\n" +
                "03:45 下午 Ruby Errors from Mismatched Gem Versions 45min\n" +
                "04:30 下午 Lua for the Masses 30min\n" +
                "05:00 下午 NETWORKING EVENT \n" +
                "\n" +
                "Track 2:\n" +
                "09:00 上午 Common Ruby Errors 45min\n" +
                "09:45 上午 Accounting-Driven Development 45min\n" +
                "10:30 上午 Pair Programming vs Noise 45min\n" +
                "11:15 上午 Clojure Ate Scala (on my project) 45min\n" +
                "12:00 下午 LUNCH \n" +
                "01:00 下午 Woah 30min\n" +
                "01:30 下午 Sit Down and Write 30min\n" +
                "02:00 下午 Programming in the Boondocks of Seattle 30min\n" +
                "02:30 下午 Ruby vs. Clojure for Back-End Development 30min\n" +
                "03:00 下午 A World Without HackerNews 30min\n" +
                "03:30 下午 User Interface CSS in Rails Apps 30min\n" +
                "04:00 下午 Rails for Python Developers lightning\n" +
                "05:00 下午 NETWORKING EVENT " +
                "\n" +
                "\n";
        Assert.assertThat(output, is(expected));
    }

    @Test
    public void formatOutput2() {
        //1.文件解析，生成talk列表
        List<String> talksStr = FileTool.readFile(Const.TALKS_INPUT_FILE);
        List<Talk> talkList = Talk.parseTalkStr(talksStr);
        int allTalkConsume = 0;
        for (Talk talk : talkList) {
            allTalkConsume += talk.getConsume();
        }
        //2.根据列表生成tracklist
        conference.setTrackList(conference.createTrack(conference.calculateTrackCount(allTalkConsume), new TrackStrategyImpl()));
        //3.生成conference，并通过调度将talks分配进每个track,组装进conference
        conference.dispatch(talkList);
        //String expected = "Track 1:\n" +
        //        "09:00 上午 Writing Fast Tests Against Enterprise Rails 60min\n" +
        //        "10:00 上午 Communicating Over Distance 60min\n" +
        //        "11:00 上午 Rails Magic 60min\n" +
        //        "12:00 下午 LUNCH \n" +
        //        "01:00 下午 Ruby on Rails: Why We Should Move On 60min\n" +
        //        "02:00 下午 Ruby on Rails Legacy App Maintenance 60min\n" +
        //        "03:00 下午 Overdoing it in Python 45min\n" +
        //        "03:45 下午 Ruby Errors from Mismatched Gem Versions 45min\n" +
        //        "04:30 下午 Lua for the Masses 30min\n" +
        //        "05:00 下午 NETWORKING EVENT \n" +
        //        "\n" +
        //        "Track 2:\n" +
        //        "09:00 上午 Common Ruby Errors 45min\n" +
        //        "09:45 上午 Accounting-Driven Development 45min\n" +
        //        "10:30 上午 Pair Programming vs Noise 45min\n" +
        //        "11:15 上午 Clojure Ate Scala (on my project) 45min\n" +
        //        "12:00 下午 LUNCH \n" +
        //        "01:00 下午 Woah 30min\n" +
        //        "01:30 下午 Sit Down and Write 30min\n" +
        //        "02:00 下午 Programming in the Boondocks of Seattle 30min\n" +
        //        "02:30 下午 Ruby vs. Clojure for Back-End Development 30min\n" +
        //        "03:00 下午 A World Without HackerNews 30min\n" +
        //        "03:30 下午 User Interface CSS in Rails Apps 30min\n" +
        //        "04:00 下午 Rails for Python Developers lightning\n" +
        //        "05:00 下午 NETWORKING EVENT " +
        //        "\n" +
        //        "\n";
        String output = conference.formatOutput();
        System.out.println(output);
        //Assert.assertThat(output, is(expected));
        Assert.assertThat(output, StringContains.containsString("12:30 下午 LUNCH"));

        Assert.assertThat(output, StringContains.containsString("09:00 上午 Writing Fast Tests Against Enterprise Rails 60min"));
        Assert.assertThat(output, StringContains.containsString("10:10 上午 Communicating Over Distance 60min"));
        Assert.assertThat(output, StringContains.containsString("04:55 下午 java java lightning"));
        //Assert.assertThat(output, StringContains.containsString("05:10 下午 xxxxxxx in Rails Apps 1min"));
    }
}