package com.freetax.controller.pc;

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
 * @Date 2017/11/17 17:19
 */
@RestController
@RequestMapping("/pc/qa")
public class PcQAController {

    @Autowired
    private QuestionsAndAnswersFacade questionsAndAnswersFacade;

    /**
     * pc查询问答列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "pc查询问答列表",notes = "pc查询问答列表",response = Response.class)
    @RequestMapping(value = "queryQuestionsAndAnswers",method = RequestMethod.POST)
    public Response queryQuestionsAndAnswers(@ApiParam(value = "当前页")@RequestParam(defaultValue = "1") String pageNo,
                                             @ApiParam(value = "每页几条")@RequestParam(defaultValue = "10") String pageSize){
        Response response = new Response();
        Paging<QuestionsAndAnswers> pag = new Paging<QuestionsAndAnswers>(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        List<QuestionsAndAnswers> list = questionsAndAnswersFacade.queryQAList(null,null,pag);
        pag.result(list);
        response.setMessage("查询成功");
        response.setData(pag);
        return response;
    }
}
