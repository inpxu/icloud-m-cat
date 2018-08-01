package com.zhiyun.interfaceImpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyun.base.dto.BaseResult;
import com.zhiyun.dao.IcloudApplicationentryDao;
import com.zhiyun.dao.IcloudUserApplicationentryDao;
import com.zhiyun.entity.IcloudApplicationentry;
import com.zhiyun.entity.IcloudUserApplicationentry;
import com.zhiyun.internal.server.icloud.constant.ErrorCode;
import com.zhiyun.internal.server.icloud.entity.IcloudUser;
import com.zhiyun.internal.server.icloud.service.UserInterface;
import com.zhiyun.liferay.constants.UserConstant;
import com.zhiyun.liferay.impl.OrganizationInvoker;
import com.zhiyun.liferay.impl.RoleInvoker;
import com.zhiyun.liferay.impl.UserInvoker;
import com.zhiyun.liferay.model.LiferayInvokerResult;
import com.zhiyun.liferay.util.ScreenNameConventer;
import com.zhiyun.util.EmailAddressConventer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Auther: sunyuntao
 * @Date: 2018/07/04 16:15
 * @Description:
 */
@Service("userInterface")
public class UserInterfaceImpl implements UserInterface {

    @Resource
    private UserInvoker userInvoker;

    @Resource
    private RoleInvoker roleInvoker;

    @Resource
    private OrganizationInvoker organizationInvoker;

    private static final String DEFAULT_PASSWORD = "123456";

    @Resource
    private IcloudApplicationentryDao icloudApplicationentryDao;

    @Resource
    private IcloudUserApplicationentryDao icloudUserApplicationentryDao;

    @Override
    public BaseResult<IcloudUser> getByPhone(String phone) {

        BaseResult<IcloudUser> baseResult = validGetByPhone(phone);

        if(!baseResult.getResult()){
            return baseResult;
        }

        LiferayInvokerResult result = userInvoker.getUserByScreenName(ScreenNameConventer.screenNamePlusP(phone));

        if(result.getResult() == null){
            baseResult.setResult(false);
            baseResult.setErrorCode(ErrorCode.UESR_NOT_EXIST);
            baseResult.setMessage("用户不存在");
            return baseResult;
        }

        IcloudUser icloudUser = new IcloudUser();
        JSONObject user = (JSONObject) result.getResult();


        icloudUser.setId(user.getLong("userId"));
        icloudUser.setEmailAddress(user.getString("emailAddress"));
        icloudUser.setPhone(phone);
        icloudUser.setName(user.getString("lastName") + user.getString("firstName"));
        IcloudApplicationentry icloudApplicationentry = icloudApplicationentryDao.findByUserId(user.getLong("userId"));
        if(icloudApplicationentry!= null){
            icloudUser.setCompanyId(icloudApplicationentry.getOrganizationId());
        }
        baseResult.setModel(icloudUser);

        return baseResult;
    }

    @Override
    public BaseResult<String> updatePassword(Long userId, String password) {

        BaseResult<String> baseResult = validUpdatePassword(userId,password);

        if(!baseResult.getResult()){
            return baseResult;
        }

        try {
            LiferayInvokerResult result = userInvoker.updatePassword(userId,
                    password == null ? DEFAULT_PASSWORD : password,
                    password == null ? DEFAULT_PASSWORD : password);

            if(result.getError() != null && !(((JSONObject)result.getError()).getInteger("code") == -32602)){
                baseResult.setResult(false);
                baseResult.setMessage("修改密码失败！");
            }
        }catch (Exception e){
            baseResult.setResult(false);
            baseResult.setMessage("网络异常请重试，请重试！");
        }

        return baseResult;
    }

