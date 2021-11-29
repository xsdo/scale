package com.qx.eis.service;

import com.qx.eis.domain.EisScaleQuestions;
import com.qx.eis.domain.EisVo;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/3 9:48
 */
public interface IEisScaleQuestionsService {

    EisScaleQuestions selectEisScaleQuestionsById(Long id);

    EisScaleQuestions selectEisScaleQuestionsByTitle(String title);

    List<EisScaleQuestions> selectEisScaleQuestionsByScaleId(Long scaleId);

    int getQuestionsScore(List<EisVo> eisVoList);

    int getScoreA(List<EisVo> eisVoList);

    int getScoreB(List<EisVo> eisVoList);

    int getScoreC(List<EisVo> eisVoList);

    int getScoreD(List<EisVo> eisVoList);

    int getScoreE(List<EisVo> eisVoList);

    int getScoreF(List<EisVo> eisVoList);

    int getScoreG(List<EisVo> eisVoList);
}
