package comunicacion.utilidades;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.Serializable;

public class Mensaje {
    public static void sendMensaje(int tipoMensaje, String receptor, Agent emisor, String conversationId, String contenido, Serializable contenidoObjeto) {
        ACLMessage aclMessage = new ACLMessage(tipoMensaje);
        aclMessage.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        AID aid = new AID();
        aid.setLocalName(receptor);
        aclMessage.addReceiver(aid);
        aclMessage.setSender(emisor.getAID());
        aclMessage.setConversationId(conversationId);

        if (contenidoObjeto != null) {
            try {
                aclMessage.setContentObject(contenidoObjeto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            aclMessage.setContent(contenido);
        }

        emisor.send(aclMessage);
    }

    public static void recibirMensaje(ACLMessage aclMessage) {
        try {
            Persona p = (Persona) aclMessage.getContentObject();
            System.out.println(p.toString());
        } catch (Exception e) {
            System.out.println(aclMessage.getContent());
        }
    }
}
