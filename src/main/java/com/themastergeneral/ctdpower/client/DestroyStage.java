package com.themastergeneral.ctdpower.client;

import com.themastergeneral.ctdpower.CTDPower;

import net.minecraft.util.ResourceLocation;

public final class DestroyStage
{
	private static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[]
	{
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_0.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_1.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_2.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_3.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_4.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_5.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_6.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_7.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_8.png"),
		new ResourceLocation(CTDPower.MODID, "textures/models/destroy_stage_9.png")
	};
	
	public static ResourceLocation getByProgress(float progress)
	{
		return DESTROY_STAGES[(int) Math.round(progress * (DESTROY_STAGES.length - 1))];
	}
}
