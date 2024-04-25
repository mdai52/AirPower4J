package cn.hamm.airpower.util;

import cn.hamm.airpower.config.MessageConstant;
import cn.hamm.airpower.enums.Result;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * <h1>邮件助手类</h1>
 *
 * @author Hamm.cn
 */
@Component
public class EmailUtil {

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username: ''}")
    private String mailFrom;

    /**
     * <h2>发送邮件验证码</h2>
     *
     * @param email 邮箱
     * @param title 标题
     * @param code  验证码
     * @param sign  签名
     */
    public final void sendCode(String email, String title, String code, String sign) throws MessagingException {
        String content = """
                <div style='border-radius:20px;padding: 20px;background-color:#f5f5f5;color:#333;display:inline-block;'>
                <div style='font-size:24px;font-weight:bold;color:orangered;'>
                """ + code + """
                </div>
                <div style='margin-top:20px;font-size:16px;font-weight:300'>
                    上面是你的验证码，请注意不要转发给他人，五分钟内有效，请尽快使用。
                </div>
                <div style='margin-top:10px;font-size:12px;color:#aaa;font-weight:300'>
                """ + sign + """
                </div></div>
                """;
        sendEmail(email, title, content);
    }

    /**
     * <h2>发送邮件</h2>
     *
     * @param email   邮箱
     * @param title   标题
     * @param content 内容
     */
    public final void sendEmail(String email, String title, String content) throws MessagingException {
        Result.EMAIL_ERROR.whenNull(javaMailSender, MessageConstant.MAIL_SERVER_CONFIG_MISSING);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject(title);
        helper.setFrom(mailFrom);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}