package entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sessiontyoe.impl.lunchSessionType;
import sessiontyoe.impl.networkingEventSessionType;
import util.FileTool;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class SessionTest {
    List<Talk> talkList;
    Session session;
    @Before
    public void setUp() throws Exception {
        session = new Session();
        List<String> talksStr = FileTool.readFile(Const.TALKS_INPUT_FILE);
        talkList = Talk.parseTalkStr(talksStr);
    }
    @Test
    public void createTalks() {
        List<Talk> talks ;

        //try {
        //    session.createTalks(talkList);
        //    Assert.fail("没有捕获设置sessionType！");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        session.setSessionType(new lunchSessionType());
        talks = session.createTalks(talkList);
        Assert.assertThat(talks.size(),is(1));
        Assert.assertThat(talks.get(0).getTitle(),is("LUNCH"));
        session.setSessionType(new networkingEventSessionType());
        talks = session.createTalks(talkList);
        Assert.assertThat(talks.size(),is(1));
        Assert.assertThat(talks.get(0).getTitle(),is("NETWORKING EVENT"));

    }
}