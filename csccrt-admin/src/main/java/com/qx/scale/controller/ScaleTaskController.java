package com.qx.scale.controller;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;
import com.qx.framework.util.SecurityUtils;
import com.qx.patient.util.ExportExcelUtil;
import com.qx.scale.domain.*;
import com.qx.scale.domain.vo.ScaleScoreVo;
import com.qx.scale.service.*;
import com.qx.web.controller.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author qq
 * @version 1.0
 * @date 2021/9/30 11:45
 */
@RestController
@RequestMapping("/scale/task")
public class ScaleTaskController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);


    @Autowired
    private IScaleQuestionsService scaleQuestionsService;

    @Autowired
    private IScaleTaskService scaleTaskService;

    @Autowired
    private IScaleTaskScoreService scaleTaskScoreService;

    @Autowired
    private IScaleMenuService scaleMenuService;

    @Autowired
    private IScalePatientService scalePatientService;

    @Autowired
    private IScaleScoreService scaleScoreService;


    /**
     *患者端首页，查询任务，
     */
    @GetMapping("/getTask")
    public AjaxResult getTask(String workstation){
//        AjaxResult aj =  AjaxResult.success();
        List<ScaleTask> task = scaleTaskService.getTask(workstation);
//        aj.put("task",task);
//        List<ScaleMenu> list = scaleMenuService.scaleMenuListByTask(task);
//        aj.put("scaleMenuList",list);
        return AjaxResult.success(task);
    }
    /**
     *患者端首页，查询任务，
     */
    @GetMapping("/getTask1")
    public AjaxResult getTask1(String workstation){
        AjaxResult aj =  AjaxResult.success();
        List<ScaleTask> task = scaleTaskService.getTask(workstation);
        aj.put("task",task);
        List<ScaleMenu> list = scaleMenuService.scaleMenuListByTask(task);
        aj.put("scaleMenuList",list);
        return aj;
    }
    /**
     *获取题目
     */
    @GetMapping("/getScaleQuestions")
    public AjaxResult getScaleQuestions(Long scaleId){
        ScaleQuestions scaleQuestions=new ScaleQuestions();
        scaleQuestions.setScaleId(scaleId);
        List<ScaleQuestions> scaleQuestionsList=scaleQuestionsService.selectScaleQuestionsList(scaleQuestions);
        return AjaxResult.success(scaleQuestionsService.selectScaleQuestionsVoList(scaleQuestionsList,scaleId));
    }

    /**
     * 患者提交任务得分
     */
    @PostMapping("/insertTaskScore")
    public AjaxResult insertTaskScore(@RequestBody ScaleTaskScore scaleTaskScore ){
        //通过任务id，来获取得分任务中要保存的数据
        if (scaleTaskScore!=null&&scaleTaskScore.getTaskId()!=null){
            ScaleTask scaleTask = scaleTaskService.selectScaleTaskById(scaleTaskScore.getTaskId());
            Long score =scaleTaskScore.getScore();
            List<ScaleScoreVo>scaleScoreVos=scaleTaskScore.getScaleScoreVos();
            int i = scaleTaskScoreService.insertTaskScore(scaleTask,score,scaleScoreVos);
            //如果任务得分数据插入成功，将任务表中的状态更改为3 已完成
            if (i==1){
                scaleTaskService.updateTaskStatus(scaleTask);
            }
            return AjaxResult.success(scaleTask);
        }else {
            return AjaxResult.error("请完成答题再提交");
        }
    }

    /**
     * 根据PatientID修改任务
     */

    @PostMapping(value = "/updateByPatientId")
    public AjaxResult editByPatientId(@RequestBody ScaleTask scaleTask)
    {
        return toAjax(scaleTaskService.updateByPatientId(scaleTask));
    }

    /**
     * 获取工作站，进行验证
     * @param workstation
     * @param patientId
     * @return
     */
    @RequestMapping(value = "/getWorkstation",method = RequestMethod.GET)
    public AjaxResult getWorkstation(String workstation,String patientId)
    {
        return AjaxResult.success(scaleTaskService.getWebpackVersion(workstation,patientId));
    }

    /**
     * 新增量表系统任务
     */
    @PreAuthorize("@ss.hasPermi('scale:task:add')")
    @Log(title = "量表系统任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScaleTask scaleTask)
    {

        scaleTask.setCreateBy(SecurityUtils.getUsername());
        scaleTask.setUpdateBy(SecurityUtils.getUsername());
        return AjaxResult.success(scaleTaskService.insertScaleTaskByScaleId(scaleTask));
    }

    /**
     * 查询量表系统任务列表
     */
//    @PreAuthorize("@ss.hasPermi('scale:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScaleTask scaleTask)
    {
        startPage();
        List<ScaleTask> list = scaleTaskService.selectScaleTaskList(scaleTask);
        return getDataTable(list);
    }

    /**
     * 导出量表系统任务列表
     */
    @PreAuthorize("@ss.hasPermi('scale:task:export')")
    @Log(title = "量表系统任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScaleTask scaleTask)
    {
        List<ScaleTask> list = scaleTaskService.selectScaleTaskList(scaleTask);
        ExcelUtil<ScaleTask> util = new ExcelUtil<ScaleTask>(ScaleTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取量表系统任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('scale:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(scaleTaskService.selectScaleTaskById(taskId));
    }



    /**
     * 修改量表系统任务
     */
    @PreAuthorize("@ss.hasPermi('scale:task:edit')")
    @Log(title = "量表系统任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScaleTask scaleTask)
    {
        return toAjax(scaleTaskService.updateScaleTask(scaleTask));
    }

    /**
     * 删除量表系统任务
     */
    @PreAuthorize("@ss.hasPermi('scale:task:remove')")
    @Log(title = "量表系统任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(scaleTaskService.deleteScaleTaskByIds(taskIds));
    }


    //用户下载报告
    @PostMapping(value ="/downloadScale")
    public AjaxResult downloadScale(Long patientId,Long taskId){
        ScalePatient scalePatient =scalePatientService.selectScalePatientById(patientId);
        ScaleTask scaleTask =scaleTaskService.selectScaleTaskById(taskId);
        if (scalePatient==null||scaleTask==null){
            return AjaxResult.error("用户信息不匹配");
        }
        ScaleScore scaleScore=new ScaleScore();
        scaleScore.setScaleId(Long.parseLong(scaleTask.getScaleId()));
        scaleScore.setTaskId(scaleTask.getTaskId());
        List<ScaleScore> scaleScoreList=scaleScoreService.selectScaleScoreList(scaleScore);
        if (scaleScoreList==null||scaleScoreList.size()==0){
            return AjaxResult.error("未获取到答题信息");
        }
        ScaleQuestions scaleQuestions=new ScaleQuestions();
        scaleQuestions.setScaleId(Long.parseLong(scaleTask.getScaleId()));
        List<ScaleQuestions>scaleQuestionsList = scaleQuestionsService.selectScaleQuestionsList(scaleQuestions);
        if (scaleQuestionsList==null||scaleQuestionsList.size()==0){
            return AjaxResult.error("未获取到题目详情");
        }
        Map<String,String> map=new HashMap<>();
        log.info("scaleId:{}",scaleTask.getScaleId());
        if (scaleTask.getScaleId().equals("9")){
            log.info("开始下载SCL-90");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/scl-90.docx";
            String fileName= SclWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成SCL-90");
        }
        if (scaleTask.getScaleId().equals("10")){
            log.info("开始下载GHQ-12");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/ghq-12.docx";
            String fileName= GhqWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成GHQ-12");
        }
        if (scaleTask.getScaleId().equals("18")){
            log.info("开始下载BPRS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/bprs.docx";
            String fileName= BprsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成BPRS");
        }
        if (scaleTask.getScaleId().equals("19")){
            log.info("开始下载SANS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sans.docx";
            String fileName= SansWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成SANS");
        }
        if (scaleTask.getScaleId().equals("20")){
            log.info("开始下载SAPS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/saps.docx";
            String fileName= SapsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成SAPS");
        }
        if (scaleTask.getScaleId().equals("21")){
            log.info("开始下载PANSS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/panss.docx";
            String fileName= PanssWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成PANSS");
        }
        if (scaleTask.getScaleId().equals("22")){
            log.info("开始下载Krawiecka");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/krawiecka.docx";
            String fileName= KrawieckaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成Krawiecka");
        }
        if (scaleTask.getScaleId().equals("24")){
            log.info("开始下载BRMS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/brms.docx";
            String fileName= BrmsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成BRMS");
        }
        if (scaleTask.getScaleId().equals("25")){
            log.info("开始下载YMPS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/ymps.docx";
            String fileName= YmpsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成YMPS");
        }
        if (scaleTask.getScaleId().equals("26")){
            log.info("开始下载HCL-32");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hcl-32.docx";
            String fileName= HclWord.getDld(scalePatient,scaleTask,string,scaleScoreList,scaleQuestionsList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成HCL-32");
        }
        if (scaleTask.getScaleId().equals("27")){
            log.info("开始下载MDQ");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/mdq.docx";
            String fileName= MdqWord.getDld(scalePatient,scaleTask,string,scaleScoreList,scaleQuestionsList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成MDQ");
        }
        if (scaleTask.getScaleId().equals("29")){
            log.info("开始下载HAMD");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hamd.docx";
            String fileName= HamdWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成HAMD");
        }
        if (scaleTask.getScaleId().equals("30")){
            log.info("开始下载MADRS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/madrs.docx";
            String fileName= MadrsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成MADRS");
        }
        if (scaleTask.getScaleId().equals("31")){
            log.info("开始下载NDI");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/ndi.docx";
            String fileName= NdiWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成NDI");
        }
        if (scaleTask.getScaleId().equals("32")){
            log.info("开始下载EPDS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/epds.docx";
            String fileName= EpdsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成EPDS");
        }
        if (scaleTask.getScaleId().equals("33")){
            log.info("开始下载CES-D");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hamd.docx";
            String fileName= CesdWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成CES-D");
        }
        if (scaleTask.getScaleId().equals("34")){
            log.info("开始下载SDS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sds.docx";
            String fileName= SdsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成SDS");
        }
        if (scaleTask.getScaleId().equals("35")){
            log.info("开始下载BDI");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/bdi.docx";
            String fileName= BdiWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成BDI");
        }
        if (scaleTask.getScaleId().equals("36")){
            log.info("开始下载HAD");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/had.docx";
            String fileName= HadWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成HAD");
        }
        if (scaleTask.getScaleId().equals("37")){
            log.info("开始下载PHQ-9");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/phq-9.docx";
            String fileName= PhqWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成PHQ-9");
        }
        if (scaleTask.getScaleId().equals("38")){
            log.info("开始下载GDS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/gds.docx";
            String fileName= GdsWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成GDS");
        }
        if (scaleTask.getScaleId().equals("40")){
            log.info("开始下载HAMA");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成HAMA");
        }
        if (scaleTask.getScaleId().equals("41")){
            log.info("开始下载STAI");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成STAI");
        }
        if (scaleTask.getScaleId().equals("42")){
            log.info("开始下载SAS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sas.docx";
            String fileName= SasWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成SAS");
        }
        if (scaleTask.getScaleId().equals("43")){
            log.info("开始下载PASS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成PASS");
        }
        if (scaleTask.getScaleId().equals("44")){
            log.info("开始下载PDSS");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成PDSS");
        }
        if (scaleTask.getScaleId().equals("45")){
            log.info("开始下载GAD-7");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWord.getDld(scalePatient,scaleTask,string,scaleScoreList);
            map.put(scaleTask.getScaleId(),fileName);
            log.info("下载完成GAD-7");
        }
        return AjaxResult.success(map);
    }



}
