package agent;

import container.Container;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.UUID;

public class ChildAgent extends Agent {
    private Container globalContainer;
    private int generationCounter = 0;

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                globalContainer = (Container) getArguments()[0];

                try {
                    generationCounter = (int) getArguments()[1];
                } catch (Exception e) {
                    //
                }

                ACLMessage msg = blockingReceive();

                Message.getMessage(msg);

                if (msg.getContent().contains("morir")) {
                    generationCounter++;
                    doDelete();
                } else {
                    String[] splited = msg.getContent().split(":");

                    Message.sendMessage(ACLMessage.INFORM, "Ag4", getAgent(), UUID.randomUUID().toString(), "Hola, soy un Child, mi generacion es:" + splited[1], null);
                }
            }
        });
    }

    @Override
    protected void takeDown() {
        globalContainer.addChild("ChildAgent" + Integer.toString(generationCounter), new Object[]{globalContainer, generationCounter});
        Message.sendMessage(ACLMessage.INFORM, "ChildAgent" + Integer.toString(generationCounter), this, UUID.randomUUID().toString(), "No olvides mencionarles como te llamas:ChildAgent" + Integer.toString(generationCounter), null);
    }
}
