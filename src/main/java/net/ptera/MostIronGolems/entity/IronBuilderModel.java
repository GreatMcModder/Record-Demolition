package net.ptera.MostIronGolems.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CamelEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class IronBuilderModel extends SinglePartEntityModel<IronBuilderEntity> {
	private final ModelPart builder;
	private final ModelPart head;
	private final ModelPart tentacle;
	private final ModelPart tentacle2;
	private final ModelPart tentacle3;
	private final ModelPart tentacle4;
	private final ModelPart tentacle5;
	private final ModelPart tentacle6;
	private final ModelPart tentacle7;
	private final ModelPart tentacle13;
	private final ModelPart tentacle8;
	private final ModelPart tentacle14;
	private final ModelPart tentacle9;
	private final ModelPart tentacle10;
	private final ModelPart tentacle11;
	private final ModelPart tentacle12;
	public IronBuilderModel(ModelPart root) {
		this.builder = root.getChild("builder");
		this.head = this.builder.getChild("head");
		this.tentacle = this.head.getChild("tentacle");
		this.tentacle2 = this.head.getChild("tentacle2");
		this.tentacle3 = this.head.getChild("tentacle3");
		this.tentacle4 = this.head.getChild("tentacle4");
		this.tentacle5 = this.head.getChild("tentacle5");
		this.tentacle6 = this.head.getChild("tentacle6");
		this.tentacle7 = this.head.getChild("tentacle7");
		this.tentacle13 = this.head.getChild("tentacle13");
		this.tentacle8 = this.head.getChild("tentacle8");
		this.tentacle14 = this.head.getChild("tentacle14");
		this.tentacle9 = this.head.getChild("tentacle9");
		this.tentacle10 = this.head.getChild("tentacle10");
		this.tentacle11 = this.head.getChild("tentacle11");
		this.tentacle12 = this.head.getChild("tentacle12");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData builder = modelPartData.addChild("builder", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = builder.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -11.0F, -1.0F));

		ModelPartData tentacle = head.addChild("tentacle", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, 4.0F));

		ModelPartData tentacle2 = head.addChild("tentacle2", ModelPartBuilder.create().uv(8, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, 0.0F));

		ModelPartData tentacle3 = head.addChild("tentacle3", ModelPartBuilder.create().uv(16, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 0.0F, -4.0F));

		ModelPartData tentacle4 = head.addChild("tentacle4", ModelPartBuilder.create().uv(24, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, -4.0F));

		ModelPartData tentacle5 = head.addChild("tentacle5", ModelPartBuilder.create().uv(32, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));

		ModelPartData tentacle6 = head.addChild("tentacle6", ModelPartBuilder.create().uv(32, 15).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 4.0F));

		ModelPartData tentacle7 = head.addChild("tentacle7", ModelPartBuilder.create().uv(32, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -5.0F, 2.0F, 0.0F, 0.0F, 0.8727F));

		ModelPartData tentacle13 = head.addChild("tentacle13", ModelPartBuilder.create().uv(40, 15).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.0F, 2.0F, 0.0F, 0.0F, 0.8727F));

		ModelPartData tentacle8 = head.addChild("tentacle8", ModelPartBuilder.create().uv(0, 33).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.0F, -2.0F, 0.0F, 0.0F, 0.8727F));

		ModelPartData tentacle14 = head.addChild("tentacle14", ModelPartBuilder.create().uv(40, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.0F, -2.0F, 0.0F, 0.0F, 0.8727F));

		ModelPartData tentacle9 = head.addChild("tentacle9", ModelPartBuilder.create().uv(8, 33).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.6428F, -4.766F, -2.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData tentacle10 = head.addChild("tentacle10", ModelPartBuilder.create().uv(16, 33).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.6428F, -4.766F, 2.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData tentacle11 = head.addChild("tentacle11", ModelPartBuilder.create().uv(24, 33).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.6428F, -7.766F, 2.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData tentacle12 = head.addChild("tentacle12", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.6428F, -7.766F, -2.0F, 0.0F, 0.0F, -0.8727F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(IronBuilderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		animateMovement(IronBuilderModelAnimation.walk,limbSwing,limbSwingAmount,1.5F,1.5F);
		updateAnimation(entity.build,IronBuilderModelAnimation.place,ageInTicks);
	}

	@Override
	public ModelPart getPart() {
		return builder;
	}
}