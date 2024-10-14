package agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.UUID;

public class Agent2 extends Agent {
    private String dynamicMessageReceiver = "";

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msg = blockingReceive();

                Message.getMessage(msg);

                String[] splited = msg.getContent().split(":");

                dynamicMessageReceiver = splited[1];

                Message.sendMessage(ACLMessage.INFORM, dynamicMessageReceiver, getAgent(), UUID.randomUUID().toString(), "Hola, soy el Agente 2, ya puedes morir en paz", null);
                Message.sendMessage(ACLMessage.INFORM, "Ag4", getAgent(), UUID.randomUUID().toString(), "Hola, soy el Agente 2. Ag4, avisanos quien es la nueva generacion", null);
            }
        });
    }
}
