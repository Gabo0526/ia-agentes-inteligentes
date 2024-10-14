package utilidades;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.Serializable;

public class Message {
    public static void sendMessage(int messageType, String receiver, Agent sender, String conversationId, String stringContent, Serializable objectContent) {
        ACLMessage aclMessage = new ACLMessage(messageType);
        aclMessage.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        AID aid = new AID();
        aid.setLocalName(receiver);

        aclMessage.addReceiver(aid);
        aclMessage.setSender(sender.getAID());
        aclMessage.setConversationId(conversationId);

        if (objectContent != null) {
            try {
                aclMessage.setContentObject(objectContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            aclMessage.setContent(stringContent);
        }

        sender.send(aclMessage);
    }

    public static void getMessage(ACLMessage aclMessage) {
        System.out.printf("Mensaje del agente %s obtenido:\n", aclMessage.getSender().toString());

        try {
            Person p = (Person) aclMessage.getContentObject();
            System.out.println(p.toString());
        } catch (Exception e) {
            System.out.println(aclMessage.getContent());
            System.out.println();
        }
    }
}
