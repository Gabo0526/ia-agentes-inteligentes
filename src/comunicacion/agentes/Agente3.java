package comunicacion.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import comunicacion.utilidades.Mensaje;

import java.util.UUID;

public class Agente3 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() { // Nunca llamar este metodo
            ACLMessage aclMessage = blockingReceive();

            Mensaje.recibirMensaje(aclMessage);

            Mensaje.sendMensaje(ACLMessage.INFORM, "Ag4", getAgent(), UUID.randomUUID().toString(), String.format("Ag4, soy el agente %s, envia las credenciales que te pedi, el ag2 ya debio haberte mandado la info necesaria", getAgent().getName()), null);
        }
    }
}
