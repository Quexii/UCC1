package gay.shoroa.bolt.client.event.impl;

import gay.shoroa.bolt.client.event.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EventMouseClick extends Event {
    public final int button;
}
