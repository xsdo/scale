package com.qx.scale.domain.vo;

import com.qx.scale.domain.ScaleQuestions;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/9/30 14:31
 */
public class ScaleQuestionsVo {
    private static final long serialVersionUID = 1L;

    private ScaleQuestions scaleQuestions;

    private List<ScaleAnswerVo> scaleAnswerVoList;

    private Long scaleId;

    private String scaleName;

    private String instruction;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public ScaleQuestions getScaleQuestions() {
        return scaleQuestions;
    }

    public void setScaleQuestions(ScaleQuestions scaleQuestions) {
        this.scaleQuestions = scaleQuestions;
    }

    public List<ScaleAnswerVo> getScaleAnswerVoList() {
        return scaleAnswerVoList;
    }

    public void setScaleAnswerVoList(List<ScaleAnswerVo> scaleAnswerVoList) {
        this.scaleAnswerVoList = scaleAnswerVoList;
    }
}
