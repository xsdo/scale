package com.qx.system.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qx.patient.domain.EvaluationType;
import com.qx.scale.domain.ScaleMenu;
import com.qx.system.domain.AppCategory;
import com.qx.system.domain.AppRegion;


/**
 * Treeselect树结构实体类
 * 
 * @author patient
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;
    
    /** 层级 */
    private String level;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(EvaluationType evaluationType){
        this.id=evaluationType.getTypeId();
        this.label=evaluationType.getTypeName();
        this.children=evaluationType.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    public TreeSelect(ScaleMenu scaleMenu){
        this.id=scaleMenu.getId();
        this.label=scaleMenu.getScaleName();
        this.children=scaleMenu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    public TreeSelect(AppCategory appCategory)
    {
        this.id = appCategory.getId();
        this.label = appCategory.getName();
        this.children = appCategory.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    public TreeSelect(AppRegion appRegion)
    {
        this.id = appRegion.getId();
        this.label = appRegion.getName();
        this.children = appRegion.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    
    public TreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu)
    {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
