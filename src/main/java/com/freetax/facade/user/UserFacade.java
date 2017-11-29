package com.freetax.facade.user;

import com.freetax.common.Response;
import com.freetax.common.constant.MsgCodeConstant;
import com.freetax.exception.AuthException;
import com.freetax.mybatis.user.entity.LoginUser;
import com.freetax.mybatis.user.entity.User;
import com.freetax.mybatis.user.entity.UserVo;
import com.freetax.mybatis.user.service.UserService;
import com.freetax.utils.pagination.model.Paging;
import com.freetax.utils.propertiesLoader.PropertiesLoader;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP用户 facade
 *
 * @Author shuxf
 * @Date 2017/10/30 20:20
 */
@Service
public class UserFacade {

    private static Logger log = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    private UserService userService;

    /**
     * 根据token获取app登录用户信息
     *
     * @param parammap
     * @return
     */
    public LoginUser getLoginUserByToken(Map<String, Object> parammap) {

        LoginUser loginUser = userService.selectLoginUserByToken(parammap);
        if (null == loginUser) {
            throw new AuthException(MsgCodeConstant.app_user_not_exist_with_this_token, "用户名密码错误或用户不存在");
        }
        return loginUser;
    }

    /**
     * 根据用户id获取Loginuser
     *
     * @param mobile
     * @return
     */
    public LoginUser getLoginuserByUserid(String mobile) {
        return userService.selectLoginuserByUserid(mobile);
    }

    /**
     * 修改会员用户信息
     *
     * @param phone
     * @param photo
     * @param name
     * @param company
     * @param email
     * @param infosource
     */
    public void updateUser(String id, String phone, String photo, String password, String name, String company, String email, String infosource) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        if (StringUtils.isNotEmpty(phone)) {
            user.setPhone(phone);
        }
        if (StringUtils.isNotEmpty(photo)) {
            user.setPhoto(photo);
        }
        if (StringUtils.isNotEmpty(password)){
            user.setPasswd(password);
        }
        if (StringUtils.isNotEmpty(name)) {
            user.setName(name);
        }
        if (StringUtils.isNotEmpty(company)) {
            user.setCompany(company);
        }
        if (StringUtils.isNotEmpty(email)) {
            user.setEmail(email);
        }
        if (StringUtils.isNotEmpty(infosource)) {
            user.setInfosource(Integer.parseInt(infosource));
        }
        userService.updateUser(user);
    }

    /**
     * 根据id查询会员用户详情
     *
     * @param id
     * @return
     */
    public User queryUserById(String id) {
        return userService.queryUserById(Integer.parseInt(id));
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public void deleteUserById(String id) {
        userService.deleteUserById(Integer.parseInt(id));
    }

    /**
     * 更新用户联系状态
     *
     * @param id
     */
    public void updateUserByMark(String id, String mark) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setMark(Integer.parseInt(mark));
        userService.updateUserByMark(user);
    }

    public List<User> queryUserByList(String phone, String name, String company, String infosource, String mark,
                                      String logbegin, String logend, String advicebiegin, String adviceend, Paging<User> pag,
                                      String intime) {
        UserVo user = new UserVo();
        if (StringUtils.isNotEmpty(phone)){
            user.setPhone(phone);
        }
        if (StringUtils.isNotEmpty(name)){
            user.setName(name);
        }
        if (StringUtils.isNotEmpty(company)){
            user.setCompany(company);
        }
        if (StringUtils.isNotEmpty(infosource)){
            user.setInfosource(Integer.parseInt(infosource));
        }
        if (StringUtils.isNotEmpty(mark)){
            user.setMark(Integer.parseInt(mark));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //时间操作
        /*map.put("end",endTime);
        map.put("begin",beginTime);*/
        Map<String,Date> logmap = getTime(logbegin, logend,format);
        if (logmap != null){
            user.setLogEnd(logmap.get("end"));
            user.setLogBegin(logmap.get("begin"));
        }
        //时间操作
        Map<String,Date> advmap = getTime(advicebiegin, adviceend,format);
        if (advmap != null){
            user.setAdviceEnd(advmap.get("end"));
            user.setAdviceBegin(advmap.get("begin"));
        }
        //分解注册时间
        String registerin = "";
        String registerend = "";
        if (StringUtils.isNotEmpty(intime)) {
            intime = intime.replaceAll(" ","");
            String[] times = intime.split(",");
            for (int j = 0; j < times.length; j++) {
                if (j == 0) {
                    registerin = times[j];
                } else if (j == 1) {
                    registerend = times[j];
                }
            }

        }
        //注册时间的时间操作
        Map<String,Date> regmap = getTime(registerin, registerend, format);
        if (regmap != null){
            user.setRegisterend(regmap.get("end"));
            user.setRegisterin(regmap.get("begin"));
        }

        return userService.queryUserByList(user,pag);

    }

    /**
     * 获取时间
     */
    private Map<String,Date> getTime(String begin, String end, SimpleDateFormat logformat) {
        Date beginTime;
        Date endTime;
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(begin) && StringUtils.isNotEmpty(end)){
            try {
                beginTime = logformat.parse(begin);
                endTime = logformat.parse(end);
                map.put("end",endTime);
                map.put("begin",beginTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    /**
     * 修改用户个人资料
     * @param id
     * @param name
     * @param photo
     * @param company
     * @param email
     * @return
     */
    public Response updatePersonInfo(String id, String photo, String name, String company, String email){
        Response response = new Response();

        try {
            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setPhoto(photo);
            user.setName(name);
            user.setCompany(company);
            user.setEmail(email);
            userService.updatePersonInfo(user);
            response.setCode(200);
            response.setMessage("修改成功");
        }catch (Exception e){
            response.setCode(300);
            response.setMessage("修改失败");
            throw e;
        }

        return response;
    }

    /**
     * 上传头像图片
     * @param file
     * @return
     */
    public Response uploadPhotoImg(HttpServletRequest request, MultipartFile file) throws IOException {
        Response response = new Response();

        if (file != null){//判断上传的文件是否为空

            String path;// 文件路径
            String type;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            log.info("上传的文件原名称:"+fileName);

            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;

            if (type!=null){// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {

                    //PC访问时的服务器前缀路径
                    String urlsuf = PropertiesLoader.getValue("urlsuf");
                    // 项目在容器中实际发布运行的根路径
                    String realPath = PropertiesLoader.getValue("realPath");
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis()+"."+type);
                    // 设置存放图片文件的路径
                    path=realPath+trueFileName;
                    log.info("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    log.info("上传成功");
                    response.setCode(200);
                    response.setMessage("上传成功");
                    response.setData(urlsuf + trueFileName);

                }else {
                    log.info("系统不支持该文件类型,请重新上传");
                    response.setCode(201);
                    response.setMessage("系统不支持该文件类型,请重新上传");
                }
            }else{
                log.info("文件类型为空，请重新选择");
                response.setCode(202);
                response.setMessage("文件类型为空，请重新选择");
            }

        }else {
            log.info("未找到对应的文件，请选择图片文件");
            response.setCode(203);
            response.setMessage("未找到对应的文件，请选择图片文件");
        }

        return response;
    }
}