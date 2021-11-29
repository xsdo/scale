package com.qx.web.controller.patient;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qx.framework.util.SecurityUtils;
import com.qx.framework.security.service.TokenService;
import com.qx.patient.domain.PatientCategories;
import com.qx.patient.domain.SysHometown;
import com.qx.patient.domain.SysNation;
import com.qx.patient.service.IPatientCategoriesService;
import com.qx.patient.service.ISysHometownService;
import com.qx.patient.service.ISysNationService;
import com.qx.system.domain.SysUser;
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
import com.qx.patient.domain.PatientUser;
import com.qx.patient.service.IPatientUserService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 患者Controller
 * 
 * @author patient
 * @date 2020-07-08
 */
@RestController
@RequestMapping("/patient/user")
public class PatientUserController extends BaseController
{
    @Autowired
    private IPatientUserService patientUserService;

    @Autowired
    private ISysNationService sysNationService;

    @Autowired
    private ISysHometownService sysHometownService;

    @Autowired
    private IPatientCategoriesService patientCategoriesService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询患者列表
     */
   @PreAuthorize("@ss.hasPermi('patient:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(PatientUser patientUser)
    {
        startPage();
        if(!"admin".equals(SecurityUtils.getUsername())){
            patientUser.setCreateBy(SecurityUtils.getUsername());
        }
        List<PatientUser> list = patientUserService.selectPatientUserList(patientUser);
        return getDataTable(list);
    }

    /**
     * 导出患者列表
     */
    @PreAuthorize("@ss.hasPermi('patient:user:export')")
    @Log(title = "患者", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PatientUser patientUser)
    {
        List<PatientUser> list = patientUserService.selectPatientUserList(patientUser);
        ExcelUtil<PatientUser> util = new ExcelUtil<PatientUser>(PatientUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:user:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(patientUserService.selectPatientUserById(patientId));
    }

    /**
     * 新增患者
     */
    @PreAuthorize("@ss.hasPermi('patient:user:add')")
    @Log(title = "患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientUser patientUser)
    {
        patientUser.setCreateBy(SecurityUtils.getUsername());
        patientUser.setCreateTime(new Date());
        return toAjax(patientUserService.insertPatientUser(patientUser));
    }

    /**
     * 修改患者
     */
    @PreAuthorize("@ss.hasPermi('patient:user:edit')")
    @Log(title = "患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientUser patientUser)
    {
        patientUser.setUpdateBy(SecurityUtils.getUsername());
        patientUser.setUpdateTime(new Date());
        return toAjax(patientUserService.updatePatientUser(patientUser));
    }

    /**
     * 删除患者
     */
    @PreAuthorize("@ss.hasPermi('patient:user:remove')")
    @Log(title = "患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(patientUserService.deletePatientUserByIds(patientIds));
    }

    /**
     * 删除患者任务
     */

    @Log(title = "患者", businessType = BusinessType.DELETE)
    @DeleteMapping("/delUserTask/{patientIds}")
    public AjaxResult delUserTask(@PathVariable Long[] patientIds)
    {
        int a=0;
        for (Long l:patientIds) {
            a+= patientUserService.deleteUserTask(l);
        }
        return toAjax(a);
    }

    /**
     * 获取所有民族
     * @param sysNation
     * @return
     */
    @GetMapping("/nationList")
    public AjaxResult list(SysNation sysNation)
    {
        startPage();
        List<Map<String,String>> map = sysNationService.selectSysNationList(sysNation);
        return AjaxResult.success(map);
    }
    /**
     * 获取所有省
     * @param sysHometown
     * @return
     */
    @GetMapping("/sysHometownList")
    public AjaxResult list(SysHometown sysHometown)
    {
        startPage();
        List<Map<String,String>> map  = sysHometownService.selectSysHometownList(sysHometown);
        return AjaxResult.success(map);
    }
    /**
     * 获取所有分类目录
     * @param patientCategories
     * @return
     */
    @GetMapping("/patientCategoriesList")
    public AjaxResult list(PatientCategories patientCategories)
    {
        startPage();
        List<Map<String,String>> map = patientCategoriesService.selectPatientCategoriesList(patientCategories);
        return AjaxResult.success(map);
    }
    /**
     * 逻辑删除患者
     */

    @GetMapping("/updateDelFlag/{patientId}")
    public AjaxResult updateDelFlag(@PathVariable Long patientId)
    {
        PatientUser patientUser=new PatientUser();
        patientUser.setPatientId(patientId);
        patientUser.setDelFlag("1");
        return toAjax(patientUserService.updateDelFlag(patientUser));
    }
}
