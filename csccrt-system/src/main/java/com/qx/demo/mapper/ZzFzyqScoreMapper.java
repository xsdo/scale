package com.qx.demo.mapper;

import com.qx.demo.entity.ZzFzyqScore;
import java.util.List;

/**
 * 复杂眼区识别范式Mapper接口
 * 
 * @author patient
 * @date 2021-02-02
 */
public interface ZzFzyqScoreMapper 
{
    /**
     * 查询复杂眼区识别范式
     * 
     * @param id 复杂眼区识别范式ID
     * @return 复杂眼区识别范式
     */
    public ZzFzyqScore selectZzFzyqScoreById(Long id);

    /**
     * 查询复杂眼区识别范式列表
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 复杂眼区识别范式集合
     */
    public List<ZzFzyqScore> selectZzFzyqScoreList(ZzFzyqScore zzFzyqScore);

    /**
     * 新增复杂眼区识别范式
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 结果
     */
    public int insertZzFzyqScore(ZzFzyqScore zzFzyqScore);

    /**
     * 修改复杂眼区识别范式
     * 
     * @param zzFzyqScore 复杂眼区识别范式
     * @return 结果
     */
    public int updateZzFzyqScore(ZzFzyqScore zzFzyqScore);

    /**
     * 删除复杂眼区识别范式
     * 
     * @param id 复杂眼区识别范式ID
     * @return 结果
     */
    public int deleteZzFzyqScoreById(Long id);

    /**
     * 批量删除复杂眼区识别范式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteZzFzyqScoreByIds(Long[] ids);

    /**
     * 通过任务查询量表
     *
     * @param taskId
     * @return 量表集合
     */
    public List<ZzFzyqScore> selectListByTaskId(Long taskId);
}
