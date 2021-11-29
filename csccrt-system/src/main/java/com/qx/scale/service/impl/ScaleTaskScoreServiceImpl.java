package com.qx.scale.service.impl;

import java.util.Date;
import java.util.List;

import com.qx.scale.domain.ScaleScore;
import com.qx.scale.domain.ScaleTask;
import com.qx.scale.domain.vo.ScaleScoreVo;
import com.qx.scale.service.IScaleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.scale.mapper.ScaleTaskScoreMapper;
import com.qx.scale.domain.ScaleTaskScore;
import com.qx.scale.service.IScaleTaskScoreService;

/**
 * 量表得分保存Service业务层处理
 * 
 * @author patient
 * @date 2021-10-11
 */
@Service
public class ScaleTaskScoreServiceImpl implements IScaleTaskScoreService 
{
    @Autowired
    private ScaleTaskScoreMapper scaleTaskScoreMapper;

    @Autowired
    private IScaleScoreService scaleScoreService;

    /**
     * 查询量表得分保存
     * 
     * @param id 量表得分保存ID
     * @return 量表得分保存
     */
    @Override
    public ScaleTaskScore selectScaleTaskScoreById(Long id)
    {
        return scaleTaskScoreMapper.selectScaleTaskScoreById(id);
    }

    @Override
    public int insertTaskScore(ScaleTask scaleTask,Long score,List<ScaleScoreVo>scaleScoreVos){
        if (scaleTask!=null){
            //保存量表得分表
            ScaleTaskScore scaleTaskScore=new ScaleTaskScore();
            scaleTaskScore.setPatientId(scaleTask.getPatientId());
            if (scaleTask.getScaleId()!=""&&scaleTask.getScaleId()!=null){
                scaleTaskScore.setScaleId(Long.parseLong(scaleTask.getScaleId()));
            }
            scaleTaskScore.setTaskId(scaleTask.getTaskId());
            scaleTaskScore.setWorkstation(scaleTask.getWorkstation());
            List<ScaleTaskScore>scaleTaskScoreList = scaleTaskScoreMapper.selectScaleTaskScoreList(scaleTaskScore);
            if (scaleTaskScoreList==null||scaleTaskScoreList.size()==0){
                scaleTaskScore.setScore(score);
                scaleTaskScore.setTestDate(new Date());
                scaleTaskScoreMapper.insertScaleTaskScore(scaleTaskScore);
            }else {
                scaleTaskScore.setId(scaleTaskScoreList.get(0).getId());
                scaleTaskScore.setScore(score);
                scaleTaskScore.setTestDate(new Date());
                scaleTaskScoreMapper.updateScaleTaskScore(scaleTaskScore);
            }
            if (scaleScoreVos!=null&&scaleScoreVos.size()>0){
                for (ScaleScoreVo scaleScoreVo:scaleScoreVos){
                    ScaleScore scaleScore=new ScaleScore();
                    scaleScore.setScaleId(Long.parseLong(scaleTask.getScaleId()));
                    scaleScore.setTaskId(scaleTask.getTaskId());
                    scaleScore.setTitle(scaleScoreVo.getTitle());
                    List<ScaleScore>scaleScoreList=scaleScoreService.selectScaleScoreList(scaleScore);
                    if (scaleScoreList==null||scaleScoreList.size()==0){
                        scaleScore.setScore(scaleScoreVo.getScore());
                        scaleScoreService.insertScaleScore(scaleScore);
                    }else {
                        scaleScore.setId(scaleScoreList.get(scaleScoreList.size()-1).getId());
                        scaleScore.setScore(scaleScoreVo.getScore());
                        scaleScoreService.updateScaleScore(scaleScore);
                    }

                }
            }
            return 1;
        }
        return 0;
    }
    /**
     * 查询量表得分保存列表
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 量表得分保存
     */
    @Override
    public List<ScaleTaskScore> selectScaleTaskScoreList(ScaleTaskScore scaleTaskScore)
    {
        return scaleTaskScoreMapper.selectScaleTaskScoreList(scaleTaskScore);
    }

    /**
     * 新增量表得分保存
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 结果
     */
    @Override
    public int insertScaleTaskScore(ScaleTaskScore scaleTaskScore)
    {
        return scaleTaskScoreMapper.insertScaleTaskScore(scaleTaskScore);
    }

    /**
     * 修改量表得分保存
     * 
     * @param scaleTaskScore 量表得分保存
     * @return 结果
     */
    @Override
    public int updateScaleTaskScore(ScaleTaskScore scaleTaskScore)
    {
        return scaleTaskScoreMapper.updateScaleTaskScore(scaleTaskScore);
    }

    /**
     * 批量删除量表得分保存
     * 
     * @param ids 需要删除的量表得分保存ID
     * @return 结果
     */
    @Override
    public int deleteScaleTaskScoreByIds(Long[] ids)
    {
        return scaleTaskScoreMapper.deleteScaleTaskScoreByIds(ids);
    }

    /**
     * 删除量表得分保存信息
     * 
     * @param id 量表得分保存ID
     * @return 结果
     */
    @Override
    public int deleteScaleTaskScoreById(Long id)
    {
        return scaleTaskScoreMapper.deleteScaleTaskScoreById(id);
    }
}
