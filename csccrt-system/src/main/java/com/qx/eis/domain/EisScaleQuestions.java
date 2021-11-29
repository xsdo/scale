package com.qx.eis.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/3 9:21
 */
public class EisScaleQuestions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 量表id */
    @Excel(name = "量表id")
    private Long scaleId;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String content;

    /** 选项a */
    @Excel(name = "选项a")
    private String answerA;

    /** 选项b */
    @Excel(name = "选项b")
    private String answerB;

    /** 选项c */
    @Excel(name = "选项c")
    private String answerC;

    /** 选项d */
    @Excel(name = "选项d")
    private String answerD;

    /** 选项e */
    @Excel(name = "选项e")
    private String answerE;

    /** 选项f */
    @Excel(name = "选项f")
    private String answerF;

    /** 选项g */
    @Excel(name = "选项g")
    private String answerG;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswerE() {
        return answerE;
    }

    public void setAnswerE(String answerE) {
        this.answerE = answerE;
    }

    public String getAnswerF() {
        return answerF;
    }

    public void setAnswerF(String answerF) {
        this.answerF = answerF;
    }

    public String getAnswerG() {
        return answerG;
    }

    public void setAnswerG(String answerG) {
        this.answerG = answerG;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("scaleId", scaleId)
                .append("title", title)
                .append("content", content)
                .append("answerA", answerA)
                .append("answerB", answerB)
                .append("answerC", answerC)
                .append("answerD", answerD)
                .append("answerE", answerE)
                .append("answerF", answerF)
                .append("answerG", answerG)
                .toString();
    }
}
