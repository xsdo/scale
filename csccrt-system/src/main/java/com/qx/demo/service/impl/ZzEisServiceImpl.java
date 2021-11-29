package com.qx.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.EisDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzEisMapper;
import com.qx.demo.entity.ZzEis;
import com.qx.demo.service.IZzEisService;

/**
 * 情绪智力量(EIS)Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzEisServiceImpl implements IZzEisService 
{
    @Autowired
    private ZzEisMapper zzEisMapper;

    /**
     * 查询情绪智力量(EIS)
     * 
     * @param id 情绪智力量(EIS)ID
     * @return 情绪智力量(EIS)
     */
    @Override
    public ZzEis selectZzEisById(Long id)
    {
        return zzEisMapper.selectZzEisById(id);
    }

    /**
     * 查询情绪智力量(EIS)列表
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 情绪智力量(EIS)
     */
    @Override
    public List<ZzEis> selectZzEisList(ZzEis zzEis)
    {
        return zzEisMapper.selectZzEisList(zzEis);
    }

    /**
     * 新增情绪智力量(EIS)
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 结果
     */
    @Override
    public int insertZzEis(ZzEis zzEis)
    {
        return zzEisMapper.insertZzEis(zzEis);
    }

    /**
     * 修改情绪智力量(EIS)
     * 
     * @param zzEis 情绪智力量(EIS)
     * @return 结果
     */
    @Override
    public int updateZzEis(ZzEis zzEis)
    {
        return zzEisMapper.updateZzEis(zzEis);
    }

    /**
     * 批量删除情绪智力量(EIS)
     * 
     * @param ids 需要删除的情绪智力量(EIS)ID
     * @return 结果
     */
    @Override
    public int deleteZzEisByIds(Long[] ids)
    {
        return zzEisMapper.deleteZzEisByIds(ids);
    }

    /**
     * 删除情绪智力量(EIS)信息
     * 
     * @param id 情绪智力量(EIS)ID
     * @return 结果
     */
    @Override
    public int deleteZzEisById(Long id)
    {
        return zzEisMapper.deleteZzEisById(id);
    }

    @Override
    public EisDomain selectListByTaskId(Long taskId) {
        EisDomain eisDomain=new EisDomain();
       List<ZzEis> list=zzEisMapper.selectListByTaskId(taskId);
        for (ZzEis z:list) {
            if("2".equals(z.getTitle()) ||"3".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"14".equals(z.getTitle()) ||"16".equals(z.getTitle())||"22".equals(z.getTitle())||"23".equals(z.getTitle())||"28".equals(z.getTitle())||"30".equals(z.getTitle())||"31".equals(z.getTitle())){
                eisDomain.setQxzj(eisDomain.getQxzj()+Integer.parseInt(z.getPoint()));
            }else if("7".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"17".equals(z.getTitle()) ||"20".equals(z.getTitle())||"24".equals(z.getTitle())){
                eisDomain.setZwqx(eisDomain.getZwqx()+Integer.parseInt(z.getPoint()));
            }else if("4".equals(z.getTitle()) ||"6".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"19".equals(z.getTitle()) ||"21".equals(z.getTitle()) ||"26".equals(z.getTitle()) ||"27".equals(z.getTitle())||"29".equals(z.getTitle())||"32".equals(z.getTitle())){
                eisDomain.setTrqx(eisDomain.getTrqx()+Integer.parseInt(z.getPoint()));
            }else if("1".equals(z.getTitle()) ||"5".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"25".equals(z.getTitle()) ||"33".equals(z.getTitle()) ){
                eisDomain.setQxbd(eisDomain.getQxbd()+Integer.parseInt(z.getPoint()));
            }
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        eisDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        eisDomain.setSum(eisDomain.getQxbd()+eisDomain.getQxzj()+eisDomain.getTrqx()+eisDomain.getZwqx());
        return eisDomain;
    }
}
