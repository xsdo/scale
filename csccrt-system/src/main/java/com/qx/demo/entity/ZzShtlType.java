package com.qx.demo.entity;

import com.qx.demo.entity.vo.ZzMettTypeVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 社会情境排列对象 zz_shtl_type
 * 
 * @author patient
 * @date 2021-02-02
 */
public class ZzShtlType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 他评或自评 */
    @Excel(name = "他评或自评")
    private String grade;

    /** 题号 */
    @Excel(name = "题号")
    private String title;

    /** 路径 */
    @Excel(name = "路径")
    private String aUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String aUrlb;

    /** 路径 */
    @Excel(name = "路径")
    private String bUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String bUrlb;

    /** 路径 */
    @Excel(name = "路径")
    private String cUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String cUrlb;

    /** 路径 */
    @Excel(name = "路径")
    private String dUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String dUrlb;

    /** 路径 */
    @Excel(name = "路径")
    private String eUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String eUrlb;

    /** 路径 */
    @Excel(name = "路径")
    private String fUrla;

    /** 路径 */
    @Excel(name = "路径")
    private String fUrlb;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String correct;

    private Set<ZzMettTypeVo> set;

    private List<Object> list;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }


    public Set<ZzMettTypeVo> getSet() {
        return set;
    }

    public void setSet(Set<ZzMettTypeVo> set) {
        this.set = set;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setaUrla(String aUrla) 
    {
        this.aUrla = aUrla;
    }

    public String getaUrla() 
    {
        return aUrla;
    }
    public void setaUrlb(String aUrlb) 
    {
        this.aUrlb = aUrlb;
    }

    public String getaUrlb() 
    {
        return aUrlb;
    }
    public void setbUrla(String bUrla) 
    {
        this.bUrla = bUrla;
    }

    public String getbUrla() 
    {
        return bUrla;
    }
    public void setbUrlb(String bUrlb) 
    {
        this.bUrlb = bUrlb;
    }

    public String getbUrlb() 
    {
        return bUrlb;
    }
    public void setcUrla(String cUrla) 
    {
        this.cUrla = cUrla;
    }

    public String getcUrla() 
    {
        return cUrla;
    }
    public void setcUrlb(String cUrlb) 
    {
        this.cUrlb = cUrlb;
    }

    public String getcUrlb() 
    {
        return cUrlb;
    }
    public void setdUrla(String dUrla) 
    {
        this.dUrla = dUrla;
    }

    public String getdUrla() 
    {
        return dUrla;
    }
    public void setdUrlb(String dUrlb) 
    {
        this.dUrlb = dUrlb;
    }

    public String getdUrlb() 
    {
        return dUrlb;
    }
    public void seteUrla(String eUrla) 
    {
        this.eUrla = eUrla;
    }

    public String geteUrla() 
    {
        return eUrla;
    }
    public void seteUrlb(String eUrlb) 
    {
        this.eUrlb = eUrlb;
    }

    public String geteUrlb() 
    {
        return eUrlb;
    }
    public void setfUrla(String fUrla) 
    {
        this.fUrla = fUrla;
    }

    public String getfUrla() 
    {
        return fUrla;
    }
    public void setfUrlb(String fUrlb) 
    {
        this.fUrlb = fUrlb;
    }

    public String getfUrlb() 
    {
        return fUrlb;
    }
    public void setCorrect(String correct) 
    {
        this.correct = correct;
    }

    public String getCorrect() 
    {
        return correct;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("grade", getGrade())
            .append("title", getTitle())
            .append("aUrla", getaUrla())
            .append("aUrlb", getaUrlb())
            .append("bUrla", getbUrla())
            .append("bUrlb", getbUrlb())
            .append("cUrla", getcUrla())
            .append("cUrlb", getcUrlb())
            .append("dUrla", getdUrla())
            .append("dUrlb", getdUrlb())
            .append("eUrla", geteUrla())
            .append("eUrlb", geteUrlb())
            .append("fUrla", getfUrla())
            .append("fUrlb", getfUrlb())
            .append("correct", getCorrect())
            .toString();
    }
}
