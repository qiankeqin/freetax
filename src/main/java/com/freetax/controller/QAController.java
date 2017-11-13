package com.freetax.controller;

import com.freetax.common.Response;
import com.freetax.facade.QuestionsAndAnswersFacade;
import com.freetax.mybatis.questionsAndAnswers.entity.QuestionsAndAnswers;
import com.freetax.utils.pagination.model.Paging;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 修改QA问答
     * @param id
     * @param ask
     * @param answer
     * @param number
     * @return
     */
    @RequestMapping(value = "/updateQA",method = RequestMethod.POST)
    @ApiOperation(value = "修改QA问答",notes = "修改QA问答",response = Response.class)
    public Response updateQuestionsAndAnswers(@ApiParam(value = "id")@RequestParam String id,
                                              @ApiParam(value = "问题")@RequestParam(required = false) String ask,
                                              @ApiParam(value = "答案")@RequestParam(required = false) String answer,
                                              @ApiParam(value = "优先级")@RequestParam(required = false) String number){
        Response response = new Response();
        questionsAndAnswersFacade.updateQuestionsAndAnswers(id,ask,answer,number);
        response.setMessage("操作成功");
        response.setData(1);
        return response;
    }

    /**
     * 删除QA问答
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteQA",method = RequestMethod.POST)
    @ApiOperation(value = "删除QA问答",notes = "用于根据id删除QA问答",response = Response.class)
    public Response deleteQuestionsAndAnswers(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        questionsAndAnswersFacade.deleteQuestionsAndAnswers(id);
        response.setMessage("操作成功");
        response.setData("1");
        return response;
    }

    /**
     *条件查询QA问答列表
     *
     * @param ask
     * @param answer
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryQAByList",method = RequestMethod.POST)
    @ApiOperation(value = "条件查询QA列表",response = Response.class)
    public Response queryQAList(@ApiParam(value = "问题")@RequestParam(required = false) String ask,
                                @ApiParam(value = "答案")@RequestParam(required = false) String answer,
                                @ApiParam(value = "当前页")@RequestParam(defaultValue = "1") String pageNo,
                                @ApiParam(value = "每页几条")@RequestParam(defaultValue = "10") String pageSize){
        Response response = new Response();
        Paging<QuestionsAndAnswers> pag = new Paging<QuestionsAndAnswers>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<QuestionsAndAnswers> list = questionsAndAnswersFacade.queryQAList(ask,answer,pag);
        pag.result(list);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }

    /**
     * 查询问答详情
     * @param id
     * @return
     */
    @ApiOperation(value = "查询问答详情",notes = "查询问答详情",response = Response.class)
    @RequestMapping(value = "queryQAById",method = RequestMethod.POST)
    public Response queryQAById(@ApiParam(value = "id")@RequestParam String id){
        Response response = new Response();
        QuestionsAndAnswers andAnswers = questionsAndAnswersFacade.queryQAById(id);
        response.setMessage("查询成功");
        response.setData(andAnswers);
        return response;
    }
}
