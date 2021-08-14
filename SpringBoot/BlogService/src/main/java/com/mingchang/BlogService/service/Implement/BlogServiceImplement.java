package com.mingchang.BlogService.service.Implement;

import com.mingchang.BlogService.database.AdminInfoMapper;
import com.mingchang.BlogService.model.LoginOutput;
import com.mingchang.BlogService.model.database.AdminInfo;
import com.mingchang.BlogService.model.request.LoginBackendRequest;
import com.mingchang.BlogService.service.BlogService;
import com.mingchang.BlogService.utility.EncryptUtility;
import com.mingchang.BlogService.utility.LogUtility;
import com.mingchang.BlogService.utility.TimerUtility;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BlogServiceImplement implements BlogService {

    final
    AdminInfoMapper adminInfoMapper;

    public BlogServiceImplement(AdminInfoMapper adminInfoMapper) {
        this.adminInfoMapper = adminInfoMapper;
    }

    @Override
    public LoginOutput login(LoginBackendRequest loginBackendRequest) {
        LoginOutput loginOutput = null;

        try {
            String md5Code = EncryptUtility.digest(loginBackendRequest.getPassword());
            try {
                loginBackendRequest.setPassword(md5Code);
                AdminInfo adminInfo = adminInfoMapper.inspectPwd(loginBackendRequest);
                if (adminInfo != null) {
                    System.out.println(adminInfo.getAdminId());
                    loginOutput = new LoginOutput();
                    Date currentDate = TimerUtility.getNowDate();
                    SimpleDateFormat sdf = TimerUtility.getSimpleDateFormat("yyyyMMdd");
                    loginOutput.setSecurityKey(sdf.format(currentDate));
                    loginOutput.setAdminInfo(adminInfo);
                }
            } catch (Exception e) {
                LogUtility.error(this.getClass(), "loginBackend has error :" + e);
            }
        } catch (Exception e) {
            LogUtility.error(getClass(), "loginBackend function encrypt md5Code has error : " + e);
        }
        return loginOutput;
    }

}
