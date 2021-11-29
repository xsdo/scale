package com.qx.eis.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author qq
 * @version 1.0
 * @date 2021/7/30 14:14
 */
public class EisTable extends BaseEntity {

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

    /** 开始,2进行中,3已结束    */
    @Excel(name = "（开始,2进行中,3已结束)")
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTaskId() {
        return scaleId;
    }

    public void setTaskId(Long taskId) {
        this.scaleId = taskId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tableId", tableId)
                .append("scaleId", scaleId)
                .append("tableName", tableName)
                .append("grade", grade)
                .append("taskStatus", taskStatus)
                .append("delFlag", delFlag)
                .append("tableComment", tableComment)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("remark", remark)
                .toString();
    }
}
