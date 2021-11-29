package com.qx.web.controller.peri;

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
import com.qx.peri.domain.PeriPatientUser;
import com.qx.peri.service.IPeriPatientUserService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 围手术期系统患者Controller
 * 
 * @author Meng
 * @date 2021-07-13
 */
@RestController
@RequestMapping("/peri/patient")
public class PeriPatientUserController extends BaseController
{
    @Autowired
    private IPeriPatientUserService periPatientUserService;

    /**
     * 查询围手术期系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(PeriPatientUser periPatientUser)
    {
        startPage();
        List<PeriPatientUser> list = periPatientUserService.selectPeriPatientUserList(periPatientUser);
        return getDataTable(list);
    }

    /**
     * 导出围手术期系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:export')")
    @Log(title = "围手术期系统患者", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PeriPatientUser periPatientUser)
    {
        List<PeriPatientUser> list = periPatientUserService.selectPeriPatientUserList(periPatientUser);
        ExcelUtil<PeriPatientUser> util = new ExcelUtil<PeriPatientUser>(PeriPatientUser.class);
        return util.exportExcel(list, "peri");
    }

    /**
     * 获取围手术期系统患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(periPatientUserService.selectPeriPatientUserById(patientId));
    }

    /**
     * 新增围手术期系统患者
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:add')")
    @Log(title = "围手术期系统患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PeriPatientUser periPatientUser)
    {
        return toAjax(periPatientUserService.insertPeriPatientUser(periPatientUser));
    }

    /**
     * 修改围手术期系统患者
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:edit')")
    @Log(title = "围手术期系统患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PeriPatientUser periPatientUser)
    {
        return toAjax(periPatientUserService.updatePeriPatientUser(periPatientUser));
    }

    /**
     * 删除围手术期系统患者
     */
    @PreAuthorize("@ss.hasPermi('peri:patient:remove')")
    @Log(title = "围手术期系统患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(periPatientUserService.deletePeriPatientUserByIds(patientIds));
    }

    /**
     * 删除患者未完成所有任务
     */

    @DeleteMapping("/delAllTask/{patientIds}")
    public AjaxResult delAllTask(@PathVariable Long[] patientIds)
    {
        int a=0;
        for (Long l:patientIds) {
            a+= periPatientUserService.delAllTask(l);
        }
        return toAjax(a);
    }
}
