package com.mingchang.BlogService.controller;

import com.mingchang.BlogService.model.BaseModel;
import com.mingchang.BlogService.model.LoginOutput;
import com.mingchang.BlogService.model.database.AdminInfo;
import com.mingchang.BlogService.model.request.LoginBackendRequest;
import com.mingchang.BlogService.service.BlogService;
import com.mingchang.BlogService.utility.TimerUtility;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/")
    public String directBrowse() {
        return "Seems you're trying to browse the service directly from your client, this is not a good idea. Go check my webpage: https://mingchang137.su";
    }

    @RequestMapping(value = "/login_backend", produces = "application/json")
    public @ResponseBody
    BaseModel loginBackend(HttpSession session,
                           HttpServletResponse resp, @RequestBody LoginBackendRequest loginBackendRequest) {

        resp.setHeader("Access-Control-Allow-Origin", "*");

        BaseModel respLogin = new BaseModel();
        LoginOutput result = blogService.login(loginBackendRequest);
        Date dateNow = new Date();
        Date dateAllowTime = new Date();
        int attempt = 0;

        // 已經有登入失敗的記錄，把記錄寫回去
        if (session.getAttribute("failedAttempt") != null) {
            attempt = (Integer) session.getAttribute("failedAttempt");

            // 如果登入失敗已經超過三次，把停用時間寫回去
            if ((Integer) session.getAttribute("failedAttempt") >= 3) {
                dateAllowTime.setTime((Long) session.getAttribute("allowTime"));
            }
        }

        // 不在被停用的時段中登入
        if (dateAllowTime.getTime() <= dateNow.getTime()) {

            // 登入成功
            if (result != null) {
                String sessionId = session.getId();
                result.setSessionId(sessionId);
                respLogin.setResult(true);
                respLogin.setData(result);
                respLogin.setMessage("登入成功");

                AdminInfo adminInfo = result.getAdminInfo();
                String strtp = String.valueOf(TimerUtility.getNowDate().getTime());
                session.setAttribute("time_key", strtp);
                session.setAttribute("loginSuccess", "yes");
                session.setAttribute("pass_id", adminInfo.getAdminId());
                session.setAttribute("adminInfo", adminInfo);
                session.setAttribute("serviceKey", result.getSecurityKey());
                session.removeAttribute("failedAttempt");

            }

            // 登入失敗
            else {
                attempt++;
                session.setAttribute("failedAttempt", attempt);

                // 登入失敗次數三次
                if (attempt == 3) {
                    // 設定停用時間
                    dateAllowTime.setTime(dateNow.getTime() + 30000); //900000
                    respLogin.setResult(false);
                    respLogin.setMessage("登入功能已被停用，請於" + dateAllowTime + "後重試");
                    session.setAttribute("allowTime", dateAllowTime.getTime());
                } else {
                    // 登入失敗次數超過3要重設
                    if (attempt > 3) {
                        attempt = 1;
                        session.setAttribute("failedAttempt", attempt);
                    }
                    respLogin.setResult(false);
                    respLogin.setMessage("帳號或密碼輸入錯誤，您還有" + (3 - attempt) + "次嘗試機會");
                }
            }
        }

        // 在被停用的時段中登入
        else {
            respLogin.setResult(false);
            respLogin.setMessage("登入功能已被停用，請於" + dateAllowTime + "後重試");
        }

        //回傳
        return respLogin;
    }

}
