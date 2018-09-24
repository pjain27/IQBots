package api.utility;

import com.aventstack.extentreports.ExtentTest;
import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.mail.Flags.Flag;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static api.common.GlobalVariables.*;



public class EmailActions extends Utility{
    private Store store;
    private Folder folder, folderInbox;
    /**
     * Helper method that sends out the email with the given parameters.
     */
    public static void sendEmailWithAttachments(InputStream inputStream, ExtentTest logTest) throws IOException {
        try {

            String from = "donotreply@automationanywhere.com";
            String toAddress = "vijay.bhusri@automationanywhere.com";
            String dateSuffix = new SimpleDateFormat("MMM_dd_YYYY").format(new Date());
            String fileName = "TargetExcelFile" + dateSuffix + ".xlsx";
            // SMTP info
            String SMTP_SERVER = "fastmail.automationanywhere.com";
            String subject = "Target Scrapper Excel Sheet";
            String message = "PFA attachments";
            // sets SMTP server properties
            Properties props = System.getProperties();
            props.put("mail.smtp.host", SMTP_SERVER);
            props.put("mail.smtp.auth", "false");
            Session session = Session.getInstance(props, null);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
            msg.setSubject(subject);
            msg.setText("Hi");
            msg.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(inputStream, "application/vnd.ms-excel");
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(dataSource));
            attachment.setFileName(fileName);
            multipart.addBodyPart(attachment);
            msg.setContent(multipart);
            // sends the e-mail
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect();
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (Exception e) {
            log4j.error("sendEmailWithAttachments method - ERROR - " + e);
            logException(logTest, "sendEmailWithAttachments method - ERROR", e);
        }
    }

    /**
     * @ActionName: sendEmailWithReportLink: send email with extend report file path to user after running test suite
     * @param reportPath
     * @param logTest - Extend report
     */
    public static void sendEmailReport(String reportPath, ExtentTest logTest) throws IOException {
        try {

            log4j.info("Send email with report link after suite:");
            logInfo(logTest,  "Send email with report link after suite:");
            String subject;
            if(totalTestsRan == totalTestsPassed)
                subject = "API Automation Results" + TESTING_TYPE + " - PASS";
            else
                subject = "API Automation Results" + TESTING_TYPE + " - FAIL";


            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("qa.cognitive@gmail.com","Automation@123");
                        }
                    });

            log4j.info("Create a new email message");
            logInfo(logTest,  "Create a new email message");

            try {
                // Create a new email message
                Message msg = new MimeMessage(session);

                //From
                msg.setFrom(new InternetAddress(from));

                // To
                //for(int i=0;i<to.length;i++){
                msg.addRecipient(Message.RecipientType.TO, new
                        InternetAddress(to));
                //}
                // Set Subject
                msg.setSubject(subject);

                // creates message part
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent("Automation report: <br /> -  Total tests: " + totalTestsRan + " <br /> -  Pass: " + totalTestsPassed + " <br /> -  Fail: " + totalTestsFailed + "<br /><br /> For a detailed report, please download attachment and open in a browser.", "text/html");

                // creates multi-part
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(reportPath);
                multipart.addBodyPart(attachPart);

                msg.setContent(multipart);


                log4j.info("Open SMTP connection and send the e-mail");
                logInfo(logTest, "Open SMTP connection and send the e-mail");
                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
                t.connect();
                t.sendMessage(msg, msg.getAllRecipients());
                t.close();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        catch(Exception e){
            log4j.error("sendEmailReport method - ERROR" + e);
            logException(logTest, "sendEmailReport method - ERROR", e);
        }
    }


    /**
     * Delete all messages
     *
     * @throws Exception
     */
    public void deleteAllEmail(ExtentTest logTest) throws Exception {
        try {
            log4j.info("Delete all emails from Inbox - Start");

            logInfo(logTest, "Delete all emails after suite:");
            deleteAllEmailAtFolder("Inbox", logTest);

            log4j.info("Delete all emails from Inbox - End");
        } catch(Exception e){
            log4j.error("deleteAllEmail method - ERROR" + e);
            logException(logTest, "deleteAllEmail method - ERROR", e);
        }
    }

    /**
     * Delete all messages in a specified folder
     *
     * @throws Exception
     *
     */
    public void deleteAllEmailAtFolder(String folder, ExtentTest logTest) throws Exception {
        try {
            /*
            log4j.debug("deleteAllEmailAtFolder...starts");

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.store.port", "995");
            properties.put("mail.store.starttls.enable", "true");
            Session session = Session.getInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(GMAIL_ADDRESS, GMAIL_PASSWORD);
                        }
                    });

            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", GMAIL_ADDRESS, GMAIL_PASSWORD);

            //Delete all mail in folder
            folderInbox = store.getFolder(folder);
            folderInbox.open(Folder.READ_WRITE);
            Message[] messageInbox = folderInbox.getMessages();
            for (int i = 0; i < messageInbox.length; i++) {
                messageInbox[i].setFlag(Flag.DELETED, true);
            }
            folderInbox.expunge();
            properties.clear();

            log4j.info("Delete email on " + folder + " folder successfully");

            log4j.info("deleteAllEmailAtFolder...ends");*/
        } catch (Exception e) {
            log4j.error("deleteAllEmailAtFolder method - ERROR - " + e);
            logException(logTest, "deleteAllEmailAtFolder method - ERROR", e);
        }
    }
}
