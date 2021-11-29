package com.qx.eis.service.impl;


import com.qx.common.core.domain.AjaxResult;
import com.qx.eis.domain.EisRecord;
import com.qx.eis.domain.EisTable;
import com.qx.eis.domain.EisUser;
import com.qx.eis.domain.EisVo;
import com.qx.eis.mapper.EisRecordMapper;
import com.qx.eis.service.IEisRecordService;
import com.qx.eis.service.IEisScaleQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author qq
 * @version 1.0
 * @date 2021/8/2 14:56
 */
@Service
public class EisRecordServiceImpl implements IEisRecordService {

    @Autowired
    private EisRecordMapper eisRecordMapper;

    @Autowired
    IEisScaleQuestionsService iEisScaleQuestionsService;


    @Override
    public List<EisRecord>selectEisRecords(Long delFlag){
        return eisRecordMapper.selectEisRecords( delFlag);
    };

    @Override
    public List<EisRecord>selectEisRecordByStatus(Long taskStatus){
        return eisRecordMapper.selectEisRecordByStatus(taskStatus);
    }
    @Override
    public List<EisRecord>selectEisRecordByTel(String telNumber){
        return eisRecordMapper.selectEisRecordByTel(telNumber);
    }

    @Override
    public EisRecord selectEisRecordById(Long tableId){
        return eisRecordMapper.selectEisRecordById(tableId);
    }
    @Override
    public EisRecord selectEisRecordByScaleId(Long scaleId){
        return eisRecordMapper.selectEisRecordByScaleId(scaleId);
    }

    @Override
    public int insertEisRecord(EisRecord eisRecord){
        return eisRecordMapper.insertEisRecord(eisRecord);
    }

    @Override
    public int updateEisRecord(EisRecord eisRecord){
        return eisRecordMapper.updateEisRecord(eisRecord);
    }

    @Override
    public int inUpEisRecord(EisTable eisTable , String telNumber, Long scaleId ){
        int i =0;
        EisRecord eisRecord=new EisRecord();
        eisRecord.setScaleId(scaleId);
        eisRecord.setTableName(eisTable.getTableName());
        eisRecord.setGrade(eisTable.getGrade());
        eisRecord.setTelNumber(telNumber);
        eisRecord.setTaskStatus("2");
        eisRecord.setDelFlag("0");
        eisRecord.setTableComment(eisTable.getTableComment());
        eisRecord.setCreateTime(new Date());
        eisRecord.setUpdateTime(new Date());
        List<EisRecord> recordList =this.selectEisRecordByTel(telNumber);
        if (recordList==null||recordList.size()==0){

             i =this.insertEisRecord(eisRecord);

        }else {
            int sum=0;
            for (int a=0;a<recordList.size();a++){
                if (recordList.get(a).getScaleId()==scaleId){
                    //更新记录
                    EisRecord record=recordList.get(a);
                    record.setUpdateTime(new Date());
                     i =this.updateEisRecord(record);

                    sum++;
                }
            }
            if (sum==0){
                //添加答题记录
                 i =this.insertEisRecord(eisRecord);
            }

        }
        return i;
    }


    @Override
    public AjaxResult reviseRecord(List<EisVo> eisVoList, String telNumber, AjaxResult ajax){
        int score =0;
        int scoreA =0;
        int scoreB =0;
        int scoreC =0;
        int scoreD =0;
        int scoreE =0;
        int scoreF =0;
        int scoreG =0;
        //获取分数

        score =iEisScaleQuestionsService.getQuestionsScore(eisVoList);
        scoreA = iEisScaleQuestionsService.getScoreA(eisVoList);
        scoreB = iEisScaleQuestionsService.getScoreB(eisVoList);
        scoreC = iEisScaleQuestionsService.getScoreC(eisVoList);
        scoreD = iEisScaleQuestionsService.getScoreD(eisVoList);
        scoreE = iEisScaleQuestionsService.getScoreE(eisVoList);
        scoreF = iEisScaleQuestionsService.getScoreF(eisVoList);
        scoreG = iEisScaleQuestionsService.getScoreG(eisVoList);

        ajax.put("score",score);
        //插入分数，更改状态

        List<EisRecord> recordList =this.selectEisRecordByTel(telNumber);
        if (recordList!=null&&recordList.size()!=0){
            Long scaleId=eisVoList.get(0).getScaleId();
            for (int a=0;a<recordList.size();a++){
                if (recordList.get(a).getScaleId()==scaleId){
                    //更新记录
                    EisRecord record=recordList.get(a);
                    record.setScore(score);

                    //将表52.53转化为粗分标准分
                    if (scaleId==52||scaleId==53){
                        scoreA=score;
                    }

                    if (scaleId==52||scaleId==53){
                        scoreB=new Double(score*1.25).intValue();
                    }

                    record.setScoreA(scoreA);
                    record.setScoreB(scoreB);
                    record.setScoreC(scoreC);
                    record.setScoreD(scoreD);
                    record.setScoreE(scoreE);
                    record.setScoreF(scoreF);
                    record.setScoreG(scoreG);
                    record.setTaskStatus("3");
                    record.setUpdateTime(new Date());
                    int i =this.updateEisRecord(record);
                    ajax.put("code","记录表更新成功");
                    ajax.put("record",record);
                }
            }
        }
        return ajax;
    }

}
