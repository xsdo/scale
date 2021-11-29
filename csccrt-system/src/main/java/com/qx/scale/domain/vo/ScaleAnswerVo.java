package com.qx.scale.domain.vo;

import com.qx.common.annotation.Excel;

/**
 * @author qq
 * @version 1.0
 * @date 2021/9/30 14:32
 */
public class ScaleAnswerVo {
    private static final long serialVersionUID = 1L;

    /** 选项 */
    private String options;

    /** 答案 */
    private Long answers;

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getAnswers() {
        return answers;
    }

    public void setAnswers(Long answers) {
        this.answers = answers;
    }
}
