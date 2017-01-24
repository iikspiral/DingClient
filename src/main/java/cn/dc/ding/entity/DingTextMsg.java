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
        // TODO 每一个之类Msg都必须调用父类的format方法，才能构建完整的消息
        JSONObject obj = super.format();
        obj.put("msgtype", "text");
        JSONObject text = new JSONObject();
        text.put("content", content);
        obj.put("text", text);
        return obj;
    }

    public String getJsonString() {
        return this.format().toString();
    }
}
