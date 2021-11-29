package com.qx.demo.service.impl;


import com.qx.demo.entity.*;
import com.qx.demo.entity.vo.*;
import com.qx.demo.mapper.*;
import com.qx.demo.service.TaskAllService;
import com.qx.demo.util.LogUtil;
import com.qx.patient.domain.*;
import com.qx.patient.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskAllserviceImpl implements TaskAllService {
    @Resource
    private  PatientUserMapper patientUserMapper;
    @Resource
    private PatientSocietyTaskMapper patientSocietyTaskMapper;
    @Resource
    private PatientBasisTaskMapper patientBasisTaskMapper;
    @Resource
    private PatientIntermediateTaskMapper patientIntermediateTaskMapper;
    @Resource
    private PatientAdvancedTaskMapper patientAdvancedTaskMapper;
    @Resource
    private ScaleTitleMapper scaleTitleMapper;
    @Resource
    private ZzFzyqTypeMapper zzFzyqTypeMapper;
    @Resource
    private ZzMettTypeMapper zzMettTypeMapper;
    @Resource
    private ZzShtlTypeMapper zzShtlTypeMapper;
    @Resource
    private ZzSysbTypeMapper zzSysbTypeMapper;
    @Resource
    private ZzGjTypeMapper zzGjTypeMapper;
    /*===========================================================================================================*/
    @Resource
    private ZzDldMapper zzDldMapper;
    @Resource
    private ZzEisMapper zzEisMapper;
    @Resource
    private ZzIriCMapper zzIriCMapper;
    @Resource
    private ZzFzyqScoreMapper zzFzyqScoreMapper;
    @Resource
    private ZzMettScoreMapper zzMettScoreMapper;
    @Resource
    private ZzShtlScoreMapper zzShtlScoreMapper;
    @Resource
    private ZzSysbScoreMapper zzSysbScoreMapper;
    /*==============================================================================================================*/
    @Resource
    private DldLogMapper dldLogMapper;
    @Resource
    private EisLogMapper eisLogMapper;
    @Resource
    private IricLogMapper iricLogMapper;
    @Resource
    private MettLogMapper mettLogMapper;
    @Resource
    private FzyqLogMapper fzyqLogMapper;
    @Resource
    private ShtlLogMapper shtlLogMapper;
    @Resource
    private SysbLogMapper sysbLogMapper;
    @Resource
    private StatisticsTable1Mapper statisticsTable1Mapper;

    @Override
    public TaskAVo getTaskA(String workStation) {
        TaskAVo taskAVo =new TaskAVo();
        PatientSocietyTask patientSocietyTask=patientSocietyTaskMapper.selectTaskByWorkStation(workStation);
        if(patientSocietyTask!=null){
            taskAVo.setPatientSocietyTask(patientSocietyTask);
            String[] arr=patientSocietyTask.getTypeIds().split(",");
            Map<String,List<Object>> map=new HashMap<>();
            for (String s:arr) {
                if("19".equals(s)){
                    ScaleTitle scaleTitle=new ScaleTitle();
                    scaleTitle.setScaleId(Long.parseLong(s));
                List<ScaleTitle> list=scaleTitleMapper.selectScaleTitleList(scaleTitle);
                    for (ScaleTitle g : list) {
                        List<String> stringList = new ArrayList<>();
                        if (!"".equals(g.getOptionA()) && g.getOptionA() != null) {
                            stringList.add(g.getOptionA());
                        }
                        if (!"".equals(g.getOptionB()) && g.getOptionB() != null) {
                            stringList.add(g.getOptionB());
                        }
                        if (!"".equals(g.getOptionC()) && g.getOptionC() != null) {
                            stringList.add(g.getOptionC());
                        }
                        if (!"".equals(g.getOptionD()) && g.getOptionD() != null) {
                            stringList.add(g.getOptionD());
                        }
                        if (!"".equals(g.getOptionE()) && g.getOptionE() != null) {
                            stringList.add(g.getOptionE());
                        }
                        if (!"".equals(g.getOptionF()) && g.getOptionF() != null) {
                            stringList.add(g.getOptionF());
                        }
                        if (!"".equals(g.getOptionG()) && g.getOptionG() != null) {
                            stringList.add(g.getOptionG());
                        }
                        g.setStringList(stringList);
                    }
                  map.put(s, Collections.singletonList(list));
                }
                if("20".equals(s)){
                    ScaleTitle scaleTitle=new ScaleTitle();
                    scaleTitle.setScaleId(Long.parseLong(s));
                    List<ScaleTitle> list=scaleTitleMapper.selectScaleTitleList(scaleTitle);
                    for (ScaleTitle g : list) {
                        List<String> stringList = new ArrayList<>();
                        if (!"".equals(g.getOptionA()) && g.getOptionA() != null) {
                            stringList.add(g.getOptionA());
                        }
                        if (!"".equals(g.getOptionB()) && g.getOptionB() != null) {
                            stringList.add(g.getOptionB());
                        }
                        if (!"".equals(g.getOptionC()) && g.getOptionC() != null) {
                            stringList.add(g.getOptionC());
                        }
                        if (!"".equals(g.getOptionD()) && g.getOptionD() != null) {
                            stringList.add(g.getOptionD());
                        }
                        if (!"".equals(g.getOptionE()) && g.getOptionE() != null) {
                            stringList.add(g.getOptionE());
                        }
                        if (!"".equals(g.getOptionF()) && g.getOptionF() != null) {
                            stringList.add(g.getOptionF());
                        }
                        if (!"".equals(g.getOptionG()) && g.getOptionG() != null) {
                            stringList.add(g.getOptionG());
                        }
                        g.setStringList(stringList);
                    }
                    map.put(s, Collections.singletonList(list));
                }
                if("21".equals(s)){
                    ScaleTitle scaleTitle=new ScaleTitle();
                    scaleTitle.setScaleId(Long.parseLong(s));
                    List<ScaleTitle> list=scaleTitleMapper.selectScaleTitleList(scaleTitle);
                    for (ScaleTitle g : list) {
                        List<String> stringList = new ArrayList<>();
                        if (!"".equals(g.getOptionA()) && g.getOptionA() != null) {
                            stringList.add(g.getOptionA());
                        }
                        if (!"".equals(g.getOptionB()) && g.getOptionB() != null) {
                            stringList.add(g.getOptionB());
                        }
                        if (!"".equals(g.getOptionC()) && g.getOptionC() != null) {
                            stringList.add(g.getOptionC());
                        }
                        if (!"".equals(g.getOptionD()) && g.getOptionD() != null) {
                            stringList.add(g.getOptionD());
                        }
                        if (!"".equals(g.getOptionE()) && g.getOptionE() != null) {
                            stringList.add(g.getOptionE());
                        }
                        if (!"".equals(g.getOptionF()) && g.getOptionF() != null) {
                            stringList.add(g.getOptionF());
                        }
                        if (!"".equals(g.getOptionG()) && g.getOptionG() != null) {
                            stringList.add(g.getOptionG());
                        }
                        g.setStringList(stringList);
                    }
                    map.put(s, Collections.singletonList(list));
                }
                if("22".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                  List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    for (String s1:map1.keySet()) {
                        Random random=new Random();
                        List<Integer> integerList=new ArrayList<>();
                        for(int i=1;i<=4;i++){
                           int temp=random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                            if(!(integerList.contains(temp))){
                                integerList.add(temp);
                                set.add(map1.get(s1).get(temp));
                            }
                            else{
                                i--;
                            }
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("23".equals(s)){
                    String[] arra=new String[]{"惊讶的","厌烦的","不屑的","愤怒的","高兴的","怀疑的","惊恐的","埋怨的","难以置信的","深情的","严肃的","忧虑的"};;
                    ZzFzyqType zzFzyqType=new ZzFzyqType();
                    zzFzyqType.setGrade("0");
                    List<ZzFzyqType> fzyqTypeList=zzFzyqTypeMapper.selectZzFzyqTypeList(zzFzyqType);
                    for (ZzFzyqType f:fzyqTypeList) {
                        List<String> arr1 = new ArrayList<>();
                        Set<String> stringList = new HashSet<>();
                        stringList.add(f.getPicType());
                        for (String s1 : arra) {
                            if (!f.getPicType().equals(s1)) {
                                arr1.add(s1);
                            }
                        }
                        for (int i = 0; i < 3; i++) {
                            Random random = new Random();
                            int a = random.nextInt(arr1.size() - 1);
                            stringList.add(arr1.get(a));
                            arr1.remove(a);
                        }
                        f.setSet(stringList);
                    }
                    map.put(s, Collections.singletonList(fzyqTypeList));
                }
                if("24".equals(s)){
                    ZzShtlType zzShtlType=new ZzShtlType();
                    zzShtlType.setGrade("0");
                   List<ZzShtlType> list=zzShtlTypeMapper.selectZzShtlTypeList(zzShtlType);
                    if(list!=null &&list.size()!=0){
                            for (ZzShtlType z:list) {
                                Set<ZzMettTypeVo> set=new HashSet<>();
                                List<String> list1=new ArrayList<>();
                                if(!"".equals(z.getaUrla())&&z.getaUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.getaUrla());
                                    zzMettTypeVo.setExPartUrl(z.getaUrlb());
                                    list1.add(z.getaUrla());
                                    set.add(zzMettTypeVo);
                                }
                                if(!"".equals(z.getbUrla()) &&z.getbUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.getbUrla());
                                    zzMettTypeVo.setExPartUrl(z.getbUrlb());
                                    set.add(zzMettTypeVo);
                                    list1.add(z.getbUrla());

                                }
                                if(!"".equals(z.getcUrla())&&z.getcUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.getcUrla());
                                    zzMettTypeVo.setExPartUrl(z.getcUrlb());
                                    set.add(zzMettTypeVo);
                                    list1.add(z.getcUrla());
                                }
                                if(!"".equals(z.getdUrla())&&z.getdUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.getdUrla());
                                    zzMettTypeVo.setExPartUrl(z.getdUrlb());
                                    set.add(zzMettTypeVo);
                                    list1.add(z.getdUrla());
                                }
                                if(!"".equals(z.geteUrla())&&z.geteUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.geteUrla());
                                    zzMettTypeVo.setExPartUrl(z.geteUrlb());
                                    set.add(zzMettTypeVo);
                                    list1.add(z.geteUrla());
                                }
                                if(!"".equals(z.getfUrla())&&z.getfUrla()!=null){
                                    ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                    zzMettTypeVo.setExId(z.getfUrla());
                                    zzMettTypeVo.setExPartUrl(z.getfUrlb());
                                    set.add(zzMettTypeVo);
                                    list1.add(z.getfUrla());
                                }
                                z.setList(Collections.singletonList(list1));
                                z.setSet(set);
                            }
                        map.put(s, Collections.singletonList(list));
                    }
                }
                if("25".equals(s)){
                    ZzSysbType zzSysbType=new ZzSysbType();
                    zzSysbType.setParentId(0L);
                 List<ZzSysbType> list=zzSysbTypeMapper.selectZzSysbTypeList(zzSysbType);
                    for (ZzSysbType z:list) {
                        ZzSysbType zzSysbType1=new ZzSysbType();
                        zzSysbType1.setParentId(z.getId());
                      List<ZzSysbType> list1=zzSysbTypeMapper.selectZzSysbTypeList(zzSysbType1);
                      z.setList(list1);
                    }
                    map.put(s, Collections.singletonList(list));
                }
            }
            taskAVo.setSocietyMap(map);
        }
        return taskAVo;
    }

    @Override
    public TaskBVo getTaskB(String workStation) {
        TaskBVo taskBVo=new TaskBVo();
        PatientBasisTask patientBasisTask=patientBasisTaskMapper.selectTaskByWorkStation(workStation);
        if(patientBasisTask!=null){
            taskBVo.setPatientBasisTask(patientBasisTask);
            String[] arr=patientBasisTask.getTypeIds().split(",");
            Map<String,List<Object>> map=new HashMap<>();
            for (String s:arr) {
                if("26".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"高兴".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("高兴".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                        Random random=new Random();
                        List<Integer> integerList=new ArrayList<>();
                        for(int i=1;i<=6;i++){
                            int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                            if(!(integerList.contains(temp))){
                                integerList.add(temp);
                                set.add(list1.get(temp));
                            }
                            else{
                                i--;
                            }
                        }
                    map.put(s, Collections.singletonList(set));
                }
                if("27".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"悲伤".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("悲伤".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                    Random random=new Random();
                    List<Integer> integerList=new ArrayList<>();
                    for(int i=1;i<=6;i++){
                        int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                        if(!(integerList.contains(temp))){
                            integerList.add(temp);
                            set.add(list1.get(temp));
                        }
                        else{
                            i--;
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("28".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"愤怒".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("愤怒".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                    Random random=new Random();
                    List<Integer> integerList=new ArrayList<>();
                    for(int i=1;i<=6;i++){
                        int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                        if(!(integerList.contains(temp))){
                            integerList.add(temp);
                            set.add(list1.get(temp));
                        }
                        else{
                            i--;
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("29".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"恐惧".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("恐惧".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                    Random random=new Random();
                    List<Integer> integerList=new ArrayList<>();
                    for(int i=1;i<=6;i++){
                        int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                        if(!(integerList.contains(temp))){
                            integerList.add(temp);
                            set.add(list1.get(temp));
                        }
                        else{
                            i--;
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("30".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"惊讶".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("惊讶".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                    Random random=new Random();
                    List<Integer> integerList=new ArrayList<>();
                    for(int i=1;i<=6;i++){
                        int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                        if(!(integerList.contains(temp))){
                            integerList.add(temp);
                            set.add(list1.get(temp));
                        }
                        else{
                            i--;
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("31".equals(s)){
                    Set<ZzMettType> set=new HashSet<>();
                    List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                    Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                    List<ZzMettType> list1=new ArrayList<>();
                    for (String s1:map1.keySet()) {
                        Random random = new Random();
                        List<Integer> integerList = new ArrayList<>();
                        if (!"中性".equals(s1)&&!"厌恶".equals(s1)) {
                            list1.addAll(map1.get(s1));
                        }
                        if ("厌恶".equals(s1)) {
                            for (int i = 1; i <= 6; i++) {
                                int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                                if (!(integerList.contains(temp))) {
                                    integerList.add(temp);
                                    set.add(map1.get(s1).get(temp));
                                } else {
                                    i--;
                                }
                            }
                        }
                    }
                    Random random=new Random();
                    List<Integer> integerList=new ArrayList<>();
                    for(int i=1;i<=6;i++){
                        int temp=random.nextInt(list1.size());//将产生的随机数作为被抽数组的索引
                        if(!(integerList.contains(temp))){
                            integerList.add(temp);
                            set.add(list1.get(temp));
                        }
                        else{
                            i--;
                        }
                    }
                    map.put(s, Collections.singletonList(set));
                }
                if("32".equals(s)){
                    String[] arra=new String[]{"惊讶的","厌烦的","不屑的","愤怒的","高兴的","怀疑的","惊恐的","埋怨的","难以置信的","深情的","严肃的","忧虑的"};;
                    ZzFzyqType zzFzyqType=new ZzFzyqType();
                    zzFzyqType.setGrade("1");
                    List<ZzFzyqType> fzyqTypeList=zzFzyqTypeMapper.selectZzFzyqTypeList(zzFzyqType);
                    for (ZzFzyqType f:fzyqTypeList) {
                        List<String> arr1 = new ArrayList<>();
                        Set<String> stringList = new HashSet<>();
                        stringList.add(f.getPicType());
                        for (String s1 : arra) {
                            if (!f.getPicType().equals(s1)) {
                                arr1.add(s1);
                            }
                        }
                        for (int i = 0; i < 3; i++) {
                            Random random = new Random();
                            int a = random.nextInt(arr1.size() - 1);
                            stringList.add(arr1.get(a));
                            arr1.remove(a);
                        }
                        f.setSet(stringList);
                    }
                    map.put(s, Collections.singletonList(fzyqTypeList));
                }
                if("33".equals(s)){
                    ZzShtlType zzShtlType=new ZzShtlType();
                    zzShtlType.setGrade("1");
                    List<ZzShtlType> list=zzShtlTypeMapper.selectZzShtlTypeList(zzShtlType);
                    if(list!=null &&list.size()!=0){
                        for (ZzShtlType z:list) {
                            Set<ZzMettTypeVo> set=new HashSet<>();
                            List<String> list1=new ArrayList<>();
                            if(!"".equals(z.getaUrla())&&z.getaUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.getaUrla());
                                zzMettTypeVo.setExPartUrl(z.getaUrlb());
                                list1.add(z.getaUrla());
                                set.add(zzMettTypeVo);
                            }
                            if(!"".equals(z.getbUrla()) &&z.getbUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.getbUrla());
                                zzMettTypeVo.setExPartUrl(z.getbUrlb());
                                set.add(zzMettTypeVo);
                                list1.add(z.getbUrla());

                            }
                            if(!"".equals(z.getcUrla())&&z.getcUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.getcUrla());
                                zzMettTypeVo.setExPartUrl(z.getcUrlb());
                                set.add(zzMettTypeVo);
                                list1.add(z.getcUrla());
                            }
                            if(!"".equals(z.getdUrla())&&z.getdUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.getdUrla());
                                zzMettTypeVo.setExPartUrl(z.getdUrlb());
                                set.add(zzMettTypeVo);
                                list1.add(z.getdUrla());
                            }
                            if(!"".equals(z.geteUrla())&&z.geteUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.geteUrla());
                                zzMettTypeVo.setExPartUrl(z.geteUrlb());
                                set.add(zzMettTypeVo);
                                list1.add(z.geteUrla());
                            }
                            if(!"".equals(z.getfUrla())&&z.getfUrla()!=null){
                                ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                zzMettTypeVo.setExId(z.getfUrla());
                                zzMettTypeVo.setExPartUrl(z.getfUrlb());
                                set.add(zzMettTypeVo);
                                list1.add(z.getfUrla());
                            }
                            z.setList(Collections.singletonList(list1));
                            z.setSet(set);
                        }
                        map.put(s, Collections.singletonList(list));
                    }
                }
            }
            taskBVo.setBasisMap(map);
        }

        return taskBVo;
    }

    @Override
    public TaskCVo getTaskC(String workStation) {
        TaskCVo taskCVo=new TaskCVo();
        PatientIntermediateTask patientIntermediateTask=patientIntermediateTaskMapper.selectTaskByWorkStation(workStation);
        if(patientIntermediateTask!=null){
            taskCVo.setPatientIntermediateTask(patientIntermediateTask);
            String[] arr=patientIntermediateTask.getTypeIds().split(",");
            Map<String,List<Object>> listMap=new HashMap<>();
            Map<String,Set<List<Object>>> map=new HashMap<>();
            for (String s:arr) {
               if("35".equals(s)){
                   Set<List<Object>> listSet=new HashSet<>();
                   List<ZzMettType> list=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                   Map<String, List<ZzMettType>> map1 = list.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                   for (String s1:map1.keySet()) {
                       Random random = new Random();
                       List<Integer> integerList = new ArrayList<>();
                       if (!"中性".equals(s1) && !"高兴".equals(s1)) {
                           List<ZzMettType> list1=new ArrayList<>();
                           for (int i = 1; i <= 2; i++) {
                               int temp = random.nextInt(map1.get(s1).size());//将产生的随机数作为被抽数组的索引
                               if (!(integerList.contains(temp))) {
                                   integerList.add(temp);
                                   list1.add(map1.get(s1).get(temp));
                               } else {
                                   i--;
                               }
                           }
                          listSet.add(Collections.singletonList(list1));
                       }
                   }
                   /*==================================================================================================*/
                   ZzMettType a=new ZzMettType();
                   a.setExId("惊讶");
                   List<ZzMettType> lista=zzMettTypeMapper.selectZzMettTypeList(a);
                   ZzMettType b=new ZzMettType();
                   b.setExId("恐惧");
                   List<ZzMettType> listb=zzMettTypeMapper.selectZzMettTypeList(b);
                   ZzMettType c=new ZzMettType();
                   c.setExId("愤怒");
                   List<ZzMettType> listc=zzMettTypeMapper.selectZzMettTypeList(c);
                   ZzMettType d=new ZzMettType();
                   d.setExId("厌恶");
                   List<ZzMettType> listd=zzMettTypeMapper.selectZzMettTypeList(d);
                   ZzMettType e=new ZzMettType();
                   e.setExId("悲伤");
                   List<ZzMettType> liste=zzMettTypeMapper.selectZzMettTypeList(e);
                   Random random=new Random();
                   for (int i=0;i<2;i++) {
                       List<ZzMettType> list1=new ArrayList<>();
                       list1.add(lista.get(random.nextInt(10)));
                       list1.add(listb.get(random.nextInt(10)));
                       listSet.add(Collections.singletonList(list1));
                       List<ZzMettType> list2=new ArrayList<>();
                       list2.add(listc.get(random.nextInt(10)));
                       list2.add(listd.get(random.nextInt(10)));
                       listSet.add(Collections.singletonList(list2));
                       List<ZzMettType> list3=new ArrayList<>();
                       list3.add(listb.get(random.nextInt(10)));
                       list3.add(liste.get(random.nextInt(10)));
                       listSet.add(Collections.singletonList(list3));
                       List<ZzMettType> list4=new ArrayList<>();
                       list4.add(listb.get(random.nextInt(10)));
                       list4.add(listc.get(random.nextInt(10)));
                       listSet.add(Collections.singletonList(list4));
                   }
                   map.put(s,listSet);
                   }
                   if("36".equals(s)){
                       List<Object> list=new ArrayList<>();
                       Random random=new Random();
                       List<ZzMettType> list1=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                       Map<String, List<ZzMettType>> map1 = list1.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                       for (int i=0;i<10;i++){
                          ZzMettType zzMettType=list1.get(random.nextInt(list1.size()));
                          List<ZzMettType> list2=new ArrayList<>();
                          Map<Long,ZzMettTypeVo> map2=new HashMap<>();
                           for (String s1:map1.keySet()) {
                               if(s1.equals(zzMettType.getExId())){
                                   ZzMettType zzMettType1=map1.get(s1).get(random.nextInt(map1.get(s1).size()));
                                   ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                   zzMettTypeVo.setExId(zzMettType1.getExId());
                                   zzMettTypeVo.setExPartUrl(zzMettType1.getExPartUrl());
                                   map2.put(zzMettType1.getId(),zzMettTypeVo);
                               }else{
                                 list2.addAll(map1.get(s1));
                               }
                           }
                           List<Integer> integerList=new ArrayList<>();
                           for (int j=0;j<3;j++){
                               int temp = random.nextInt(list2.size());//将产生的随机数作为被抽数组的索引
                               if (!(integerList.contains(temp))) {
                                   integerList.add(temp);
                                   ZzMettType zzMettType1=list2.get(temp);
                                   ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                                   zzMettTypeVo.setExId(zzMettType1.getExId());
                                   zzMettTypeVo.setExPartUrl(zzMettType1.getExPartUrl());
                                   map2.put(zzMettType1.getId(),zzMettTypeVo);
                               } else {
                                   j--;
                               }
                           }
                           zzMettType.setMap(map2);
                           list.add(zzMettType);
                       }
                       listMap.put(s,list);
               }
               if("37".equals(s)){
                   ScaleTitle scaleTitle=new ScaleTitle();
                   scaleTitle.setScaleId(Long.parseLong(s));
                 List<ScaleTitle> list=scaleTitleMapper.selectScaleTitleList(scaleTitle);
                   for (ScaleTitle sc:list){
                       ZzMettType zzMettType=new ZzMettType();
                       zzMettType.setExId(sc.getOptionA());
                      List<ZzMettType> list1=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                       Map<String, List<ZzMettType>> map1 = list1.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                       Set<ZzMettType> set=new HashSet<>();
                       List<ZzMettType> list2=new ArrayList<>();
                       Random random=new Random();
                       for (String s1:map1.keySet()) {
                           if(sc.getOptionA().equals(s1)){
                               int temp = random.nextInt(map1.get(s1).size());
                               set.add(map1.get(s1).get(temp));
                               sc.setOptionB(map1.get(s1).get(temp).getExAllUrl());
                           }else{
                               list2.addAll(map1.get(s1));
                           }
                       }
                       List<Integer> integerList=new ArrayList<>();
                      for (int i=0;i<3;i++){
                          int temp = random.nextInt(list2.size());//将产生的随机数作为被抽数组的索引
                          if (!(integerList.contains(temp))) {
                              integerList.add(temp);
                              set.add(list2.get(temp));
                          } else {
                              i--;
                          }
                      }
                      sc.setSet(set);
                   }
                   listMap.put(s, Collections.singletonList(list));
               }
               if("38".equals(s)){
                   ScaleTitle scaleTitle=new ScaleTitle();
                   scaleTitle.setScaleId(Long.parseLong(s));
                   List<ScaleTitle> list=scaleTitleMapper.selectScaleTitleList(scaleTitle);
                   for (ScaleTitle sc:list){
                       ZzMettType zzMettType=new ZzMettType();
                       zzMettType.setExId(sc.getOptionA());
                       List<ZzMettType> list1=zzMettTypeMapper.selectZzMettTypeList(new ZzMettType());
                       Map<String, List<ZzMettType>> map1 = list1.stream().collect(Collectors.groupingBy(ZzMettType::getExId));
                       Set<ZzMettType> set=new HashSet<>();
                       List<ZzMettType> list2=new ArrayList<>();
                       Random random=new Random();
                       for (String s1:map1.keySet()) {
                           if(sc.getOptionA().equals(s1)){
                               int temp = random.nextInt(map1.get(s1).size());
                               set.add(map1.get(s1).get(temp));
                               sc.setOptionB(map1.get(s1).get(temp).getExAllUrl());
                           }else{
                               list2.addAll(map1.get(s1));
                           }
                       }
                       List<Integer> integerList=new ArrayList<>();
                       for (int i=0;i<3;i++){
                           int temp = random.nextInt(list2.size());//将产生的随机数作为被抽数组的索引
                           if (!(integerList.contains(temp))) {
                               integerList.add(temp);
                               set.add(list2.get(temp));
                           } else {
                               i--;
                           }
                       }
                       sc.setSet(set);
                   }
                   listMap.put(s, Collections.singletonList(list));
               }
               if("39".equals(s)){
                   String[] arra=new String[]{"惊讶的","厌烦的","不屑的","愤怒的","高兴的","怀疑的","惊恐的","埋怨的","难以置信的","深情的","严肃的","忧虑的"};;
                   ZzFzyqType zzFzyqType=new ZzFzyqType();
                   zzFzyqType.setGrade("2");
                   List<ZzFzyqType> fzyqTypeList=zzFzyqTypeMapper.selectZzFzyqTypeList(zzFzyqType);
                   for (ZzFzyqType f:fzyqTypeList) {
                       List<String> arr1 = new ArrayList<>();
                       Set<String> stringList = new HashSet<>();
                       stringList.add(f.getPicType());
                       for (String s1 : arra) {
                           if (!f.getPicType().equals(s1)) {
                               arr1.add(s1);
                           }
                       }
                       for (int i = 0; i < 3; i++) {
                           Random random = new Random();
                           int a = random.nextInt(arr1.size() - 1);
                           stringList.add(arr1.get(a));
                           arr1.remove(a);
                       }
                       f.setSet(stringList);
                   }
                  listMap.put(s, Collections.singletonList(fzyqTypeList));
               }
               if("40".equals(s)){
                   ZzShtlType zzShtlType=new ZzShtlType();
                   zzShtlType.setGrade("2");
                   List<ZzShtlType> list=zzShtlTypeMapper.selectZzShtlTypeList(zzShtlType);
                   if(list!=null &&list.size()!=0){
                       for (ZzShtlType z:list) {
                           Set<ZzMettTypeVo> set=new HashSet<>();
                           List<String> list1=new ArrayList<>();
                           if(!"".equals(z.getaUrla())&&z.getaUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.getaUrla());
                               zzMettTypeVo.setExPartUrl(z.getaUrlb());
                               list1.add(z.getaUrla());
                               set.add(zzMettTypeVo);
                           }
                           if(!"".equals(z.getbUrla()) &&z.getbUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.getbUrla());
                               zzMettTypeVo.setExPartUrl(z.getbUrlb());
                               set.add(zzMettTypeVo);
                               list1.add(z.getbUrla());

                           }
                           if(!"".equals(z.getcUrla())&&z.getcUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.getcUrla());
                               zzMettTypeVo.setExPartUrl(z.getcUrlb());
                               set.add(zzMettTypeVo);
                               list1.add(z.getcUrla());
                           }
                           if(!"".equals(z.getdUrla())&&z.getdUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.getdUrla());
                               zzMettTypeVo.setExPartUrl(z.getdUrlb());
                               set.add(zzMettTypeVo);
                               list1.add(z.getdUrla());
                           }
                           if(!"".equals(z.geteUrla())&&z.geteUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.geteUrla());
                               zzMettTypeVo.setExPartUrl(z.geteUrlb());
                               set.add(zzMettTypeVo);
                               list1.add(z.geteUrla());
                           }
                           if(!"".equals(z.getfUrla())&&z.getfUrla()!=null){
                               ZzMettTypeVo zzMettTypeVo=new ZzMettTypeVo();
                               zzMettTypeVo.setExId(z.getfUrla());
                               zzMettTypeVo.setExPartUrl(z.getfUrlb());
                               set.add(zzMettTypeVo);
                               list1.add(z.getfUrla());
                           }
                           z.setList(Collections.singletonList(list1));
                           z.setSet(set);
                       }
                       listMap.put(s, Collections.singletonList(list));
                   }
               }
            }
            taskCVo.setSetMap(map);
            taskCVo.setIntermediateMap(listMap);
        }
        return taskCVo;
    }

    @Override
    public TaskDVo getTaskD(String workStation) {
        TaskDVo taskDVo=new TaskDVo();
        PatientAdvancedTask patientAdvancedTask=patientAdvancedTaskMapper.selectTaskByWorkStation(workStation);
        if(patientAdvancedTask!=null){
            taskDVo.setPatientAdvancedTask(patientAdvancedTask);
            String[] arr=patientAdvancedTask.getTypeIds().split(",");
            Map<String,Object> map=new LinkedHashMap<>();
            ZzGjType zzGjType=new ZzGjType();
            zzGjType.setParentId(0L);
            List<ZzGjType> list=zzGjTypeMapper.selectZzGjTypeList(zzGjType);
            for (String s:arr) {
                if("42".equals(s)){
                    ZzGjType zzGjType1=list.get(0);
                    ZzGjType zzGjType2=new ZzGjType();
                    zzGjType2.setParentId(zzGjType1.getId());
                    zzGjType1.setList(zzGjTypeMapper.selectZzGjTypeList(zzGjType2));
                    map.put(s,zzGjType1);
                }if("43".equals(s)){
                    ZzGjType zzGjType1=list.get(1);
                    ZzGjType zzGjType2=new ZzGjType();
                    zzGjType2.setParentId(zzGjType1.getId());
                    zzGjType1.setList(zzGjTypeMapper.selectZzGjTypeList(zzGjType2));
                    map.put(s,zzGjType1);
                }if("44".equals(s)){
                    ZzGjType zzGjType1=list.get(2);
                    ZzGjType zzGjType2=new ZzGjType();
                    zzGjType2.setParentId(zzGjType1.getId());
                    zzGjType1.setList(zzGjTypeMapper.selectZzGjTypeList(zzGjType2));
                    map.put(s,zzGjType1);
                }if("45".equals(s)){
                    ZzGjType zzGjType1=list.get(3);
                    ZzGjType zzGjType2=new ZzGjType();
                    zzGjType2.setParentId(zzGjType1.getId());
                    zzGjType1.setList(zzGjTypeMapper.selectZzGjTypeList(zzGjType2));
                    map.put(s,zzGjType1);
                }
            }
            taskDVo.setAdvancedMap(map);
        }

        return taskDVo;
    }

    @Override
    public int addResult(List<ResultVo> resultVoList) {
        if(resultVoList!=null &&resultVoList.size()!=0){
            int a=0;
            PatientSocietyTask patientSocietyTask=new PatientSocietyTask();
            PatientSocietyTask patientSocietyTask1=patientSocietyTaskMapper.selectPatientSocietyTaskById(resultVoList.get(0).getTaskId());
            for (ResultVo r:resultVoList) {
                r.setTestDate(new Date());
                if(r.getScaleId()==19){
                    ZzDld zzDld=new ZzDld();
                    BeanUtils.copyProperties(r,zzDld);
                    a+=zzDldMapper.insertZzDld(zzDld);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==20){
                    ZzEis zzEis=new ZzEis();
                    BeanUtils.copyProperties(r,zzEis);
                    a+=zzEisMapper.insertZzEis(zzEis);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==21){
                    ZzIriC zzIriC=new ZzIriC();
                    BeanUtils.copyProperties(r,zzIriC);
                    a+=zzIriCMapper.insertZzIriC(zzIriC);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==22){
                    ZzMettScore zzMettScore=new ZzMettScore();
                    BeanUtils.copyProperties(r,zzMettScore);
                    a+=zzMettScoreMapper.insertZzMettScore(zzMettScore);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==23){
                    ZzFzyqScore zzFzyqScore=new ZzFzyqScore();
                    BeanUtils.copyProperties(r,zzFzyqScore);
                    a+=zzFzyqScoreMapper.insertZzFzyqScore(zzFzyqScore);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==24){
                    ZzShtlScore zzShtlScore=new ZzShtlScore();
                    BeanUtils.copyProperties(r,zzShtlScore);
                    a+=zzShtlScoreMapper.insertZzShtlScore(zzShtlScore);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
                if(r.getScaleId()==25){
                    ZzSysbScore zzSysbScore=new ZzSysbScore();
                    BeanUtils.copyProperties(r,zzSysbScore);
                    a+=zzSysbScoreMapper.insertZzSysbScore(zzSysbScore);
                    /*================================================================*/
                    patientSocietyTask.setTaskId(r.getTaskId());
                    if(!"".equals(patientSocietyTask1.getScaleId()) &&patientSocietyTask1.getScaleId()!=null) {
                        patientSocietyTask.setScaleId(patientSocietyTask1.getScaleId()+","+r.getScaleId());
                    }else{
                        patientSocietyTask.setScaleId(r.getScaleId()+"");
                    }
                    patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask);
                    /*================================================================*/
                }
            }
            PatientSocietyTask patientSocietyTask2=patientSocietyTaskMapper.selectPatientSocietyTaskById(resultVoList.get(0).getTaskId());
            if(patientSocietyTask2.getScaleId().length()==patientSocietyTask2.getTypeIds().length()){
                PatientSocietyTask patientSocietyTask3=new PatientSocietyTask();
                patientSocietyTask3.setTaskId(patientSocietyTask2.getTaskId());
                patientSocietyTask3.setTaskStatus("3");
                patientSocietyTask3.setDelFlag("1");
                patientSocietyTaskMapper.updatePatientSocietyTask(patientSocietyTask3);
            }
            /*===========================================================================================*/
            LogUtil logUtil=new LogUtil();
            PatientUser patientUser=patientUserMapper.selectPatientUserById(resultVoList.get(0).getPatientId());
            if(resultVoList.get(0).getScaleId()==19){
            DldLog dldLog=logUtil.addDldLog(resultVoList,patientUser);
            dldLogMapper.insertDldLog(dldLog);
            }else if(resultVoList.get(0).getScaleId()==20){
                EisLog eisLog=logUtil.addEisLog(resultVoList,patientUser);
                eisLogMapper.insertEisLog(eisLog);
            }else if(resultVoList.get(0).getScaleId()==21){
                IricLog iricLog=logUtil.addIricLog(resultVoList,patientUser);
                iricLogMapper.insertIricLog(iricLog);
            }else if(resultVoList.get(0).getScaleId()==22){
                MettLog mettLog=logUtil.addMettLog(resultVoList,patientUser);
                mettLogMapper.insertMettLog(mettLog);
            }else if(resultVoList.get(0).getScaleId()==23){
                FzyqLog fzyqLog=logUtil.addFzyqLog(resultVoList,patientUser);
                 fzyqLogMapper.insertFzyqLog(fzyqLog);
            }else if(resultVoList.get(0).getScaleId()==24){
                 ShtlLog shtlLog=logUtil.addShtlLog(resultVoList,patientUser);
                 shtlLogMapper.insertShtlLog(shtlLog);
            }else if(resultVoList.get(0).getScaleId()==25){
                 SysbLog sysbLog=logUtil.addSysbLog(resultVoList,patientUser);
                 sysbLogMapper.insertSysbLog(sysbLog);
            }
           return a;
        }else{
            return 0;
        }
    }

    @Override
    public int updateBasisTask(String taskId, String scaleId) {
        PatientBasisTask patientBasisTaskById=patientBasisTaskMapper.selectPatientBasisTaskById(Long.parseLong(taskId));
        PatientBasisTask patientBasisTask=new PatientBasisTask();
        patientBasisTask.setTaskId(Long.parseLong(taskId));
        if("".equals(patientBasisTaskById.getScaleId()) ||patientBasisTaskById.getScaleId()==null){
            patientBasisTask.setScaleId(scaleId);
        }else{
            patientBasisTask.setScaleId(patientBasisTaskById.getScaleId()+","+scaleId);
        }
       int a=patientBasisTaskMapper.updatePatientBasisTask(patientBasisTask);
        if(patientBasisTask.getScaleId().length()==patientBasisTaskById.getTypeIds().length()){
            PatientBasisTask patientBasisTask1=new PatientBasisTask();
            patientBasisTask1.setTaskId(Long.parseLong(taskId));
            patientBasisTask1.setTaskStatus("3");
            patientBasisTask1.setDelFlag("1");
            patientBasisTaskMapper.updatePatientBasisTask(patientBasisTask1);
        }
        return a;
    }

    @Override
    public int updateIntermediateTask(String taskId, String scaleId) {
        PatientIntermediateTask patientIntermediateTaskById=patientIntermediateTaskMapper.selectPatientIntermediateTaskById(Long.parseLong(taskId));
        PatientIntermediateTask patientIntermediateTask=new PatientIntermediateTask();
        patientIntermediateTask.setTaskId(Long.parseLong(taskId));
        if("".equals(patientIntermediateTaskById.getScaleId()) ||patientIntermediateTaskById.getScaleId()==null){
            patientIntermediateTask.setScaleId(scaleId);
        }else{
            patientIntermediateTask.setScaleId(patientIntermediateTaskById.getScaleId()+","+scaleId);
        }
        int a=patientIntermediateTaskMapper.updatePatientIntermediateTask(patientIntermediateTask);
        if(patientIntermediateTask.getScaleId().length()==patientIntermediateTaskById.getTypeIds().length()){
            PatientIntermediateTask patientIntermediateTask1=new PatientIntermediateTask();
            patientIntermediateTask1.setTaskId(Long.parseLong(taskId));
            patientIntermediateTask1.setTaskStatus("3");
            patientIntermediateTask1.setDelFlag("1");
            patientIntermediateTaskMapper.updatePatientIntermediateTask(patientIntermediateTask1);
        }
        return a;
    }

    @Override
    public int updateAdvancedTask(String taskId, String scaleId) {
        PatientAdvancedTask patientAdvancedTaskById=patientAdvancedTaskMapper.selectPatientAdvancedTaskById(Long.parseLong(taskId));
        PatientAdvancedTask patientAdvancedTask=new PatientAdvancedTask();
        patientAdvancedTask.setTaskId(Long.parseLong(taskId));
        if("".equals(patientAdvancedTaskById.getScaleId()) ||patientAdvancedTaskById.getScaleId()==null){
            patientAdvancedTask.setScaleId(scaleId);
        }else{
            patientAdvancedTask.setScaleId(patientAdvancedTaskById.getScaleId()+","+scaleId);
        }
        int a=patientAdvancedTaskMapper.updatePatientAdvancedTask(patientAdvancedTask);
        if(patientAdvancedTask.getScaleId().length()==patientAdvancedTaskById.getTypeIds().length()){
            PatientAdvancedTask patientAdvancedTask1=new PatientAdvancedTask();
            patientAdvancedTask1.setTaskId(Long.parseLong(taskId));
            patientAdvancedTask1.setTaskStatus("3");
            patientAdvancedTask1.setDelFlag("1");
            patientAdvancedTaskMapper.updatePatientAdvancedTask(patientAdvancedTask1);
            /*================================================================================*/
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
            String[] arr=simpleDateFormat.format(new Date()).split("-");
           StatisticsTable1 statisticsTable1=statisticsTable1Mapper.selectStatisticsTable1ByYear(Long.parseLong(arr[0]));
            StatisticsTable1 statisticsTable11=new StatisticsTable1();
            statisticsTable11.setId(statisticsTable1.getId());
            statisticsTable11.setCompleteCount(statisticsTable1.getCompleteCount()+1);
            statisticsTable11.setRegisteredCount(statisticsTable1.getRegisteredCount());
            statisticsTable1Mapper.updateStatisticsTable1(statisticsTable11);
        }
        return a;
    }
}
