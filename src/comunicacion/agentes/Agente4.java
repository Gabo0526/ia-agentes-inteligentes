package comunicacion.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import comunicacion.utilidades.Mensaje;

import java.util.LinkedList;
import java.util.UUID;

public class Agente4 extends Agent {
    private LinkedList<Object> mensajes = new LinkedList<>();

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() { // Nunca llamar este metodo

            ACLMessage aclMessage = blockingReceive();
            mensajes.addLast(aclMessage);
            Mensaje.recibirMensaje(aclMessage);

            if (mensajes.size() == 2) {
                Mensaje.sendMensaje(ACLMessage.INFORM, "Ag5", getAgent(), UUID.randomUUID().toString(), String.format("Ag5, soy el agente %s, revisa que funcionen las credenciales que acabo de subir", getAgent().getName()), null);
                mensajes.clear();
            }
        }
    }
}
