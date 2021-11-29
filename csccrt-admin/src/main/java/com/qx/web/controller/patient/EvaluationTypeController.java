package com.qx.web.controller.patient;

import java.util.ArrayList;
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
import com.qx.patient.domain.EvaluationType;
import com.qx.patient.service.IEvaluationTypeService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 测评类型Controller
 * 
 * @author patient
 * @date 2020-07-09
 */
@RestController
@RequestMapping("/patient/type")
public class EvaluationTypeController extends BaseController
{
    @Autowired
    private IEvaluationTypeService evaluationTypeService;

    /**
     * 查询测评类型列表
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:list')")
    @GetMapping("/list")
    public AjaxResult list(EvaluationType evaluationType)
    {
        List<EvaluationType> list4=new ArrayList<>();
        startPage();
        EvaluationType evaluationType1=evaluationTypeService.selectEvaluationTypeById(evaluationType.getTypeId());
        int a= Math.toIntExact(evaluationType.getTypeId());
        evaluationType.setTypeId(null);
        List<EvaluationType> list = evaluationTypeService.selectEvaluationTypeList(new EvaluationType());
        List<EvaluationType> list1=evaluationTypeService.getChildPerms(list,a);
        for (EvaluationType e:list1) {
            List<EvaluationType> list3=evaluationTypeService.getChildPerms(list,e.getTypeId().intValue());
            list4.addAll(list3);
        }
        list1.addAll(list4);
        list1.add(evaluationType1);
        List<EvaluationType> list2=evaluationTypeService.buildTypeTree(list1);
        return  AjaxResult.success(list2);
    }

    /**
     *
     * 导出测评类型列表
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:export')")
    @Log(title = "测评类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EvaluationType evaluationType)
    {
        List<EvaluationType> list = evaluationTypeService.selectEvaluationTypeList(evaluationType);
        ExcelUtil<EvaluationType> util = new ExcelUtil<EvaluationType>(EvaluationType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取测评类型详细信息
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(evaluationTypeService.selectEvaluationTypeById(typeId));
    }

    /**
     * 新增测评类型
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:add')")
    @Log(title = "测评类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EvaluationType evaluationType)
    {
        return toAjax(evaluationTypeService.insertEvaluationType(evaluationType));
    }

    /**
     * 修改测评类型
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:edit')")
    @Log(title = "测评类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EvaluationType evaluationType)
    {
        return toAjax(evaluationTypeService.updateEvaluationType(evaluationType));
    }

    /**
     * 删除测评类型
     */
    //@PreAuthorize("@ss.hasPermi('patient:type:remove')")
    @Log(title = "测评类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(evaluationTypeService.deleteEvaluationTypeByIds(typeIds));
    }
}
