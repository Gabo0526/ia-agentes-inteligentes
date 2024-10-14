package agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.LinkedList;
import java.util.UUID;

public class Agent4 extends Agent {
    private LinkedList<Object> messages = new LinkedList<>();

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage msg = blockingReceive();
                messages.addLast(msg);
                Message.getMessage(msg);

                if (messages.size() == 2) {
                    for (Object message : messages) {
                        ACLMessage aclMsg = (ACLMessage) message;

                        if (aclMsg.getContent().contains("Hola, soy un Child, mi generacion es")) {
                            String[] splited = msg.getContent().split(":");

                            Message.sendMessage(ACLMessage.INFORM, "Ag5", getAgent(), UUID.randomUUID().toString(), "La generacion actual es:" + splited[1], null);

                            break;
                        }
                    }

                    messages.clear();
                }
            }
        });
    }
}
