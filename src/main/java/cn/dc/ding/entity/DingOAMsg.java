package cn.dc.ding.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dongchen on 2017/1/23.
 */
public class DingOAMsg extends DingMsgHeader implements DingMsg{

    @Override
    public JSONObject format() {
        JSONObject format = super.format();

        format.put("msgtype", "oa");
        JSONObject oa = new JSONObject();
        oa.put("message_url", getMessageUrl());
        oa.put("pc_message_url", getPcMessageUrl());
        JSONObject head = new JSONObject();
        head.put("bgcolor", getHead_bgcolor());
        head.put("text", getHead_text());
        oa.put("head", head);
        oa.put("body", getBody());
        format.put("oa", oa);

        return format;
    }

    public String getJsonString() {
        return format().toJSONString();
    }

    private String messageUrl;
    private String pcMessageUrl;
    private String head_bgcolor;
    private String head_text;
    private Body body;

    public Body createBody(){
        return new Body();
    }

    public JSONObject getBody() {
        JSONObject obj = new JSONObject();
        obj.put("title", body.getTitle());
        obj.put("form", body.getForm());
        obj.put("rich", body.getRich());
        obj.put("content", body.getContent());
        obj.put("image", body.getImage());
        obj.put("file_count", body.getFileCount());
        obj.put("author", body.getAuthor());
        return obj;
    }

    public String getPcMessageUrl() {
        return pcMessageUrl;
    }

    public void setPcMessageUrl(String pcMessageUrl) {
        this.pcMessageUrl = pcMessageUrl;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getHead_bgcolor() {
        return head_bgcolor;
    }

    public void setHead_bgcolor(String head_bgcolor) {
        this.head_bgcolor = head_bgcolor;
    }

    public String getHead_text() {
        return head_text;
    }

    public void setHead_text(String head_text) {
        this.head_text = head_text;
    }

    public class Body {
        private String title;
        private Map<String, String> form = new HashMap<String, String>();
        private JSONObject rich;
        private String content;
        private String image;
        private Integer fileCount;
        private String author;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public JSONArray getForm() {
            JSONArray array = new JSONArray();
            Set<String> keys = form.keySet();
            for (String key : keys) {
                JSONObject obj = new JSONObject();
                obj.put("key", key);
                obj.put("value", form.get(key));
                array.add(obj);
            }
            return array;
        }

        public void setForm(String key, String value) {
            form.put(key, value);
        }

        public JSONObject getRich() {
            return rich;
        }

        public void setRich(String num, String unit) {
            JSONObject obj = new JSONObject();
            obj.put("num", num);
            obj.put("unit", unit);
            this.rich = obj;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getFileCount() {
            return fileCount;
        }

        public void setFileCount(Integer fileCount) {
            this.fileCount = fileCount;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
