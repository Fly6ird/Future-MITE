package com.github.FlyBird.FutureMITE.render.entity;

import com.github.FlyBird.FutureMITE.models.ModelEndermite;
import net.minecraft.Entity;
import net.minecraft.EntityLivingBase;
import net.minecraft.RenderLiving;
import net.minecraft.ResourceLocation;

public class EndermiteRenderer extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("futuremite:textures/entity/endermite.png");

    public EndermiteRenderer() {
        super(new ModelEndermite(), 0.3F);
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    protected void setTextures() {

    }
}