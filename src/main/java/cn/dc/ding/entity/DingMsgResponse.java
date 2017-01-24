package cn.dc.ding.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dongchen on 2017/1/24.
 */
public class DingMsgResponse {
    private String errcode;
    private String errmsg;
    private List<String> invaliduser;
    private List<Long> invalidparty;
    private List<String> forbiddenUserId;
    private String messageId;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<String> getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String invaliduser) {
        String[] split = invaliduser.split("\\|");
        this.invaliduser = Arrays.asList(split);
    }

    public List<Long> getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String invalidparty){
        String[] split = invalidparty.split("\\|");
        List<Long> longs = new ArrayList<Long>();
        for (String s : split) {
            try {
                longs.add(Long.valueOf(s));
            } catch (NumberFormatException e) {
                // TODO 记录日志
            }
        }
        this.invalidparty = longs;
    }

    public List<String> getForbiddenUserId() {
        return forbiddenUserId;
    }

    public void setForbiddenUserId(String forbiddenUserId) {
        String[] split = forbiddenUserId.split("\\|");
        this.forbiddenUserId = Arrays.asList(split);
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "DingMsgResponse{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", invaliduser=" + invaliduser +
                ", invalidparty=" + invalidparty +
                ", forbiddenUserId=" + forbiddenUserId +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
