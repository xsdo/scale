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
import com.qx.scale.domain.ScaleTaskScore;
import com.qx.scale.service.IScaleTaskScoreService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 量表得分保存Controller
 * 
 * @author patient
 * @date 2021-10-11
 */
@RestController
@RequestMapping("/scale/score")
public class ScaleTaskScoreController extends BaseController
{
    @Autowired
    private IScaleTaskScoreService scaleTaskScoreService;

    /**
     * 查询量表得分保存列表
     */
    @PreAuthorize("@ss.hasPermi('scale:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScaleTaskScore scaleTaskScore)
    {
        startPage();
        List<ScaleTaskScore> list = scaleTaskScoreService.selectScaleTaskScoreList(scaleTaskScore);
        return getDataTable(list);
    }

    /**
     * 导出量表得分保存列表
     */
    @PreAuthorize("@ss.hasPermi('scale:score:export')")
    @Log(title = "量表得分保存", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScaleTaskScore scaleTaskScore)
    {
        List<ScaleTaskScore> list = scaleTaskScoreService.selectScaleTaskScoreList(scaleTaskScore);
        ExcelUtil<ScaleTaskScore> util = new ExcelUtil<ScaleTaskScore>(ScaleTaskScore.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 获取量表得分保存详细信息
     */
    @PreAuthorize("@ss.hasPermi('scale:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(scaleTaskScoreService.selectScaleTaskScoreById(id));
    }

    /**
     * 新增量表得分保存
     */
    @PreAuthorize("@ss.hasPermi('scale:score:add')")
    @Log(title = "量表得分保存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScaleTaskScore scaleTaskScore)
    {
        return toAjax(scaleTaskScoreService.insertScaleTaskScore(scaleTaskScore));
    }

    /**
     * 修改量表得分保存
     */
    @PreAuthorize("@ss.hasPermi('scale:score:edit')")
    @Log(title = "量表得分保存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScaleTaskScore scaleTaskScore)
    {
        return toAjax(scaleTaskScoreService.updateScaleTaskScore(scaleTaskScore));
    }

    /**
     * 删除量表得分保存
     */
    @PreAuthorize("@ss.hasPermi('scale:score:remove')")
    @Log(title = "量表得分保存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scaleTaskScoreService.deleteScaleTaskScoreByIds(ids));
    }
}
