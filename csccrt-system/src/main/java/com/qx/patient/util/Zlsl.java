package com.qx.patient.util;

import com.qx.common.utils.DateUtils;
import com.qx.patient.domain.StatisticsTable2;
import com.qx.patient.mapper.StatisticsTable2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
@Component
public class Zlsl {
    @Autowired
    private StatisticsTable2Mapper statisticsTable2Mapper;
    public  void zlsl(){
        SimpleDateFormat abc = new SimpleDateFormat("yyyy");
        String a=abc.format(DateUtils.getNowDate());
        StatisticsTable2 statisticsTable22=statisticsTable2Mapper.selectStatisticsTable2ByYear(Long.parseLong(a));
        if(statisticsTable22==null){
            StatisticsTable2 statisticsTable=new StatisticsTable2();
            statisticsTable.setYear(Long.parseLong(a));
            statisticsTable.setJanuaryCount(0L);
            statisticsTable.setFebruaryCount(0L);
            statisticsTable.setMarch(0L);
            statisticsTable.setApril(0L);
            statisticsTable.setMay(0L);
            statisticsTable.setJune(0L);
            statisticsTable.setJuly(0L);
            statisticsTable.setAugust(0L);
            statisticsTable.setSeptember(0L);
            statisticsTable.setOctober(0L);
            statisticsTable.setNovember(0L);
            statisticsTable.setDecember(0L);
            SimpleDateFormat aaa = new SimpleDateFormat("MM");
            String aa=aaa.format(DateUtils.getNowDate());
            if("01".equals(aa)){
                statisticsTable.setJanuaryCount(1L);
            }else if("02".equals(aa)){
                statisticsTable.setFebruaryCount(1L);
            }else if("03".equals(aa)){
                statisticsTable.setMarch(1L);
            }else if("04".equals(aa)){
                statisticsTable.setApril(1L);
            }else if("05".equals(aa)){
                statisticsTable.setMay(1L);
            }else if("06".equals(aa)){
                statisticsTable.setJune(1L);
            }else if("07".equals(aa)){
                statisticsTable.setJuly(1L);
            }else if("08".equals(aa)){
                statisticsTable.setAugust(1L);
            }else if("09".equals(aa)){
                statisticsTable.setSeptember(1L);
            }else if("10".equals(aa)){
                statisticsTable.setOctober(1L);
            }else if("11".equals(aa)){
                statisticsTable.setNovember(1L);
            }else{
                statisticsTable.setDecember(1L);
            }
            statisticsTable.setCreateTime(DateUtils.getNowDate());
            statisticsTable2Mapper.insertStatisticsTable2(statisticsTable);
        }else{
            StatisticsTable2 statisticsTable2=new StatisticsTable2();
            SimpleDateFormat aaa = new SimpleDateFormat("MM");
            String aa=aaa.format(DateUtils.getNowDate());
            statisticsTable2.setId(statisticsTable22.getId());
            if("01".equals(aa)){
                statisticsTable2.setJanuaryCount(statisticsTable22.getJanuaryCount()+1L);
            }else if("02".equals(aa)){
                statisticsTable2.setFebruaryCount(statisticsTable22.getFebruaryCount()+1L);
            }else if("03".equals(aa)){
                statisticsTable2.setMarch(statisticsTable22.getMarch()+1L);
            }else if("04".equals(aa)){
                statisticsTable2.setApril(statisticsTable22.getApril()+1L);
            }else if("05".equals(aa)){
                statisticsTable2.setMay(statisticsTable22.getMay()+1L);
            }else if("06".equals(aa)){
                statisticsTable2.setJune(statisticsTable22.getJune()+1L);
            }else if("07".equals(aa)){
                statisticsTable2.setJuly(statisticsTable22.getJuly()+1L);
            }else if("08".equals(aa)){
                statisticsTable2.setAugust(statisticsTable22.getAugust()+1L);
            }else if("09".equals(aa)){
                statisticsTable2.setSeptember(statisticsTable22.getSeptember()+1L);
            }else if("10".equals(aa)){
                statisticsTable2.setOctober(statisticsTable22.getOctober()+1L);
            }else if("11".equals(aa)){
                statisticsTable2.setNovember(statisticsTable22.getNovember()+1L);
            }else{
                statisticsTable2.setDecember(statisticsTable22.getDecember()+1L);
            }
            statisticsTable2Mapper.updateStatisticsTable2(statisticsTable2);
        }
    }
}
