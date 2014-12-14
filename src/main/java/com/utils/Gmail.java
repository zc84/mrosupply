package com.utils;

import com.data.DataProvider;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Gmail {

    private static Logger logger = Logger.getLogger(Gmail.class);
    private static Session session;
    private static Store store;
    private static Folder folder;
    private final String EMAIL = LoadProperties.loadProperty("gmail.address");
    private final String PASSWORD = LoadProperties.loadProperty("gmail.password");
    private Properties props;

    public Gmail() throws MessagingException {

        if (folder == null || !folder.isOpen()) {
            props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");
            session = Session.getDefaultInstance(props, null);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", EMAIL, PASSWORD);
            folder = store.getFolder("Inbox");
            folder.open(Folder.READ_WRITE);
        }
    }

    public static Message getGoalMessage(String subj) throws Exception {

        Message message = null;
        if (subj.equals("any"))
            return folder.getMessages()[0];
        else
            for (Message msg : folder.getMessages())
                if (msg.getSubject().contains(subj)) {
                    message = msg;
                    break;
                }
        if (message == null)
            throw new Exception(subj + " email wasn't found");
        return message;
    }

    public static Message getFirstMsg() throws MessagingException {
        return folder.getMessages()[0];
    }

    public static String readEmail(Message message) throws IOException, MessagingException {
        return message.getContent().toString();
    }

    public static String read_email_mime(Message email) throws MessagingException, IOException {

        StringBuilder sb = null;
        Object content = email.getContent();
        if (content instanceof Multipart) {

            Multipart multi = ((Multipart) content);
            int parts = multi.getCount();
            for (int j = 0; j < parts; ++j) {
                MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
                InputStream in = part.getInputStream();
                BufferedReader br = null;
                sb = new StringBuilder();

                String line;

                br = new BufferedReader(new InputStreamReader(in));
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
            }

            return sb.toString();

        } else return email.getContent().toString();
    }

    public static void saveParts(Object content, String filename) throws IOException, MessagingException {

        OutputStream out = null;
        InputStream in = null;
        if (content instanceof Multipart) {
            Multipart multi = ((Multipart) content);
            int parts = multi.getCount();
            for (int j = 0; j < parts; ++j) {
                MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
                if (part.getContent() instanceof Multipart) {
                    saveParts(part.getContent(), filename);
                } else {
                    String extension = "";
                    if (part.isMimeType("text/html")) {
                        extension = "html";
                    } else {
                        if (part.isMimeType("text/plain")) {
                            extension = "txt";
                        } else {
                            extension = part.getDataHandler().getName();
                        }
                        filename = filename + "." + extension;
                        out = new FileOutputStream(new File(filename));
                        in = part.getInputStream();
                        int k;
                        while ((k = in.read()) != -1) {
                            out.write(k);
                        }
                    }
                }
            }
        }
    }

    public void waitForNewEmail(int waitInMin) throws Exception {

        int waitPeriodInMilSec = waitInMin * 60 * 1000;
        int waitInterval = 5000;
        int i = 0;

        while (i < waitPeriodInMilSec) {
            if (folder.getMessageCount() > 0)
                break;
            else {
                Thread.sleep(waitInterval);
                i += waitInterval;
                logger.info("Waiting for " + ((waitInMin * 60 * 1000) - i) / 1000 + " secs");
            }
        }
        if (!(folder.getMessageCount() > 0)) throw new Exception("Email wasn't received for " + DataProvider.USER_EMAIL + " after " + waitInMin + " mins");
    }

    public void clearEmailBox() throws MessagingException {
        if (folder.getMessages().length > 0)
            for (Message msg : folder.getMessages())
                msg.setFlag(Flags.Flag.DELETED, true);
    }

    public void getAttachment() throws MessagingException, IOException {
        Message msg = getFirstMsg();
        String subject = msg.getSubject();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
        String filename = "temp/" + dateFormat.format(calendar.getTime()) + "_" + subject;
        saveParts(msg.getContent(), filename);
    }
}
