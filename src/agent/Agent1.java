package agent;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import utilidades.Message;

import java.util.UUID;

public class Agent1 extends Agent {
    private String currentGeneration = "Ag3"; // Se le asigna un valor inicial, en este caso, el valor inicial es el agente 3;
    private int count = 0;

    @Override
    protected void setup() {
            addBehaviour(new SimpleBehaviour(this) {

            @Override
            public void action() {
                Message.sendMessage(ACLMessage.INFORM, "Ag2", getAgent(), UUID.randomUUID().toString(), "La generacion actual es:" + currentGeneration, null);

                ACLMessage msg = blockingReceive();

                Message.getMessage(msg);

                String[] splited = msg.getContent().split(":");

                currentGeneration = splited[1];
                count++;

                System.out.printf("Se ha completado la generacion nro.: %d\n", count);
            }

                @Override
                public boolean done() {
                    return count >= 100;
                }
            });
    }
}
