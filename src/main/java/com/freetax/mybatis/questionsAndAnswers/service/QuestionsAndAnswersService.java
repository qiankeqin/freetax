package com.freetax.mybatis.questionsAndAnswers.service;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import com.freetax.mybatis.questionsAndAnswers.mapper.QuestionsAndAnswersMapper;
import com.freetax.utils.pagination.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 修改QA问答
     * @param andAnswers
     */
    public void updateQuestionsAndAnswers(QuestionsAndAnswers andAnswers){
        try {
            log.info("修改QA问答");
            questionsAndAnswersMapper.updateByPrimaryKeySelective(andAnswers);
        } catch (Exception e) {
            log.error("修改QA问答异常",e);
            throw e;
        }
    }


    /**
     * 删除QA问答
     * @param id
     */
    public void deleteQuestionsAndAnswers(Integer id){
        try {
            log.info("删除QA问答");
            questionsAndAnswersMapper.deleteQuestionsAndAnswers(id);
        } catch (Exception e) {
            log.error("删除QA问答异常",e);
            throw e;
        }
    }

    /**
     * 条件查询QA问答列表
     * @param andAnswers
     * @param pag
     * @return
     */
    public List<QuestionsAndAnswers> queryQAList(QuestionsAndAnswers andAnswers , Paging<QuestionsAndAnswers> pag){
        try {
            log.info("条件查询QA问答列表");
            return questionsAndAnswersMapper.findAllQueryQAList(andAnswers,pag.getRowBounds());
        } catch (Exception e) {
            log.error("条件查询QA问答列表",e);
            throw e;
        }
    }
}
