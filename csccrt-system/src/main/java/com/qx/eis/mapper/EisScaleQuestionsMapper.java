package com.qx.eis.mapper;

import com.qx.eis.domain.EisScaleQuestions;

import java.util.List;


public interface EisScaleQuestionsMapper
{


    public EisScaleQuestions selectEisScaleQuestionsById(Long id);

    public EisScaleQuestions selectEisScaleQuestionsByTitle(String title);

    public List<EisScaleQuestions> selectEisScaleQuestionsByScaleId(Long scaleId);


    public List<EisScaleQuestions> selectEisScaleQuestionsList(EisScaleQuestions eisScaleQuestions);



    public int insertEisScaleQuestions(EisScaleQuestions eisScaleQuestions);



    public int updateEisScaleQuestions(EisScaleQuestions eisScaleQuestions);



    public int deleteEisScaleQuestionsById(Long id);



    public int deleteIpaScaleQuestionsByIds(Long[] ids);
}
