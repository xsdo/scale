package com.qx.eis.domain;

import com.qx.common.annotation.Excel;
import com.qx.ipa.domain.IpaQuestion;
import com.qx.ipa.domain.IpaTask;

import java.util.List;
import java.util.Map;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/3 9:57
 */
public class EisVo {


    private Long scaleId;
    /**
     *题号
     */
    private String title;

    //答案
    private String answer;

    private  String telNumber;

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
