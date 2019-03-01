package entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Talk {
    /**
     * title
     */
    private String title;

    /**
     * 耗时
     */
    private int consume;
    private LocalTime startTime;
    private boolean displayConsume;
    private String consumeStr;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public Talk setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public Talk() {
    }

    public Talk(String title, int consume, String consumeStr,boolean flag) {
        this.title = title;
        this.consume = consume;
        this.consumeStr = consumeStr;
        this.displayConsume = true;
        this.flag = flag;
    }

    public Talk(String title, int consume) {
        this.title = title;
        this.consume = consume;
        this.displayConsume = true;
    }

    public Talk(String title, int consume, boolean displayConsume) {
        this.title = title;
        this.consume = consume;
        this.displayConsume = displayConsume;
    }

    public String getTitle() {
        return title;
    }

    public Talk setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getConsume() {
        return consume;
    }

    public Talk setConsume(int consume) {
        this.consume = consume;
        return this;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public Talk setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public boolean isDisplayConsume() {
        return displayConsume;
    }

    public Talk setDisplayConsume(boolean displayConsume) {
        this.displayConsume = displayConsume;
        return this;
    }

    public String getConsumeStr() {
        return consumeStr;
    }

    public Talk setConsumeStr(String consumeStr) {
        this.consumeStr = consumeStr;
        return this;
    }

    /**
     * 根据str解析成talk对象
     *
     * @param talkStr
     * @return talk对象
     */
    private static Talk parseTalkStr(String talkStr) {
        String title = talkStr.substring(0, talkStr.lastIndexOf(" "));
        String consumeStr = talkStr.substring(talkStr.lastIndexOf(" ") + 1);
        int consume;
        boolean flag = false;
        if ("lightning".equals(consumeStr)) {
            consume = Const.LIGHTNING_DURATION;
            flag = true;
        }else {
            consume = Integer.valueOf(consumeStr.replaceAll("\\D+", ""));
        }
        return new Talk(title,consume,consumeStr,flag);
    }

    /**
     * 批量解析str成talk对象集合
     * @param talkStrList
     * @return
     */
    public static List<Talk> parseTalkStr(List<String> talkStrList){
        if (talkStrList == null || talkStrList.size() == 0) {
            throw new IllegalArgumentException("文本命令为空！");
        }
        List<Talk> talks = new ArrayList<>();
        talkStrList.forEach(s -> talks.add(parseTalkStr(s)));
        return talks;
    }


}
