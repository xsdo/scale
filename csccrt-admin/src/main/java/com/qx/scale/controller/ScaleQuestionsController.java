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
import com.qx.scale.domain.ScaleQuestions;
import com.qx.scale.service.IScaleQuestionsService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 量表题目及分数Controller
 * 
 * @author patient
 * @date 2021-09-30
 */
@RestController
@RequestMapping("/scale/questions")
public class ScaleQuestionsController extends BaseController
{
    @Autowired
    private IScaleQuestionsService scaleQuestionsService;

    /**
     * 查询量表题目及分数列表
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScaleQuestions scaleQuestions)
    {
        startPage();
        List<ScaleQuestions> list = scaleQuestionsService.selectScaleQuestionsList(scaleQuestions);
        return getDataTable(list);
    }

    /**
     * 导出量表题目及分数列表
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:export')")
    @Log(title = "量表题目及分数", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScaleQuestions scaleQuestions)
    {
        List<ScaleQuestions> list = scaleQuestionsService.selectScaleQuestionsList(scaleQuestions);
        ExcelUtil<ScaleQuestions> util = new ExcelUtil<ScaleQuestions>(ScaleQuestions.class);
        return util.exportExcel(list, "questions");
    }

    /**
     * 获取量表题目及分数详细信息
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(scaleQuestionsService.selectScaleQuestionsById(id));
    }

    /**
     * 新增量表题目及分数
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:add')")
    @Log(title = "量表题目及分数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScaleQuestions scaleQuestions)
    {
        return toAjax(scaleQuestionsService.insertScaleQuestions(scaleQuestions));
    }

    /**
     * 修改量表题目及分数
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:edit')")
    @Log(title = "量表题目及分数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScaleQuestions scaleQuestions)
    {
        return toAjax(scaleQuestionsService.updateScaleQuestions(scaleQuestions));
    }

    /**
     * 删除量表题目及分数
     */
    @PreAuthorize("@ss.hasPermi('scale:questions:remove')")
    @Log(title = "量表题目及分数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scaleQuestionsService.deleteScaleQuestionsByIds(ids));
    }
}
