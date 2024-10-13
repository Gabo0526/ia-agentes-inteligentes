package comunicacion.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import comunicacion.utilidades.Mensaje;

import java.util.UUID;

public class Agente5 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage acl = blockingReceive();

            Mensaje.recibirMensaje(acl);

            Mensaje.sendMensaje(ACLMessage.INFORM, "Ag1", getAgent(), UUID.randomUUID().toString(), "Ag1, soy el agente 5, te he enviado tus credenciales", null);
        }
    }
}
