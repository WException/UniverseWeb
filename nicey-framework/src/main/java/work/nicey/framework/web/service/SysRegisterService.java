package work.nicey.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.nicey.common.constant.CacheConstants;
import work.nicey.common.constant.Constants;
import work.nicey.common.constant.UserConstants;
import work.nicey.common.core.domain.entity.SysUser;
import work.nicey.common.core.domain.model.RegisterBody;
import work.nicey.common.core.redis.RedisCache;
import work.nicey.common.exception.user.CaptchaException;
import work.nicey.common.exception.user.CaptchaExpireException;
import work.nicey.common.utils.MessageUtils;
import work.nicey.common.utils.SecurityUtils;
import work.nicey.common.utils.StringUtils;
import work.nicey.common.utils.sign.Md5Utils;
import work.nicey.framework.manager.AsyncManager;
import work.nicey.framework.manager.factory.AsyncFactory;
import work.nicey.system.service.ISysConfigService;
import work.nicey.system.service.ISysUserService;

import java.util.Random;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    private final String SALT = "iupqwfdb";

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            // 生成 accessKey 和 secretKey
            Random random = new Random();
            String accessKey = Md5Utils.hash(username + SALT + random.nextInt(90000));
            String secretKey = Md5Utils.hash(username + SALT + random.nextInt(9000000));
            sysUser.setAccessKey(accessKey);
            sysUser.setSecretKey(secretKey);
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
