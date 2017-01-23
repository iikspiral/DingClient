package cn.dc.ding.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by dongchen on 2017/1/23.
 */
public class DingTextMsg extends DingMsgHeader implements DingMsg{

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public JSONObject format() {
        JSONObject obj = super.format();
        obj.put("msgtype", "text");
        JSONObject text = new JSONObject();
        text.put("content", content);
        obj.put("text", text);
        return obj;
    }


    @Override
    public String getJsonString() {
        return this.format().toString();
    }
}
