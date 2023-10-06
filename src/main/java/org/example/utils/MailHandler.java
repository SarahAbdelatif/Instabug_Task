package org.example.utils;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.*;
import java.io.IOException;
public class MailHandler {

   public static void main(String[] args) throws IOException, MailosaurException {
        // Available in the API tab of a server
        String apiKey = "6DdN5j3EmR0YJSTFmOmXCnpc13gKaxaC";
        String serverId = "2ckgo66j";
        String serverDomain = "example.mailosaur.net";

        MailosaurClient mailosaur = new MailosaurClient(apiKey);

        MessageSearchParams params = new MessageSearchParams();
        params.withServer(serverId);

        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentFrom("asdas@" + serverDomain);

        Message message = mailosaur.messages().get(params, criteria);

        System.out.println("My email subject"+ message.subject());
    }
}