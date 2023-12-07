package gay.shoroa.bolt.client.event.impl;

import gay.shoroa.bolt.client.event.Event;
import lombok.AllArgsConstructor;
import net.minecraft.entity.EntityLivingBase;

@AllArgsConstructor
public class EventEntityHurt extends Event {
    public EntityLivingBase entity;
}
