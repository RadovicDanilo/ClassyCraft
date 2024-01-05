package main.java.raf.dsw.classycraft.app.model.message;

import main.java.raf.dsw.classycraft.app.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.util.ArrayList;

public class MessageGenerator implements IPublisher {

    private ArrayList<ISubscriber> subscribers;

    public void GenerateMessage(SystemEvent systemEvent) {
        Message message;
        String savet = "\n Savet: Nazivi svih polja, metoda i enuma kao i njihobih vrednosti moraj poceti slovom (malim ili velikim) " +
                "\n ostatak naziv sme samo da sadrzi alfanumericke karktere";
        switch (systemEvent) {
            case NAME_CANNOT_BE_EMPTY:
                message = new Message(systemEvent, MessageType.ERROR, "Naziv nesme biti prazan");
                break;
            case NODE_CANNOT_BE_DELETED:
                message = new Message(systemEvent, MessageType.ERROR, "Cvor ne moze biti obrisan");
                break;
            case NODE_CANNOT_BE_DUPLICATE:
                message = new Message(systemEvent, MessageType.ERROR, "Vec postoji cvor sa datim imenom");
                break;
            case CANNOT_REMOVE_ROOT:
                message = new Message(systemEvent, MessageType.ERROR, "Project Explorer ne moze da se ukloni");
                break;
            case DIAGRAM_CAN_ONLY_BE_ADDED_TO_PACKAGE:
                message = new Message(systemEvent, MessageType.ERROR, "Diagram se mogu praviti samo u projektima ili paketima");
                break;
            case CANNOT_ADD_PACKAGE_TO_ROOT_OR_DIAGRAM:
                message = new Message(systemEvent, MessageType.ERROR, "Paketi se mogu praviti samo u projektima ili drugim paketima");
                break;
            case CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS:
                message = new Message(systemEvent, MessageType.ERROR, "Mora te selektovati projekat da bi ste mogli da promentie autora");
                break;
            case CONNECTION_ALREADY_EXISTS:
                message = new Message(systemEvent, MessageType.ERROR, "veza vec postoji");
                break;
            case CANNOT_RENAME_ROOT:
                message = new Message(systemEvent, MessageType.ERROR, "project manage (root) se ne moze preimenovati");
                break;
            case ENUM_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Enum nije dobro unet" + savet);
                break;
            case FIELD_NAME_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Naziv polja nije dobro unet" + savet);
                break;
            case FIELD_VALUE_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Tip polja nije dobar" + savet);
                break;
            case METHOD_NAME_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Ime metode nije dobro uneto" + savet);
                break;
            case METHOD_RETURN_VALUE_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Povratna vrednost nije dobro uneta" + savet);
                break;
            case NO_SELECTED_NODE:
                message = new Message(systemEvent, MessageType.ERROR, "Nije izabran cvor");
                break;
            case INTERCLASS_NAME_NOT_VALID:
                message = new Message(systemEvent, MessageType.ERROR, "Naziv interclass element (klasa, enum ili interfejs) nije validan" + savet);
                break;
            case DUPLICATE_FIELDS_OR_METHODS:
                message = new Message(systemEvent, MessageType.ERROR, "Jedno ili vise polja/metoda su iste.\nPromene koje ste npravili nad njima nece biti primenjene");
                break;
            case BAD_PATH:
                message = new Message(systemEvent, MessageType.ERROR, "Putanja nije ispravna");
                break;
            case NO_OPENED_DIAGRAM:
                message = new Message(systemEvent, MessageType.ERROR, "Ne postoji otvoren diagram");
                break;
            case NO_DIAGRAM_SELECTED:
                message = new Message(systemEvent, MessageType.ERROR, "Ne postoji izabran diagram");
                break;
            case NO_PROJECT_SELECTED:
                message = new Message(systemEvent, MessageType.ERROR, "Nijedan projekat nije izabran");
                break;
            case CANNOT_RENAME_CONNECTION:
                message = new Message(systemEvent, MessageType.ERROR, "Veza se ne moze preimenovati");
                break;
            case PROJECT_ALREADY_OPENED:
                message = new Message(systemEvent, MessageType.ERROR, "Projekat je vec otvoren ili postoji vec jedan sa takvim imenom");
                break;
            default:
                return;
        }
        notifySubscribers(message);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if (notification instanceof Message) {
            for (ISubscriber s : subscribers) {
                s.update(notification);
            }
        }
    }
}
