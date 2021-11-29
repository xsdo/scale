package com.qx.scale.domain.vo;

import com.qx.scale.domain.ScaleTaskScore;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/10/11 15:25
 */
public class ScaleScoreVo {
    private static final long serialVersionUID = 1L;

    private String title;

    private Long score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
