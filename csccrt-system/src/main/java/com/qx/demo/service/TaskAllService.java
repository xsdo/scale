package com.qx.demo.service;


import com.qx.demo.entity.vo.*;

import java.util.List;

public interface TaskAllService {
    /**
     * 获取所有任务
     * @return
     */
    public TaskAVo getTaskA(String workStation);
    public TaskBVo getTaskB(String workStation);
    public TaskCVo getTaskC(String workStation);
    public TaskDVo getTaskD(String workStation);

    public int addResult(List<ResultVo> resultVoList);

    public int updateBasisTask(String TaskId,String scaleId);
    public int updateIntermediateTask(String TaskId,String scaleId);
    public int updateAdvancedTask(String TaskId,String scaleId);

}
