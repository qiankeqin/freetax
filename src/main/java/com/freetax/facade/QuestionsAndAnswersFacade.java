package com.freetax.facade;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import com.freetax.mybatis.questionsAndAnswers.service.QuestionsAndAnswersService;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/10 17:20
 */
@Service
public class QuestionsAndAnswersFacade {

    @Autowired
    private QuestionsAndAnswersService questionsAndAnswersService;

    /**
     * 新增QA问答
     * @param ask
     * @param answer
     * @param number
     */
    public void insertQuestionsAndAnswers(String ask,String answer,String number){
        QuestionsAndAnswers andAnswers = new QuestionsAndAnswers();
        andAnswers.setAsk(ask);
        andAnswers.setAnswer(answer);
        if (StringUtils.isNotEmpty(number)) {
            andAnswers.setNumber(Integer.parseInt(number));
        }
        andAnswers.setIntime(new Date());
        andAnswers.setIsdel(0);
        questionsAndAnswersService.insertQuestionsAndAnswers(andAnswers);
    }

    /**
     * 修改QA问答
     * @param id
     * @param ask
     * @param answer
     * @param number
     */
    public void updateQuestionsAndAnswers(String id,String ask,String answer,String number){
        QuestionsAndAnswers questionsAndAnswers = new QuestionsAndAnswers();
        if (StringUtils.isNotEmpty(id)){
            questionsAndAnswers.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(ask)){
            questionsAndAnswers.setAsk(ask);
        }
        if (StringUtils.isNotEmpty(answer)){
            questionsAndAnswers.setAnswer(answer);
        }
        if (StringUtils.isNotEmpty(number)){
            questionsAndAnswers.setNumber(Integer.parseInt(number));
        }
        questionsAndAnswersService.updateQuestionsAndAnswers(questionsAndAnswers);
    }


    /**
     * 删除QA问答
     * @param id
     */
    public void deleteQuestionsAndAnswers(String id){
        questionsAndAnswersService.deleteQuestionsAndAnswers(Integer.parseInt(id));
    }

    /**
     * 查询QA列表
     * @param ask
     * @param answer
     * @param pag
     * @return
     */
    public List<QuestionsAndAnswers> queryQAList(String ask, String answer, Paging<QuestionsAndAnswers> pag){
        QuestionsAndAnswers andAnswers = new QuestionsAndAnswers();
        if (StringUtils.isNotEmpty(ask)){
            andAnswers.setAsk(ask);
        }
        if (StringUtils.isNotEmpty(answer)){
            andAnswers.setAnswer(answer);
        }
        return questionsAndAnswersService.queryQAList(andAnswers,pag);
    }


}
