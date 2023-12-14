package main.java.raf.dsw.classycraft.app.model.message;

import main.java.raf.dsw.classycraft.app.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.util.ArrayList;

public class MessageGenerator implements IPublisher {
	
	private ArrayList<ISubscriber> subscribers;
	
	public void GenerateMessage(SystemEvent systemEvent) {
		Message message;
		switch(systemEvent) {
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
			case NO_SELECTED_NODE:
				message = new Message(systemEvent, MessageType.ERROR, "Nije izabran cvor");
				break;
			default:
				return;
		}
		notifySubscribers(message);
	}
	
	@Override
	public void addSubscriber(ISubscriber sub) {
		if(sub == null)
			return;
		if(this.subscribers == null)
			this.subscribers = new ArrayList<>();
		if(this.subscribers.contains(sub))
			return;
		this.subscribers.add(sub);
	}
	
	@Override
	public void removeSubscriber(ISubscriber sub) {
		subscribers.remove(sub);
	}
	
	@Override
	public void notifySubscribers(Object notification) {
		if(notification instanceof Message) {
			for(ISubscriber s : subscribers) {
				s.update(notification);
			}
		}
	}
}
