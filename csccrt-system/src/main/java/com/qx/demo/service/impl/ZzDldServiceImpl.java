package com.qx.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.DldDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzDldMapper;
import com.qx.demo.entity.ZzDld;
import com.qx.demo.service.IZzDldService;

/**
 * 多伦多述情障碍量Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzDldServiceImpl implements IZzDldService 
{
    @Autowired
    private ZzDldMapper zzDldMapper;

    /**
     * 查询多伦多述情障碍量
     * 
     * @param id 多伦多述情障碍量ID
     * @return 多伦多述情障碍量
     */
    @Override
    public ZzDld selectZzDldById(Long id)
    {
        return zzDldMapper.selectZzDldById(id);
    }

    /**
     * 查询多伦多述情障碍量列表
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 多伦多述情障碍量
     */
    @Override
    public List<ZzDld> selectZzDldList(ZzDld zzDld)
    {
        return zzDldMapper.selectZzDldList(zzDld);
    }

    /**
     * 新增多伦多述情障碍量
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 结果
     */
    @Override
    public int insertZzDld(ZzDld zzDld)
    {
        return zzDldMapper.insertZzDld(zzDld);
    }

    /**
     * 修改多伦多述情障碍量
     * 
     * @param zzDld 多伦多述情障碍量
     * @return 结果
     */
    @Override
    public int updateZzDld(ZzDld zzDld)
    {
        return zzDldMapper.updateZzDld(zzDld);
    }

    /**
     * 批量删除多伦多述情障碍量
     * 
     * @param ids 需要删除的多伦多述情障碍量ID
     * @return 结果
     */
    @Override
    public int deleteZzDldByIds(Long[] ids)
    {
        return zzDldMapper.deleteZzDldByIds(ids);
    }

    /**
     * 删除多伦多述情障碍量信息
     * 
     * @param id 多伦多述情障碍量ID
     * @return 结果
     */
    @Override
    public int deleteZzDldById(Long id)
    {
        return zzDldMapper.deleteZzDldById(id);
    }

    @Override
    public DldDomain selectListByTaskId(Long taskId) {
        DldDomain dldDomain=new DldDomain();
        List<ZzDld> list=zzDldMapper.selectListByTaskId(taskId);
        for (ZzDld z:list) {
           if("1".equals(z.getTitle()) ||"3".equals(z.getTitle()) ||"6".equals(z.getTitle()) ||"7".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"14".equals(z.getTitle())){
               dldDomain.setQgbb(dldDomain.getQgbb()+Integer.parseInt(z.getPoint()));
           }else if("2".equals(z.getTitle()) ||"4".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"17".equals(z.getTitle())){
               dldDomain.setQgms(dldDomain.getQgms()+Integer.parseInt(z.getPoint()));
           }else if("5".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"16".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"19".equals(z.getTitle()) ||"20".equals(z.getTitle())){
               dldDomain.setWxx(dldDomain.getWxx()+Integer.parseInt(z.getPoint()));
           }
        }
        dldDomain.setSum(dldDomain.getQgbb()+dldDomain.getQgms()+dldDomain.getWxx());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        dldDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        return dldDomain;
    }
}
