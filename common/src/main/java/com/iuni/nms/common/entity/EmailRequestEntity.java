package com.iuni.nms.common.entity;

/**
 * @author zowie
 *         Email: nicholas@iuni.com
 */
public class EmailRequestEntity {

    /**
     * 邮件地址
     */
    private String email;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 邮件来源，GIONEE、IUNI
     */
    private String emailSource;

    public EmailRequestEntity(String email, String title, String content, String emailSource) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.emailSource = emailSource;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailSource() {
        return emailSource;
    }

    public void setEmailSource(String emailSource) {
        this.emailSource = emailSource;
    }
}
