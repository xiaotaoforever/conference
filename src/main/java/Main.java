import entity.Conference;
import entity.Const;
import entity.Dispatcher;
import entity.Talk;
import trackstrategy.TrackStrategyImpl;
import util.FileTool;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Conference conference = new Conference();
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

        //4.输出结果
        System.out.println(conference.formatOutput());
    }
}
