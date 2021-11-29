package com.qx.scale.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.scale.domain.ScaleMenu;
import com.qx.scale.domain.vo.ScaleAnswerVo;
import com.qx.scale.domain.vo.ScaleQuestionsVo;
import com.qx.scale.mapper.ScaleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScaleQuestionsMapper;
import com.qx.scale.domain.ScaleQuestions;
import com.qx.scale.service.IScaleQuestionsService;

/**
 * 量表题目及分数Service业务层处理
 * 
 * @author patient
 * @date 2021-09-30
 */
@Service
public class ScaleQuestionsServiceImpl implements IScaleQuestionsService 
{
    @Autowired
    private ScaleQuestionsMapper scaleQuestionsMapper;

    @Autowired
    private ScaleMenuMapper scaleMenuMapper;

    /**
     * 查询量表题目及分数
     * 
     * @param id 量表题目及分数ID
     * @return 量表题目及分数
     */
    @Override
    public ScaleQuestions selectScaleQuestionsById(Long id)
    {
        return scaleQuestionsMapper.selectScaleQuestionsById(id);
    }

    /**
     * 查询量表题目及分数列表
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 量表题目及分数
     */
    @Override
    public List<ScaleQuestions> selectScaleQuestionsList(ScaleQuestions scaleQuestions)
    {
        return scaleQuestionsMapper.selectScaleQuestionsList(scaleQuestions);
    }

    @Override
    public List<ScaleQuestionsVo> selectScaleQuestionsVoList(List<ScaleQuestions> scaleQuestionsList, Long scaleId){
        ScaleMenu scaleMenu =scaleMenuMapper.selectScaleMenuById(scaleId);
        List<ScaleQuestionsVo>scaleQuestionsVoList=new ArrayList<ScaleQuestionsVo>();
        if (scaleQuestionsList!=null&&scaleMenu!=null){
            for (ScaleQuestions questions: scaleQuestionsList){
                ScaleQuestionsVo scaleQuestionsVo =new ScaleQuestionsVo();
                scaleQuestionsVo.setScaleQuestions(questions);
                scaleQuestionsVo.setScaleId(scaleId);
                scaleQuestionsVo.setScaleName(scaleMenu.getScaleName());
                scaleQuestionsVo.setInstruction(scaleMenu.getInstruction());
                List<ScaleAnswerVo>scaleAnswerVos=new ArrayList<>();
                String[] options=questions.getOptions().split(",");
                String[] ids=questions.getAnswers().split(",");
                List<Long> longList = Arrays.asList(ids).stream().map(Long::parseLong).collect(Collectors.toList());
                for (int i=0;i<options.length&&i<longList.size();i++){
                    ScaleAnswerVo scaleAnswerVo=new ScaleAnswerVo();
                    scaleAnswerVo.setOptions(options[i]);
                    scaleAnswerVo.setAnswers(longList.get(i));
                    scaleAnswerVos.add(scaleAnswerVo);
                }
                scaleQuestionsVo.setScaleAnswerVoList(scaleAnswerVos);
                scaleQuestionsVoList.add(scaleQuestionsVo);
            }
        }

        return scaleQuestionsVoList;
}
    /**
     * 新增量表题目及分数
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 结果
     */
    @Override
    public int insertScaleQuestions(ScaleQuestions scaleQuestions)
    {
        return scaleQuestionsMapper.insertScaleQuestions(scaleQuestions);
    }

    /**
     * 修改量表题目及分数
     * 
     * @param scaleQuestions 量表题目及分数
     * @return 结果
     */
    @Override
    public int updateScaleQuestions(ScaleQuestions scaleQuestions)
    {
        return scaleQuestionsMapper.updateScaleQuestions(scaleQuestions);
    }

    /**
     * 批量删除量表题目及分数
     * 
     * @param ids 需要删除的量表题目及分数ID
     * @return 结果
     */
    @Override
    public int deleteScaleQuestionsByIds(Long[] ids)
    {
        return scaleQuestionsMapper.deleteScaleQuestionsByIds(ids);
    }

    /**
     * 删除量表题目及分数信息
     * 
     * @param id 量表题目及分数ID
     * @return 结果
     */
    @Override
    public int deleteScaleQuestionsById(Long id)
    {
        return scaleQuestionsMapper.deleteScaleQuestionsById(id);
    }
}
