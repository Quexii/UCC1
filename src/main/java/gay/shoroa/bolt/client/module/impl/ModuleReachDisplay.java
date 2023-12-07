package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.client.event.impl.EventEntityHurt;
import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.hud.TextModule;
import io.github.nevalackin.radbus.Listen;
import lombok.NonNull;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ModuleReachDisplay extends TextModule {
    public ModuleReachDisplay() {
        super("Reach Display", Category.HUD);
    }

    private float distance = 0;

    @Listen
    public void hurtListener(EventEntityHurt e) {
        if(mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit.getEntityId() == e.entity.getEntityId()) {
            Vec3 vec3 = mc.getRenderViewEntity().getPositionEyes(1.0F);
            int d = (int) (mc.objectMouseOver.hitVec.distanceTo(vec3)*100);
            distance = d / 100f;
        }
    }

    @Override
    public String getText() {
        return distance + " blocks";
    }

    @Override
    public String getDummyText() {
        return distance + " blocks";
    }
}
