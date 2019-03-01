package entity;

import org.junit.Assert;
import org.junit.Test;
import util.FileTool;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class TalkTest {

    @Test
    public void parseTalkStr() {
        List<String> talkStrList = Arrays.asList("cxxx 1231 12min","321321 10min");
        Talk talk = new Talk();
        List<Talk> talks = talk.parseTalkStr(talkStrList);
        talks.forEach(System.out::println);
        Assert.assertThat(talks.get(0).getTitle(),is("cxxx 1231"));
        Assert.assertThat(talks.get(0).getConsume(),is(12));
        Assert.assertThat(talks.get(1).getTitle(),is("321321"));
        Assert.assertThat(talks.get(1).getConsume(),is(10));

        List<String> talkStrList1 = FileTool.readFile("talks");
        List<Talk> talks1 = talk.parseTalkStr(talkStrList1);
        talks1.forEach(talk1 -> System.out.println(talk1.getTitle()+" "+talk1.getConsume()+"min"));

    }
}