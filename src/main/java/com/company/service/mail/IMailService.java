package com.company.service.mail;


public interface IMailService {

    void sendEmail(String toAccount, String subject, String text);

    void sendEmailMine(String toAccount, String subject, String text);
}
