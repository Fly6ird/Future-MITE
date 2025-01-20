package com.github.FlyBird.FutureMITE.models;

import net.minecraft.Entity;
import net.minecraft.ModelBase;
import net.minecraft.ModelRenderer;

public class ModelGrindstone extends ModelBase {
    private final ModelRenderer bone;

    public ModelGrindstone() {
        textureWidth = 48;
        textureHeight = 48;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(8.0F, 24.0F, -8.0F);
        this.bone.setTextureOffset(32, 24).addBox(-14.0F, -7.0F, 6.0F, 2, 7, 4, 0.0F);
        this.bone.setTextureOffset(32, 24).addBox(-4.0F, -7.0F, 6.0F, 2, 7, 4, 0.0F);
        this.bone.setTextureOffset(0, 24).addBox(-14.0F, -13.0F, 5.0F, 2, 6, 6, 0.0F);
        this.bone.setTextureOffset(0, 24).addBox(-4.0F, -13.0F, 5.0F, 2, 6, 6, 0.0F);
        this.bone.setTextureOffset(0, 0).addBox(-12.0F, -16.0F, 2.0F, 8, 12, 12, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bone.render(f5);
    }


    public void renderGrindstone(float rotation) {
        bone.render(rotation);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, f and f1 are used for animating the movement of arms
     * and legs, where f represents the time(so that arms and legs swing back and forth) and f1 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

    }

    /**
     * Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
     */
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}