    @Override
    public BaseResult<IcloudUser> addUser(IcloudUser icloudUser) {

        BaseResult<IcloudUser> baseResult = validAddUser(icloudUser);

        if(!baseResult.getResult()){
            return baseResult;
        }

        icloudUser.setEmailAddress(EmailAddressConventer.convert(icloudUser.getEmailAddress(),icloudUser.getPhone()));

        //手机邮箱检查
        baseResult = checkRegister(ScreenNameConventer.screenNamePlusP(icloudUser.getPhone()),icloudUser.getEmailAddress());

        if(!baseResult.getResult()){
            return baseResult;
        }

        //公司id检查
        IcloudApplicationentry icloudApplicationentry = icloudApplicationentryDao.findByOrganizationId(icloudUser.getCompanyId());

        if(icloudApplicationentry == null){
            baseResult.setResult(false);
            baseResult.setMessage("公司id不存在！");
            return baseResult;
        }

        try {
            //新增用户 组织id,角色一起增加
            LiferayInvokerResult result = userInvoker.addUser(new long[]{icloudUser.getCompanyId()},
                    ScreenNameConventer.screenNamePlusP(icloudUser.getPhone()),
                    icloudUser.getEmailAddress(),icloudUser.getName(),DEFAULT_PASSWORD,
                    new long[]{UserConstant.IC_ORDINARY_USER_ROLE_ID,UserConstant.IC_RELATED_ENTERPRISE_USER_ROLE_ID});

            System.out.println(JSON.toJSONString(result));

            long userId = ((JSONObject) result.getResult()).getLong("userId");
            if (null != result && null != result.getResult()) {
                userInvoker.updateReminderQuery(userId, String.valueOf(System.currentTimeMillis()),
                        String.valueOf(System.currentTimeMillis()));

            }

            //自定义库关联关系
            IcloudUserApplicationentry icloudUserApplicationentry = new IcloudUserApplicationentry();
            icloudUserApplicationentry.setUserId(userId);
            icloudUserApplicationentry.setApplicationentryId(icloudApplicationentry.getId());
            icloudUserApplicationentryDao.insert(icloudUserApplicationentry);

            icloudUser.setId(userId);
            baseResult.setModel(icloudUser);
        }catch (Exception e){
            baseResult.setResult(false);
            baseResult.setMessage("网络异常请重试，请重试！");
        }

        return baseResult;
    }


    private BaseResult<IcloudUser> checkRegister(String screenName,String emailAddress){

        BaseResult<IcloudUser> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("修改密码成功！");

        LiferayInvokerResult result = userInvoker.getUserByScreenName(screenName);

        if (result.getResult() != null) {
            baseResult.setResult(false);
            baseResult.setErrorCode(ErrorCode.PHONE_EXIST);
            baseResult.setMessage(ScreenNameConventer.screenNameMinusP(screenName) + "已被注册...");
            return baseResult;
        }

        result = userInvoker.getUserIdByEmailAddress(emailAddress);

        if(result.getResult() != null){
            baseResult.setResult(false);
            baseResult.setErrorCode(ErrorCode.EMAILADDRESS_EXIST);
            baseResult.setMessage(emailAddress + "已被注册...");
            return baseResult;
        }

        return baseResult;

    }

    @Override
    public BaseResult<IcloudUser> updateUser(IcloudUser icloudUser) {

        BaseResult<IcloudUser> baseResult = validUpdateUser(icloudUser);
        if(!baseResult.getResult()){
            return baseResult;
        }

        icloudUser.setEmailAddress(EmailAddressConventer.convert(icloudUser.getEmailAddress(),icloudUser.getPhone()));

        //公司id检查
        IcloudApplicationentry icloudApplicationentry = icloudApplicationentryDao.findByOrganizationId(icloudUser.getCompanyId());

        if(icloudApplicationentry == null){
            baseResult.setResult(false);
            baseResult.setMessage("公司id不存在！");
            return baseResult;
        }
        Long applicationEntryId = icloudApplicationentry.getId();

        icloudApplicationentry = icloudApplicationentryDao.findByUserId(icloudUser.getId());

        if(icloudApplicationentry!=null && !icloudUser.getCompanyId().equals(icloudApplicationentry.getOrganizationId())){
            baseResult.setResult(false);
            baseResult.setMessage("该员工已被其他公司绑定，请先解绑！");
            return baseResult;
        }

        checkUpdateData(ScreenNameConventer.screenNamePlusP(icloudUser.getPhone()),icloudUser.getEmailAddress(),icloudUser.getId(),baseResult);
        if(!baseResult.getResult()){
            return baseResult;
        }

        try {
            LiferayInvokerResult result = userInvoker.updateUser(
                    ScreenNameConventer.screenNamePlusP(icloudUser.getPhone()),
                    icloudUser.getEmailAddress(),icloudUser.getName(),new long[]{icloudUser.getCompanyId()},
                    new long[]{UserConstant.IC_ORDINARY_USER_ROLE_ID,UserConstant.IC_RELATED_ENTERPRISE_USER_ROLE_ID},icloudUser.getId());

            //自定义库关联关系
            IcloudUserApplicationentry icloudUserApplicationentry = new IcloudUserApplicationentry();
            icloudUserApplicationentry.setUserId(icloudUser.getId());
            icloudUserApplicationentry.setApplicationentryId(applicationEntryId);
            icloudUserApplicationentryDao.insert(icloudUserApplicationentry);

            baseResult.setModel(icloudUser);
        }catch (Exception e){
            baseResult.setResult(true);
            baseResult.setMessage("网络异常请重试！");
        }


        return baseResult;
    }

