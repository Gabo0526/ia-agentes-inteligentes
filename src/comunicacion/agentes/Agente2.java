package comunicacion.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import comunicacion.utilidades.Mensaje;
import comunicacion.utilidades.Persona;

import java.util.UUID;

public class Agente2 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() { // Nunca llamar este metodo
            ACLMessage aclMessage = blockingReceive();

            Mensaje.recibirMensaje(aclMessage);

            Mensaje.sendMensaje(ACLMessage.INFORM, "Ag3", getAgent(), UUID.randomUUID().toString(), String.format("Ag3, soy el agente %s, el agente %s necesita sus credenciales", getAgent().getName(), aclMessage.getSender()), null);
            Mensaje.sendMensaje(ACLMessage.INFORM, "Ag4", getAgent(), UUID.randomUUID().toString(), null, new Persona("Gabriel", "Vasconez", 20));
        }
    }
}
