package com.neusoft.ehr.utils;

public class Message extends MessageInfo {

    //200  成功
    //0    没有查询到数据
    //500   catch到异常
    //
    private  int meaasgeId;
    private String messageName;

    public Message(int meaasgeId,String messageName) {
        this.meaasgeId = meaasgeId;
        this.messageName = messageName;
    }

    public Message() {
    }

    public int getMeaasgeId() {
        return meaasgeId;
    }

    public void setMeaasgeId(int meaasgeId) {
        this.meaasgeId = meaasgeId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }
}
