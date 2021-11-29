package com.qx.web.controller.ipa;

import java.util.List;

import com.qx.ipa.service.IIpaTaskService;
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
import com.qx.ipa.domain.IpaPatientUser;
import com.qx.ipa.service.IIpaPatientUserService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 心身调节系统患者Controller
 * 
 * @author q
 * @date 2021-07-02
 */
@RestController
@RequestMapping("/ipa/patient")
public class IpaPatientUserController extends BaseController
{
    @Autowired
    private IIpaPatientUserService ipaPatientUserService;


    /**
     * 查询心身调节系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(IpaPatientUser ipaPatientUser)
    {
        startPage();
        List<IpaPatientUser> list = ipaPatientUserService.selectIpaPatientUserList(ipaPatientUser);
        return getDataTable(list);
    }

    /**
     * 导出心身调节系统患者列表
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:export')")
    @Log(title = "心身调节系统患者", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(IpaPatientUser ipaPatientUser)
    {
        List<IpaPatientUser> list = ipaPatientUserService.selectIpaPatientUserList(ipaPatientUser);
        ExcelUtil<IpaPatientUser> util = new ExcelUtil<IpaPatientUser>(IpaPatientUser.class);
        return util.exportExcel(list, "patient");
    }

    /**
     * 获取心身调节系统患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(ipaPatientUserService.selectIpaPatientUserById(patientId));
    }

    /**
     * 新增心身调节系统患者
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:add')")
    @Log(title = "心身调节系统患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IpaPatientUser ipaPatientUser)
    {
        return toAjax(ipaPatientUserService.insertIpaPatientUser(ipaPatientUser));
    }

    /**
     * 修改心身调节系统患者
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:edit')")
    @Log(title = "心身调节系统患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IpaPatientUser ipaPatientUser)
    {
        return toAjax(ipaPatientUserService.updateIpaPatientUser(ipaPatientUser));
    }

    /**
     * 删除心身调节系统患者
     */
    @PreAuthorize("@ss.hasPermi('ipa:patient:remove')")
    @Log(title = "心身调节系统患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(ipaPatientUserService.deleteIpaPatientUserByIds(patientIds));
    }

    /**
     * 删除患者未完成所有任务
     */

    @DeleteMapping("/delAllTask/{patientIds}")
    public AjaxResult delAllTask(@PathVariable Long[] patientIds)
    {
        int a=0;
        for (Long l:patientIds) {
            a+= ipaPatientUserService.delAllTask(l);
        }
        return toAjax(a);
    }
}
