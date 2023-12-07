package gay.shoroa.bolt.client.event.impl;

import gay.shoroa.bolt.client.event.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EventKey extends Event {
    public final int keycode;
}
