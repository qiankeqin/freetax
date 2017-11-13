package com.freetax.mybatis.questionsAndAnswers.mapper;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsAndAnswersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionsAndAnswers record);

    int insertSelective(QuestionsAndAnswers record);

    QuestionsAndAnswers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionsAndAnswers record);

    int updateByPrimaryKey(QuestionsAndAnswers record);

    void deleteQuestionsAndAnswers(Integer id);

    List<QuestionsAndAnswers> findAllQueryQAList(QuestionsAndAnswers andAnswers, RowBounds rowBounds);

    QuestionsAndAnswers queryQAById(Integer id);
}