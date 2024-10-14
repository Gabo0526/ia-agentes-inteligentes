package agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.UUID;

public class Agent5 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msg = blockingReceive();

                Message.getMessage(msg);

                String[] splited = msg.getContent().split(":");

                Message.sendMessage(ACLMessage.INFORM, "Ag1", getAgent(), UUID.randomUUID().toString(), "La generacion actual es:" + splited[1], null);
            }
        });
    }
}
