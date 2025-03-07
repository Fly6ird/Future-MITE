package com.github.FlyBird.FutureMITE.render.entity;

import com.github.FlyBird.FutureMITE.entities.EntityRabbit;
import com.github.FlyBird.FutureMITE.models.ModelRabbit;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RabbitRenderer extends RenderLiving {

    private static final ResourceLocation BROWN = new ResourceLocation("futuremite:textures/entity/rabbit/brown.png");
    private static final ResourceLocation WHITE = new ResourceLocation("futuremite:textures/entity/rabbit/white.png");
    private static final ResourceLocation BLACK = new ResourceLocation("futuremite:textures/entity/rabbit/black.png");
    private static final ResourceLocation GOLD = new ResourceLocation("futuremite:textures/entity/rabbit/gold.png");
    private static final ResourceLocation SALT = new ResourceLocation("futuremite:textures/entity/rabbit/salt.png");
    private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
    private static final ResourceLocation TOAST = new ResourceLocation("futuremite:textures/entity/rabbit/toast.png");

    public RabbitRenderer() {
        super(new ModelRabbit(), 0.3F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entityliving, float patialTickTime) {
        GL11.glScalef(0.65F, 0.65F, 0.65F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityRabbit rabbit = (EntityRabbit) entity;
        String s = EnumChatFormatting.func_110646_a(rabbit.getEntityName());//getEntityName  = getCommandSenderName ?

        if (s != null && s.equals("Toast"))
            return TOAST;
        else
            switch (rabbit.getRabbitType()) {
                case 0:
                default:
                    return BROWN;
                case 1:
                    return WHITE;
                case 2:
                    return BLACK;
                case 3:
                    return WHITE_SPLOTCHED;
                case 4:
                    return GOLD;
                case 5:
                    return SALT;
            }
    }

    @Override
    protected void setTextures() {

    }
}