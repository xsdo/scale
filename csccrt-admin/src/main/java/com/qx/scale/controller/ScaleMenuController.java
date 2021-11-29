package com.qx.scale.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qx.scale.domain.ScaleMenu;
import com.qx.scale.service.IScaleMenuService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 量表目录Controller
 * 
 * @author patient
 * @date 2021-09-27
 */
@RestController
@RequestMapping("/scale/menu")
public class ScaleMenuController extends BaseController
{
    @Autowired
    private IScaleMenuService scaleMenuService;

    /**
     * 查询量表目录列表
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScaleMenu scaleMenu)
    {
//        startPage();
        List<ScaleMenu> list = scaleMenuService.selectScaleMenuList(scaleMenu);
//        List<ScaleMenu>treeList = scaleMenuService.buildTypeTree(list);
        return getDataTable(scaleMenuService.buildTypeTree(list));
    }

    /**
     * 查询量表常用目录列表
     */
    @GetMapping("/listCommonly")
    public TableDataInfo listCommonly(ScaleMenu scaleMenu)
    {
//        startPage();
        scaleMenu.setCommonly(1L);
        List<ScaleMenu> list = scaleMenuService.selectScaleMenuList(scaleMenu);
//        List<ScaleMenu>treeList = scaleMenuService.buildTypeTree(list);
        return getDataTable(scaleMenuService.buildTypeTree(list));
    }

    @GetMapping("/treeselect")
    public TableDataInfo treeselect(ScaleMenu scaleMenu)
    {
//        startPage();
        List<ScaleMenu> list = scaleMenuService.selectScaleMenuList(scaleMenu);
        return getDataTable(scaleMenuService.buildTypeTreeSelect(list));
    }

    /*//模糊查询
    @GetMapping("/listByScaleName")
    public List<ScaleMenu>  listByScaleName(String scaleName)
    {
        List<ScaleMenu> list =scaleMenuService.listByScaleName(scaleName);
        return list;
    }*/
    /**
     * 导出量表目录列表
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:export')")
    @Log(title = "量表目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScaleMenu scaleMenu)
    {
        List<ScaleMenu> list = scaleMenuService.selectScaleMenuList(scaleMenu);
        ExcelUtil<ScaleMenu> util = new ExcelUtil<ScaleMenu>(ScaleMenu.class);
        return util.exportExcel(list, "menu");
    }

    /**
     * 获取量表目录详细信息
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(scaleMenuService.selectScaleMenuById(id));
    }

    /**
     * 新增量表目录
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:add')")
    @Log(title = "量表目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScaleMenu scaleMenu)
    {
        return toAjax(scaleMenuService.insertScaleMenu(scaleMenu));
    }

    /**
     * 修改量表目录
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:edit')")
    @Log(title = "量表目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScaleMenu scaleMenu)
    {
        return toAjax(scaleMenuService.updateScaleMenu(scaleMenu));
    }

    /**
     * 删除量表目录
     */
    @PreAuthorize("@ss.hasPermi('scale:menu:remove')")
    @Log(title = "量表目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long  id)
    {
        if (scaleMenuService.hasChildById(id)){
            return AjaxResult.error("存在子项目,不允许删除");

        }
        return toAjax(scaleMenuService.deleteScaleMenuById(id));
    }
}
