package com.qx.web.controller.eis;

import com.qx.common.core.domain.AjaxResult;
import com.qx.demo.entity.*;
import com.qx.demo.service.IScaleTitleService;
import com.qx.eis.domain.*;
import com.qx.eis.service.*;
import com.qx.patient.util.ExportExcelUtil;
import com.qx.system.service.ISysDictDataService;
import com.qx.web.controller.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * eis请求Controller
 *
 * @author qx
 * @date 2021-07-05
 */
@RestController
@RequestMapping("/eis/patientTask")
public class EisPatientTaskController {

    private static final Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);



    @Autowired
    ISysDictDataService iSysDictDataService;


    @Autowired
    IScaleTitleService scaleTitleService;

    @Autowired
    IEisTableService iEisTableService;

    @Autowired
    IEisTaskScoreService iEisTaskScoreService;

    @Autowired
    IEisUserService iEisUserService;

    @Autowired
    IEisRecordService iEisRecordService;

    @Autowired
    IEisScaleQuestionsService iEisScaleQuestionsService;


    /**
     *获取eis题目
     */
    @GetMapping("/getScaleTitleEis")
    public AjaxResult getScaleTitleEis(Long scaleId){
        List<ScaleTitle> scaleTitleList=scaleTitleService.selectScaleTitleListByScaleId(scaleId);
        return AjaxResult.success(scaleTitleList);
    }

    @GetMapping("/getScaleTitleEis1")
    public AjaxResult getScaleTitleEis1(Long scaleId){
        ScaleTitle scaleTitle = scaleTitleService.selectScaleTitleById(scaleId);
        return AjaxResult.success(scaleTitle);
    }
    /**
     *返回所有题目
     */
    @GetMapping("/getTaskEisTable")
    public AjaxResult getTaskEisTable(){
        long delFlag=0;
        List<EisTable> eisTables =iEisTableService.selectEisTables(delFlag);
        return AjaxResult.success(eisTables);
    }

    //查询全部用户
    @GetMapping("/getAllUser")
    public AjaxResult getAllUser(){
        long delFlag=0;
        List<EisUser> eisUsers =iEisUserService.selectEisUsers(delFlag);
        return AjaxResult.success(eisUsers);
    }
    //用户注册
    @PostMapping("/insertUserRegister")
    public AjaxResult insertUserRegister(
            @RequestBody EisUser eisUser
            ){
        //保存用户信息
        int flag =iEisUserService.insertUserRegister(eisUser);
        if (flag==0){
            return AjaxResult.error("用户已存在");
        }
        return AjaxResult.success();
    }

    //用户登录验证
    @PostMapping("/eisUserLogin")
    public AjaxResult eisUserLogin(String telNumber,String password){

        EisUser eisUser=iEisUserService.userLogin(telNumber,password);

        if (eisUser==null){
            return AjaxResult.error("用户不存在或密码错误");
        }else if(eisUser.getUserId()==null){
            return AjaxResult.error("用户不存在或密码错误");
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("eisUser",eisUser);
        return ajax;
    }

    //查询所有答题记录
    @GetMapping("/getAllRecord")
    public AjaxResult getAllRecord(){
        long delFlag=0;
        List<EisRecord>eisRecordList =iEisRecordService.selectEisRecords(delFlag);
        return AjaxResult.success(eisRecordList);
    }
    //根据用户查询答题记录
    @GetMapping("/getRecordByTel")
    public AjaxResult getRecordByTel(String telNumber){
        List<EisRecord>eisRecordList =iEisRecordService.selectEisRecordByTel(telNumber);
        return AjaxResult.success(eisRecordList);
    }
    //根据完成情况查询答题记录2进行中3已结束
    @GetMapping("/getRecordByStatus")
    public AjaxResult getRecordByTel(Long taskStatus){
        List<EisRecord>eisRecordList =iEisRecordService.selectEisRecordByStatus(taskStatus);
        return AjaxResult.success(eisRecordList);
    }
    //用户点击开始答题
    @GetMapping("/insertRecord")
    public AjaxResult insertRecord(String telNumber,  Long scaleId  ){
        AjaxResult ajax =AjaxResult.success();
        //查询用户获取信息
        EisUser eisUser =iEisUserService.selectEisUserByTel(telNumber);

        if (eisUser==null){
            return AjaxResult.error("用户信息不存在");
        }else {
            ajax.put("user",eisUser);
            //获取表题
            List<ScaleTitle> scaleTitleList = scaleTitleService.selectScaleTitleListByScaleId(scaleId);
            if (scaleTitleList==null||scaleTitleList.size()==0){
                return AjaxResult.error("答题信息不存在");
            }else {
                ajax.put("scaleTitle",scaleTitleList);
            }
        }
        //获取表信息
            EisTable eisTable =iEisTableService.selectEisTableByScaleId(scaleId);
        if (eisTable==null){
            return AjaxResult.error("答题信息不存在");
        }
        //添加答题记录
        int i =iEisRecordService.inUpEisRecord(eisTable,telNumber,scaleId);
        if (i==1){
            ajax.put("code","记录表创建或更新成功");
        }else {
            return AjaxResult.error("记录表更新失败");
        }

        return ajax;
    }

    //用户提交答题
    @PostMapping("/submitEisQuestions")
    public AjaxResult submitEisQuestions(@RequestBody List<EisVo> eisVoList){
        AjaxResult ajax=AjaxResult.success();
        if (eisVoList==null||eisVoList.size()==0){
            return AjaxResult.error("请提交正确信息");
        }
        String telNumber =eisVoList.get(0).getTelNumber();
        //查询用户
        EisUser eisUser =iEisUserService.selectEisUserByTel(telNumber);
        if (eisUser==null){
            return AjaxResult.error("用户信息不存在");
        }
        ajax.put("eisUser",eisUser);
        //处理答题数据
        ajax=iEisRecordService.reviseRecord(eisVoList,telNumber,ajax);


        return ajax;
    }
        //用户下载报告
    @PostMapping(value ="/downloadScale")
    public AjaxResult downloadScale(String telNumber,Long tableId){
        EisUser eisUser=iEisUserService.selectEisUserByTel(telNumber);
        EisRecord eisRecord =iEisRecordService.selectEisRecordById(tableId);
        if (eisUser==null||eisRecord==null){
            return AjaxResult.error("用户信息不匹配");
        }
        Map<String,String> map=new HashMap<>();
        log.info("scaleId:{}",eisRecord.getScaleId());
        if (eisRecord.getScaleId()==51){
            log.info("开始下载eis");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/eis.docx";
            String fileName= EisWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }
        if (eisRecord.getScaleId()==52){
            log.info("开始下载sas");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sas.docx";
            String fileName= SasWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }
        if (eisRecord.getScaleId()==53){
            log.info("开始下载sds");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/sds.docx";
            String fileName= SdsWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }
        if (eisRecord.getScaleId()==54){
            log.info("开始下载hama");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hama.docx";
            String fileName= HamaWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }
        if (eisRecord.getScaleId()==55){
            log.info("开始下载hamd");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+"scaleTemplate/hamd.docx";
            String fileName= HamdWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }
        if (eisRecord.getScaleId()==56){
            log.info("开始下载panss");
            String  string= this.getClass().getResource("/").getPath().toString().replaceAll("%20"," ")+ "scaleTemplate/panss1.docx";
            String fileName= PanssWordUtil.getDld(eisUser,eisRecord,string);
            map.put(eisRecord.getScaleId().toString(),fileName);
        }

        return AjaxResult.success(map);
    }

}
