package com.qx.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.qx.demo.entity.IricDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.demo.mapper.ZzIriCMapper;
import com.qx.demo.entity.ZzIriC;
import com.qx.demo.service.IZzIriCService;

/**
 * 人际反应指针Service业务层处理
 * 
 * @author patient
 * @date 2021-02-02
 */
@Service
public class ZzIriCServiceImpl implements IZzIriCService 
{
    @Autowired
    private ZzIriCMapper zzIriCMapper;

    /**
     * 查询人际反应指针
     * 
     * @param id 人际反应指针ID
     * @return 人际反应指针
     */
    @Override
    public ZzIriC selectZzIriCById(Long id)
    {
        return zzIriCMapper.selectZzIriCById(id);
    }

    /**
     * 查询人际反应指针列表
     * 
     * @param zzIriC 人际反应指针
     * @return 人际反应指针
     */
    @Override
    public List<ZzIriC> selectZzIriCList(ZzIriC zzIriC)
    {
        return zzIriCMapper.selectZzIriCList(zzIriC);
    }

    /**
     * 新增人际反应指针
     * 
     * @param zzIriC 人际反应指针
     * @return 结果
     */
    @Override
    public int insertZzIriC(ZzIriC zzIriC)
    {
        return zzIriCMapper.insertZzIriC(zzIriC);
    }

    /**
     * 修改人际反应指针
     * 
     * @param zzIriC 人际反应指针
     * @return 结果
     */
    @Override
    public int updateZzIriC(ZzIriC zzIriC)
    {
        return zzIriCMapper.updateZzIriC(zzIriC);
    }

    /**
     * 批量删除人际反应指针
     * 
     * @param ids 需要删除的人际反应指针ID
     * @return 结果
     */
    @Override
    public int deleteZzIriCByIds(Long[] ids)
    {
        return zzIriCMapper.deleteZzIriCByIds(ids);
    }

    /**
     * 删除人际反应指针信息
     * 
     * @param id 人际反应指针ID
     * @return 结果
     */
    @Override
    public int deleteZzIriCById(Long id)
    {
        return zzIriCMapper.deleteZzIriCById(id);
    }

    @Override
    public IricDomain selectListByTaskId(Long taskId) {
        IricDomain iricDomain=new IricDomain();
        List<ZzIriC> list=zzIriCMapper.selectListByTaskId(taskId);
        for (ZzIriC z:list) {
            if("6".equals(z.getTitle()) ||"9".equals(z.getTitle()) ||"15".equals(z.getTitle()) ||"19".equals(z.getTitle())||"22".equals(z.getTitle()) ){
                iricDomain.setGdcz(iricDomain.getGdcz()+Integer.parseInt(z.getPoint()));
            }else if("4".equals(z.getTitle()) ||"8".equals(z.getTitle()) ||"13".equals(z.getTitle()) ||"18".equals(z.getTitle()) ||"21".equals(z.getTitle())){
                iricDomain.setGrtk(iricDomain.getGrtk()+Integer.parseInt(z.getPoint()));
            }else if("3".equals(z.getTitle()) ||"5".equals(z.getTitle()) ||"10".equals(z.getTitle()) ||"12".equals(z.getTitle()) ||"17".equals(z.getTitle()) ||"20".equals(z.getTitle()) ){
                iricDomain.setXx(iricDomain.getXx()+Integer.parseInt(z.getPoint()));
            }else if("1".equals(z.getTitle()) ||"2".equals(z.getTitle()) ||"7".equals(z.getTitle()) ||"11".equals(z.getTitle()) ||"14".equals(z.getTitle())||"16".equals(z.getTitle()) ){
                iricDomain.setGqgx(iricDomain.getGqgx()+Integer.parseInt(z.getPoint()));
            }
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        iricDomain.setCreateTime(simpleDateFormat.format(list.get(0).getTestDate()));
        iricDomain.setSum(iricDomain.getGdcz()+iricDomain.getGqgx()+iricDomain.getGrtk()+iricDomain.getXx());
        return iricDomain;
    }
}
