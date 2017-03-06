package cn.dc.ding.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by dongchen on 2017/3/6.
 */
public class DingRobotTextMsg implements DingMsg{
    public String getJsonString() {
        JSONObject obj = new JSONObject();
        obj.put("msgtype", "text");

        JSONObject text = new JSONObject();
        obj.put("text", text);
        text.put("content", content);

        JSONObject at = new JSONObject();
        obj.put("at", at);
        JSONArray atMobiles = new JSONArray();
        if (this.atMobiles != null) {
            for (String m : this.atMobiles) {
                atMobiles.add(m);
            }
        }
        at.put("atMobiles", atMobiles);
        at.put("isAtAll", this.isAtAll);

        return obj.toJSONString();
    }

    private String content;
    private List<String> atMobiles;
    private Boolean isAtAll = false;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public Boolean getAtAll() {
        return isAtAll;
    }

    public void setAtAll(Boolean atAll) {
        isAtAll = atAll;
    }
}
