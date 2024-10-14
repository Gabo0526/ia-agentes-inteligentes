package agent;

import container.Container;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.UUID;

public class Agent3 extends Agent {
    private Container globalContainer;

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                globalContainer = (Container) getArguments()[0];

                ACLMessage msg = blockingReceive();

                Message.getMessage(msg);

                doDelete();

                /*if (msg.getContent().contains("Ya puedes morir en paz")) {
                    doDelete();
                } else {
                    Message.sendMessage(ACLMessage.INFORM, "Ag4", getAgent(), UUID.randomUUID().toString(), "Estoy feliz de seguir vivo xd", null);
                }*/
            }
        });
    }

    @Override
    protected void takeDown() {
        globalContainer.addChild("ChildAgent", new Object[]{globalContainer});
        Message.sendMessage(ACLMessage.INFORM, "ChildAgent", this, UUID.randomUUID().toString(), "No olvides mencionarles como te llamas:ChildAgent", null);
    }
}