    public BaseResult<IcloudUser> checkUpdateData(String screenName, String emailAddress, Long userId,BaseResult<IcloudUser> baseResult){
        LiferayInvokerResult result = userInvoker.getUserByScreenName(screenName);
        if (result.getResult() != null && !((JSONObject)result.getResult()).getLong("userId").equals(userId)) {
            baseResult.setResult(false);
            baseResult.setErrorCode(ErrorCode.PHONE_EXIST);
            baseResult.setMessage(ScreenNameConventer.screenNameMinusP(screenName) + "已被注册...");
            return baseResult;
        }

        result = userInvoker.getUserIdByEmailAddress(emailAddress);
        if (result.getResult() != null && !result.getResult().equals(String.valueOf(userId))) {
            baseResult.setResult(false);
            baseResult.setErrorCode(ErrorCode.EMAILADDRESS_EXIST);
            baseResult.setMessage(emailAddress + "已被注册...");
            return baseResult;
        }
        return baseResult;
    }

    @RequestMapping("untieUser")
    @Override
    public BaseResult<IcloudUser> untieUser(Long userId) {

        BaseResult<IcloudUser> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("解绑成功！");

        IcloudApplicationentry icloudApplicationentry = icloudApplicationentryDao.findByUserId(userId);

        //创建人解绑
        boolean needDeleteOrg = false;
        if(userId.equals(icloudApplicationentry.getCreateUserId())){
            List<IcloudUserApplicationentry> icloudUserApplicationentries = icloudUserApplicationentryDao.listByApplicationentryId(icloudApplicationentry.getId());
            if(!CollectionUtils.isEmpty(icloudUserApplicationentries)){
                for(IcloudUserApplicationentry iua:icloudUserApplicationentries){
                    if(!iua.getUserId().equals(userId)){
                        baseResult.setResult(true);
                        baseResult.setMessage("请先解绑公司下所有员工!");
                        return baseResult;
                    }
                }
            }
            needDeleteOrg = true;
        }

        try {

            //解绑角色
            roleInvoker.deleteRoleUser(userId,UserConstant.IC_RELATED_ENTERPRISE_USER_ROLE_ID);

            //lferay 组织解绑
            if(icloudApplicationentry != null){
                userInvoker.unsetOrganizationUsers(icloudApplicationentry.getOrganizationId(),new Long[]{userId});
            }

            //自定义关联表解绑
            icloudUserApplicationentryDao.deleteByUserId(userId);

            if(needDeleteOrg){
                icloudApplicationentryDao.delete(icloudApplicationentry.getId());
                organizationInvoker.deleteOrganization(icloudApplicationentry.getOrganizationId());
            }

        }catch (Exception e){
            baseResult.setResult(false);
            baseResult.setMessage("解绑失败请重试！");
        }


        return baseResult;
    }

    private BaseResult<IcloudUser> validGetByPhone(String phone){

        BaseResult<IcloudUser> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("查询成功！");

        if(StringUtils.isEmpty(phone)){
            baseResult.setResult(false);
            baseResult.setMessage("电话不能为空！");
        }

        return baseResult;
    }

    private BaseResult<IcloudUser> validUpdateUser(IcloudUser icloudUser){

        BaseResult<IcloudUser> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("修改用户成功！");

        if(icloudUser.getId() == null){
            baseResult.setResult(false);
            baseResult.setMessage("用户id不能为空！");
            return baseResult;
        }

        if(icloudUser == null){
            baseResult.setResult(false);
            baseResult.setMessage("注册信息不能为空！");
        }

        if(icloudUser.getCompanyId()== null){
            baseResult.setResult(false);
            baseResult.setMessage("公司ID不能为空");
        }

        if(StringUtils.isEmpty(icloudUser.getPhone())){
            baseResult.setResult(false);
            baseResult.setMessage("手机号码不能为空！");
        }

        if(StringUtils.isEmpty(icloudUser.getName())){
            baseResult.setResult(false);
            baseResult.setMessage("用户姓名不能为空！");
        }

        return baseResult;

    }

    private BaseResult<IcloudUser> validAddUser(IcloudUser icloudUser){

        BaseResult<IcloudUser> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("新增用户成功！");

        if(icloudUser == null){
            baseResult.setResult(false);
            baseResult.setMessage("注册信息不能为空！");
        }

        if(icloudUser.getCompanyId()== null){
            baseResult.setResult(false);
            baseResult.setMessage("公司ID不能为空");
        }

        if(StringUtils.isEmpty(icloudUser.getPhone())){
            baseResult.setResult(false);
            baseResult.setMessage("手机号码不能为空！");
        }

        if(StringUtils.isEmpty(icloudUser.getName())){
            baseResult.setResult(false);
            baseResult.setMessage("用户姓名不能为空！");
        }

        return baseResult;

    }

    private BaseResult<String> validUpdatePassword(Long userId,String password){

        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setResult(true);
        baseResult.setMessage("修改密码成功！");

        if(userId == null){
            baseResult.setResult(false);
            baseResult.setMessage("用户id不能为空！");
        }

        return baseResult;
    }
}
