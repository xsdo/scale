package com.qx.eis.service.impl;

import com.qx.eis.domain.EisScaleQuestions;
import com.qx.eis.domain.EisVo;
import com.qx.eis.mapper.EisScaleQuestionsMapper;
import com.qx.eis.service.IEisScaleQuestionsService;
import com.qx.patient.util.ExportExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qq
 * @version 1.0
 * @date 2021/8/3 9:50
 */
@Service
public class EisScaleQuestionsServiceIml implements IEisScaleQuestionsService {

    private static final Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);

    @Autowired
    private EisScaleQuestionsMapper eisScaleQuestionsMapper;


    @Override
    public EisScaleQuestions selectEisScaleQuestionsById(Long id){
        return eisScaleQuestionsMapper.selectEisScaleQuestionsById(id);
    }

    @Override
    public EisScaleQuestions selectEisScaleQuestionsByTitle(String title){
        return eisScaleQuestionsMapper.selectEisScaleQuestionsByTitle(title);
    }

    @Override
    public List<EisScaleQuestions> selectEisScaleQuestionsByScaleId(Long scaleId){
        return eisScaleQuestionsMapper.selectEisScaleQuestionsByScaleId(scaleId);
    }

    @Override
    public int getQuestionsScore (List<EisVo> eisVoList){
        int score =0;
        for (EisVo eisVo:eisVoList){
            String answer =eisVo.getAnswer();
            String title =eisVo.getTitle();
            Long scaleId=eisVo.getScaleId();
            List<EisScaleQuestions> eisScaleQuestionsList =this.selectEisScaleQuestionsByScaleId(scaleId);
            for (int a=0;a<eisScaleQuestionsList.size();a++){
                EisScaleQuestions eisScaleQuestions=eisScaleQuestionsList.get(a);
                String  title2=eisScaleQuestions.getTitle();

                if (title==title2||title.equals(title2)){

                    if (answer=="answerA"||answer.equals("answerA")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerA());
                        score=score+x;
                    }else if (answer=="answerB"||answer.equals("answerB")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerB());
                        score=score+x;
                    }else if (answer=="answerC"||answer.equals("answerC")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerC());
                        score=score+x;
                    }else if (answer=="answerD"||answer.equals("answerD")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerD());
                        score=score+x;
                    }else if (answer=="answerE"||answer.equals("answerE")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerE());
                        score=score+x;
                    }else if (answer=="answerF"||answer.equals("answerF")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerF());
                        score=score+x;
                    }else if (answer=="answerG"||answer.equals("answerG")){
                        int x=Integer.parseInt(eisScaleQuestions.getAnswerG());
                        score=score+x;
                    }
                }
            }
        }
        return score;
    }

    @Override
    public int getScoreA(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==51){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("2".equals(title)||"3".equals(title)||"9".equals(title)||"12".equals(title)||"13".equals(title)||"16".equals(title)||"22".equals(title)||"23".equals(title)||"28".equals(title)||"30".equals(title)||"31".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==54){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("7".equals(title)||"8".equals(title)||"9".equals(title)||"10".equals(title)||"11".equals(title)||"12".equals(title)||"13".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("10".equals(title)||"11".equals(title)||"12".equals(title)||"13".equals(title)||"15".equals(title)||"17".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==56){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("1".equals(title)||"2".equals(title)||"3".equals(title)||"4".equals(title)||"5".equals(title)||"6".equals(title)||"7".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreB(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==51){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("7".equals(title)||"8".equals(title)||"10".equals(title)||"17".equals(title)||"20".equals(title)||"24".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==54){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("1".equals(title)||"2".equals(title)||"3".equals(title)||"4".equals(title)||"5".equals(title)||"6".equals(title)||"14".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("2".equals(title)||"3".equals(title)||"9".equals(title)||"20".equals(title)||"21".equals(title)||"22".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==56){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("8".equals(title)||"9".equals(title)||"10".equals(title)||"11".equals(title)||"12".equals(title)||"13".equals(title)||"14".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreC(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==51){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("4".equals(title)||"6".equals(title)||"11".equals(title)||"18".equals(title)||"19".equals(title)||"21".equals(title)||"26".equals(title)||"27".equals(title)||"29".equals(title)||"32".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("16".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==56){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("15".equals(title)
                            ||"16".equals(title)
                            ||"17".equals(title)
                            ||"18".equals(title)
                            ||"19".equals(title)
                            ||"20".equals(title)
                            ||"21".equals(title)
                            ||"22".equals(title)
                            ||"23".equals(title)
                            ||"24".equals(title)
                            ||"25".equals(title)
                            ||"26".equals(title)
                            ||"27".equals(title)
                            ||"28".equals(title)
                            ||"29".equals(title)
                            ||"30".equals(title)
                    ){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreD(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==51){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("1".equals(title)||"5".equals(title)||"15".equals(title)||"25".equals(title)||"33".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }else if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("18".equals(title)||"19".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreE(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("1".equals(title)||"7".equals(title)||"8".equals(title)||"14".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreF(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("4".equals(title)||"5".equals(title)||"6".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }
    @Override
    public int getScoreG(List<EisVo> eisVoList){
        int score =0;
        if (eisVoList.size()!=0&&eisVoList!=null){
            Long scaleId =eisVoList.get(0).getScaleId();
            if (scaleId==55){
                for (EisVo eisVo:eisVoList){
                    String answer =eisVo.getAnswer();
                    String title =eisVo.getTitle();
                    scaleId=eisVo.getScaleId();
                    if ("23".equals(title)||"24".equals(title)||"25".equals(title)){
                        score=score+this.getScore(answer,title,scaleId);
                    }
                }
            }
        }

        return score;
    }



    public int getScore(String answer,String title,Long scaleId){
        int score=0;
        List<EisScaleQuestions> eisScaleQuestionsList =this.selectEisScaleQuestionsByScaleId(scaleId);
        for (int a=0;a<eisScaleQuestionsList.size();a++){
            EisScaleQuestions eisScaleQuestions=eisScaleQuestionsList.get(a);
            String  title2=eisScaleQuestions.getTitle();

            if (title==title2||title.equals(title2)){

                if (answer=="answerA"||answer.equals("answerA")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerA());
                    score=score+x;
                }else if (answer=="answerB"||answer.equals("answerB")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerB());
                    score=score+x;
                }else if (answer=="answerC"||answer.equals("answerC")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerC());
                    score=score+x;
                }else if (answer=="answerD"||answer.equals("answerD")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerD());
                    score=score+x;
                }else if (answer=="answerE"||answer.equals("answerE")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerE());
                    score=score+x;
                }else if (answer=="answerF"||answer.equals("answerF")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerF());
                    score=score+x;
                }else if (answer=="answerG"||answer.equals("answerG")){
                    int x=Integer.parseInt(eisScaleQuestions.getAnswerG());
                    score=score+x;
                }
            }
        }
        return score;
    }
}
