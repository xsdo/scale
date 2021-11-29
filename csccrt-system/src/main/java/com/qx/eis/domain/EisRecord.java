package com.qx.eis.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 14:34
 */
public class EisRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tableId;

    /** 表id */
    @Excel(name = "表id")
    private Long scaleId;

    /** 表名称 */
    @Excel(name = "表名称")
    private String tableName;

    /** 自评or他评 5自6他 */
    @Excel(name = "自评or他评")
    private String grade;

    @Excel(name = "用户电话")
    private String telNumber;

    /** 得分 */
    @Excel(name = "得分")
    private int score;

    /** 开始,2进行中,3已结束    */
    @Excel(name = "（1开始,2进行中,3已结束)")
    private String taskStatus;

    /** 是否删除(0存在，1删除) */
    @Excel(name = "是否删除(0存在，1删除)")
    private String delFlag;

    /** 表描述 */
    @Excel(name = "表描述")
    private String tableComment;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date createTime;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 得分 A*/
    @Excel(name = "得分A")
    private int scoreA;

    /** 得分B */
    @Excel(name = "得分B")
    private int scoreB;

    /** 得分C */
    @Excel(name = "得分")
    private int scoreC;

    /** 得分D */
    @Excel(name = "得分D")
    private int scoreD;

    /** 得分E */
    @Excel(name = "得分E")
    private int scoreE;

    /** 得分F */
    @Excel(name = "得分F")
    private int scoreF;

    /** 得分G */
    @Excel(name = "得分G")
    private int scoreG;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public int getScoreC() {
        return scoreC;
    }

    public void setScoreC(int scoreC) {
        this.scoreC = scoreC;
    }

    public int getScoreD() {
        return scoreD;
    }

    public void setScoreD(int scoreD) {
        this.scoreD = scoreD;
    }

    public int getScoreE() {
        return scoreE;
    }

    public void setScoreE(int scoreE) {
        this.scoreE = scoreE;
    }

    public int getScoreF() {
        return scoreF;
    }

    public void setScoreF(int scoreF) {
        this.scoreF = scoreF;
    }

    public int getScoreG() {
        return scoreG;
    }

    public void setScoreG(int scoreG) {
        this.scoreG = scoreG;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tableId", tableId)
                .append("scaleId", scaleId)
                .append("tableName", tableName)
                .append("grade", grade)
                .append("telNumber", telNumber)
                .append("score", score)
                .append("taskStatus", taskStatus)
                .append("delFlag", delFlag)
                .append("tableComment", tableComment)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("remark", remark)
                .append("scoreA", scoreA)
                .append("scoreB", scoreB)
                .append("scoreC", scoreC)
                .append("scoreD", scoreD)
                .append("scoreE", scoreE)
                .append("scoreF", scoreF)
                .append("scoreG", scoreG)
                .toString();
    }
}
