package com.illusionsolutions.notification;

//import com.sendgrid.Email;
//import com.sendgrid.Mail;
//import com.sendgrid.SendGrid;
import com.sendgrid.*;
import java.io.IOException;

import java.io.IOException;

public class Notification
{
    private String sender;
    private String recipient;
    private String subject;
    private String message;
    private SendGrid sendgrid = new SendGrid("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");

    public Notification()
    {
        sender  = "notifications@power-cloud.tech";
        recipient = "";
        subject = "PowerCloud Notification";
        message = "";
    }

    String getRecipient()
    {
        return recipient;
    }

    void setRecipient(String r)
    {
        this.recipient = r;
    }

    String getMessage()
    {
        return message;
    }

    void setMessage(String m)
    {
        this.message = m;
    }

    void sendNotification() throws IOException
    {
        Email from = new Email(sender);
        Email to = new Email(recipient);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();

        try
        {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sendgrid.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }
}
