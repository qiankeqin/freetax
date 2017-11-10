package com.freetax.mybatis.questionsAndAnswers.service;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import com.freetax.mybatis.questionsAndAnswers.mapper.QuestionsAndAnswersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2017/11/10 17:24
 */
@Service
public class QuestionsAndAnswersService {

    private static Logger log = LoggerFactory.getLogger(QuestionsAndAnswersService.class);

    @Autowired
    private QuestionsAndAnswersMapper questionsAndAnswersMapper;

    /**
     * 新增QA问答
     * @param questionsAndAnswers
     */
    public void insertQuestionsAndAnswers(QuestionsAndAnswers questionsAndAnswers){
        try {
            questionsAndAnswersMapper.insertSelective(questionsAndAnswers);
            log.info("新增问答操作成功");
        } catch (Exception e) {
            log.error("新增问答操作失败",e);
            throw e;
        }
    }
}
