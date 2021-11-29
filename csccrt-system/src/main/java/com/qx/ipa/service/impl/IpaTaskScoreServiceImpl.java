package com.qx.ipa.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.common.utils.DateUtils;
import com.qx.ipa.domain.IpaDomain;
import com.qx.ipa.domain.IpaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.ipa.mapper.IpaTaskScoreMapper;
import com.qx.ipa.domain.IpaTaskScore;
import com.qx.ipa.service.IIpaTaskScoreService;

/**
 * ipa任务得分保存Service业务层处理
 * 
 * @author Meng
 * @date 2021-07-09
 */
@Service
public class IpaTaskScoreServiceImpl implements IIpaTaskScoreService 
{
    @Autowired
    private IpaTaskScoreMapper ipaTaskScoreMapper;

    /**
     * 查询ipa任务得分保存
     * 
     * @param id ipa任务得分保存ID
     * @return ipa任务得分保存
     */
    @Override
    public IpaTaskScore selectIpaTaskScoreById(Long id)
    {
        return ipaTaskScoreMapper.selectIpaTaskScoreById(id);
    }

    /**
     * 查询ipa任务得分保存列表
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return ipa任务得分保存
     */
    @Override
    public List<IpaTaskScore> selectIpaTaskScoreList(IpaTaskScore ipaTaskScore)
    {
        return ipaTaskScoreMapper.selectIpaTaskScoreList(ipaTaskScore);
    }

    /**
     * 新增ipa任务得分保存
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return 结果
     */
    @Override
    public int insertIpaTaskScore(IpaTaskScore ipaTaskScore)
    {
        return ipaTaskScoreMapper.insertIpaTaskScore(ipaTaskScore);
    }

    @Override
    public int insertIpaTaskScore(IpaTask ipaTask, String scaleId, int score) {
        //当新冠时，不传回scaleId
        IpaTaskScore ipaTaskScore = new IpaTaskScore();
        ipaTaskScore.setPatientId(ipaTask.getPatientId());
        if(scaleId!="" && scaleId!=null){
            ipaTaskScore.setScaleId(Long.parseLong(scaleId));
        }
        ipaTaskScore.setScore(score);
        ipaTaskScore.setTaskId(ipaTask.getTaskId());
        ipaTaskScore.setWorkstation(ipaTask.getWorkstation());
        //对应的天数
        ipaTaskScore.setTypeIds(ipaTask.getTypeids());
        ipaTaskScore.setTestDate(DateUtils.getNowDate());
        if("0".equals(ipaTask.getTypeFlag())){
            return ipaTaskScoreMapper.insertIpaTaskScore(ipaTaskScore);
        //量表时第一次回答时，三张量表没有全答完就退出。再进入工作站回答时，已经回答过得量表，不执行插入，执行更新
        }else if ("1".equals(ipaTask.getTypeFlag())){
            IpaTaskScore ipaTaskScore1 = new IpaTaskScore();
            ipaTaskScore1.setTaskId(ipaTask.getTaskId());
            ipaTaskScore1.setScaleId(Long.parseLong(scaleId));
            //根据scaleId 和 taskid来判断 如果不为空 说明回答过一遍，执行更新
            List<IpaTaskScore> ipaTaskScores = ipaTaskScoreMapper.selectIpaTaskScoreList(ipaTaskScore1);
            if (ipaTaskScores.size()>0){
                //将已存在的ipaTaskScore的id set到将要执行更新的ipaTaskScore中
                ipaTaskScore.setId(ipaTaskScores.get(0).getId());
                return ipaTaskScoreMapper.updateIpaTaskScore(ipaTaskScore);
            }else {
                return ipaTaskScoreMapper.insertIpaTaskScore(ipaTaskScore);
            }

        }
        return 0;

        /*if("0".equals(ipaTask.getTypeFlag())){
            ipaTaskScore.setPatientId(ipaTask.getPatientId());
            ipaTaskScore.setScaleId(Long.parseLong(ipaTask.getScaleId()));
            ipaTaskScore.setScore((long)score);
            ipaTaskScore.setTaskId(ipaTask.getTaskId());
            ipaTaskScore.setWorkstation(ipaTask.getWorkstation());
            ipaTaskScore.setTypeIds(ipaTask.getTypeids());
            return ipaTaskScoreMapper.insertIpaTaskScore(ipaTaskScore);
        }
        //插入三张量表得分
        if("1".equals(ipaTask.getTypeFlag())){
            for (int i = 0; i <typeids.length ; i++) {
                ipaTaskScore.setPatientId(ipaTask.getPatientId());
                ipaTaskScore.setScaleId(Long.parseLong(ipaTask.getScaleId()));
                ipaTaskScore.setScore((long)score[i]);
                ipaTaskScore.setTaskId(ipaTask.getTaskId());
                ipaTaskScore.setWorkstation(ipaTask.getWorkstation());
                ipaTaskScore.setTypeIds(typeids[i]);
                return ipaTaskScoreMapper.insertIpaTaskScore(ipaTaskScore);
            }
        }*/
    }

    /**
     * 修改ipa任务得分保存
     * 
     * @param ipaTaskScore ipa任务得分保存
     * @return 结果
     */
    @Override
    public int updateIpaTaskScore(IpaTaskScore ipaTaskScore)
    {
        return ipaTaskScoreMapper.updateIpaTaskScore(ipaTaskScore);
    }

    /**
     * 批量删除ipa任务得分保存
     * 
     * @param ids 需要删除的ipa任务得分保存ID
     * @return 结果
     */
    @Override
    public int deleteIpaTaskScoreByIds(Long[] ids)
    {
        return ipaTaskScoreMapper.deleteIpaTaskScoreByIds(ids);
    }

    /**
     * 删除ipa任务得分保存信息
     * 
     * @param id ipa任务得分保存ID
     * @return 结果
     */
    @Override
    public int deleteIpaTaskScoreById(Long id)
    {
        return ipaTaskScoreMapper.deleteIpaTaskScoreById(id);
    }

    /****
     *
     * @param taskId
     * @return下载报告对象
     */
    @Override
    public IpaDomain selectListByTaskId(Long taskId) {

        IpaDomain ipaDomain = new IpaDomain();
        IpaTaskScore ipaTaskScore = new IpaTaskScore();
        int sum = 0;
        ipaTaskScore.setTaskId(taskId);
        List<IpaTaskScore> ipaTaskScores = ipaTaskScoreMapper.selectIpaTaskScoreList(ipaTaskScore);
        //ipa量表时，插入三张量表的分数
        if(ipaTaskScores.size()==3){
            for (IpaTaskScore taskScore : ipaTaskScores) {
                if("127".equals(taskScore.getScaleId().toString())){
                    ipaDomain.setSas(taskScore.getScore());
                }
                if("128".equals(taskScore.getScaleId().toString())){
                    ipaDomain.setSds(taskScore.getScore());
                }
                if("129".equals(taskScore.getScaleId().toString())){
                    ipaDomain.setAis(taskScore.getScore());
                }
                sum+=taskScore.getScore();
            }
            ipaDomain.setSum(sum);
        //新冠时，插入新冠分数
        }else if(ipaTaskScores.size()==1){
            ipaDomain.setCovid(ipaTaskScores.get(0).getScore());
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ipaDomain.setCreateTime(simpleDateFormat.format(ipaTaskScores.get(0).getTestDate()));
        return ipaDomain;
    }
}
