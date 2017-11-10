package com.freetax.mybatis.questionsAndAnswers.mapper;

import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;

public interface QuestionsAndAnswersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionsAndAnswers record);

    int insertSelective(QuestionsAndAnswers record);

    QuestionsAndAnswers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionsAndAnswers record);

    int updateByPrimaryKey(QuestionsAndAnswers record);
}