package sessiontyoe;

import entity.Session;
import entity.Talk;

import java.util.List;

public interface SessionType {
    List<Talk> createTalk(Session session, List<Talk> talkList);

}
