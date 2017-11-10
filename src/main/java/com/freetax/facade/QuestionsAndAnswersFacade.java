package com.freetax.facade;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import com.freetax.mybatis.questionsAndAnswers.service.QuestionsAndAnswersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2017/11/10 17:20
 */
@Service
public class QuestionsAndAnswersFacade {

    @Autowired
    private QuestionsAndAnswersService questionsAndAnswersService;

    public void insertQuestionsAndAnswers(String ask,String answer,String number){
        QuestionsAndAnswers andAnswers = new QuestionsAndAnswers();
        andAnswers.setAnswer(ask);
        andAnswers.setAnswer(answer);
        if (StringUtils.isNotEmpty(number)) {
            andAnswers.setNumber(Integer.parseInt(number));
        }
        questionsAndAnswersService.insertQuestionsAndAnswers(andAnswers);
    }
}
