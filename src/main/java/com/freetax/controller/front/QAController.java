package com.freetax.controller.front;

import com.freetax.common.Response;
import com.freetax.facade.QuestionsAndAnswersFacade;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhurui
 * @Date 2017/11/10 16:43
 */
@RestController
@RequestMapping("/front/QA")
public class QAController {

    @Autowired
    private QuestionsAndAnswersFacade questionsAndAnswersFacade;

    /**
     * 新增QA问答
     * @param ask
     * @param answer
     * @param number
     * @return
     */
    @RequestMapping(value = "/insertQA",method = RequestMethod.POST)
    @ApiOperation(value = "新增问答",notes = "用于新增问答接口",response = Response.class)
    public Response insertQuestionsAndAnswers(@ApiParam(value = "问题")@RequestParam String ask,
                                              @ApiParam(value = "答案")@RequestParam String answer,
                                              @ApiParam(value = "优先级 由低到高")@RequestParam String number){
        Response response = new Response();
        questionsAndAnswersFacade.insertQuestionsAndAnswers(ask,answer,number);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }
}
