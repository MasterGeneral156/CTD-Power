package com.themastergeneral.ctdpower.client.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlocks
{
  private static RenderBlocks instance;
  public static float alpha = 1.0F;
  public IBlockAccess blockAccess;
  public boolean flipTexture;
  public boolean field_152631_f;
  public boolean renderAllFaces;
  public boolean useInventoryTint = true;
  public boolean renderFromInside = false;
  public double renderMinX;
  public double renderMaxX;
  public double renderMinY;
  public double renderMaxY;
  public double renderMinZ;
  public double renderMaxZ;
  public boolean lockBlockBounds;
  public boolean partialRenderBounds;
  public final Minecraft minecraftRB;
  public int uvRotateEast;
  public int uvRotateWest;
  public int uvRotateSouth;
  public int uvRotateNorth;
  public int uvRotateTop;
  public int uvRotateBottom;
  public float aoLightValueScratchXYZNNN;
  public float aoLightValueScratchXYNN;
  public float aoLightValueScratchXYZNNP;
  public float aoLightValueScratchYZNN;
  public float aoLightValueScratchYZNP;
  public float aoLightValueScratchXYZPNN;
  public float aoLightValueScratchXYPN;
  public float aoLightValueScratchXYZPNP;
  public float aoLightValueScratchXYZNPN;
  public float aoLightValueScratchXYNP;
  public float aoLightValueScratchXYZNPP;
  public float aoLightValueScratchYZPN;
  public float aoLightValueScratchXYZPPN;
  public float aoLightValueScratchXYPP;
  public float aoLightValueScratchYZPP;
  public float aoLightValueScratchXYZPPP;
  public float aoLightValueScratchXZNN;
  public float aoLightValueScratchXZPN;
  public float aoLightValueScratchXZNP;
  public float aoLightValueScratchXZPP;
  public int aoBrightnessXYZNNN;
  public int aoBrightnessXYNN;
  public int aoBrightnessXYZNNP;
  public int aoBrightnessYZNN;
  public int aoBrightnessYZNP;
  public int aoBrightnessXYZPNN;
  public int aoBrightnessXYPN;
  public int aoBrightnessXYZPNP;
  public int aoBrightnessXYZNPN;
  public int aoBrightnessXYNP;
  public int aoBrightnessXYZNPP;
  public int aoBrightnessYZPN;
  public int aoBrightnessXYZPPN;
  public int aoBrightnessXYPP;
  public int aoBrightnessYZPP;
  public int aoBrightnessXYZPPP;
  public int aoBrightnessXZNN;
  public int aoBrightnessXZPN;
  public int aoBrightnessXZNP;
  public int aoBrightnessXZPP;
  public int brightnessTopLeft;
  public int brightnessBottomLeft;
  public int brightnessBottomRight;
  public int brightnessTopRight;
  public float colorRedTopLeft;
  public float colorRedBottomLeft;
  public float colorRedBottomRight;
  public float colorRedTopRight;
  public float colorGreenTopLeft;
  public float colorGreenBottomLeft;
  public float colorGreenBottomRight;
  public float colorGreenTopRight;
  public float colorBlueTopLeft;
  public float colorBlueBottomLeft;
  public float colorBlueBottomRight;
  public float colorBlueTopRight;
  
  public RenderBlocks(IBlockAccess p_i1251_1_)
  {
    this.blockAccess = p_i1251_1_;
    this.field_152631_f = false;
    this.flipTexture = false;
    this.minecraftRB = Minecraft.getMinecraft();
  }
  
  public RenderBlocks()
  {
    this.minecraftRB = Minecraft.getMinecraft();
  }
  
  public int setLighting(World world, BlockPos pos)
  {
    int i = world.getCombinedLight(pos, 0);
    EnumFacing[] arrayOfEnumFacing;
    int j = (arrayOfEnumFacing = EnumFacing.VALUES).length;
    for (int i1 = 0; i1 < j; i1++)
    {
      EnumFacing f = arrayOfEnumFacing[i1];i1 = Math.max(world.getCombinedLight(pos.offset(f), 0), i1);
    }
    int j1 = i % 65536;
    int k = i / 65536;
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k);
    return i;
  }
  
  public void setRenderBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
  {
    if (!this.lockBlockBounds)
    {
      this.renderMinX = minX;
      this.renderMaxX = maxX;
      this.renderMinY = minY;
      this.renderMaxY = maxY;
      this.renderMinZ = minZ;
      this.renderMaxZ = maxZ;
      this.partialRenderBounds = ((this.minecraftRB.gameSettings.ambientOcclusion >= 2) && ((this.renderMinX > 0.0D) || (this.renderMaxX < 1.0D) || (this.renderMinY > 0.0D) || (this.renderMaxY < 1.0D) || (this.renderMinZ > 0.0D) || (this.renderMaxZ < 1.0D)));
    }
  }
  
  public void setRenderBoundsFromBlock(BlockPos pos, IBlockState state)
  {
    if (!this.lockBlockBounds)
    {
      AxisAlignedBB bb = state.getBoundingBox(this.blockAccess, pos);
      this.renderMinX = (pos.getX() - bb.minX);
      this.renderMaxX = (pos.getX() - bb.maxX);
      this.renderMinY = (pos.getY() - bb.minY);
      this.renderMaxY = (pos.getY() - bb.maxY);
      this.renderMinZ = (pos.getZ() - bb.minZ);
      this.renderMaxZ = (pos.getZ() - bb.maxZ);
      this.partialRenderBounds = ((this.minecraftRB.gameSettings.ambientOcclusion >= 2) && ((this.renderMinX > 0.0D) || (this.renderMaxX < 1.0D) || (this.renderMinY > 0.0D) || (this.renderMaxY < 1.0D) || (this.renderMinZ > 0.0D) || (this.renderMaxZ < 1.0D)));
    }
  }
  
  public void overrideBlockBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
  {
    this.renderMinX = minX;
    this.renderMaxX = maxX;
    this.renderMinY = minY;
    this.renderMaxY = maxY;
    this.renderMinZ = minZ;
    this.renderMaxZ = maxZ;
    this.lockBlockBounds = true;
    this.partialRenderBounds = ((this.minecraftRB.gameSettings.ambientOcclusion >= 2) && ((this.renderMinX > 0.0D) || (this.renderMaxX < 1.0D) || (this.renderMinY > 0.0D) || (this.renderMaxY < 1.0D) || (this.renderMinZ > 0.0D) || (this.renderMaxZ < 1.0D)));
  }
  
  public void renderFaceYNeg(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinX * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxX * 16.0D);
    double d5 = sprite.getInterpolatedV(this.renderMinZ * 16.0D);
    double d6 = sprite.getInterpolatedV(this.renderMaxZ * 16.0D);
    if ((this.renderMinX < 0.0D) || (this.renderMaxX > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinZ < 0.0D) || (this.renderMaxZ > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateBottom == 2)
    {
      d3 = sprite.getInterpolatedU(this.renderMinZ * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxZ * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateBottom == 1)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMaxX * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateBottom == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMinX;
    double d12 = x + this.renderMaxX;
    double d13 = y + this.renderMinY;
    double d14 = z + this.renderMinZ;
    double d15 = z + this.renderMaxZ;
    if (this.renderFromInside)
    {
      d11 = x + this.renderMaxX;
      d12 = x + this.renderMinX;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    
    tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public void renderFaceYPos(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinX * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxX * 16.0D);
    double d5 = sprite.getInterpolatedV(this.renderMinZ * 16.0D);
    double d6 = sprite.getInterpolatedV(this.renderMaxZ * 16.0D);
    if ((this.renderMinX < 0.0D) || (this.renderMaxX > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinZ < 0.0D) || (this.renderMaxZ > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateTop == 1)
    {
      d3 = sprite.getInterpolatedU(this.renderMinZ * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxZ * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateTop == 2)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMaxX * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateTop == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMinX;
    double d12 = x + this.renderMaxX;
    double d13 = y + this.renderMaxY;
    double d14 = z + this.renderMinZ;
    double d15 = z + this.renderMaxZ;
    if (this.renderFromInside)
    {
      d11 = x + this.renderMaxX;
      d12 = x + this.renderMinX;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    
    tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public void renderFaceZNeg(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinX * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxX * 16.0D);
    if (this.field_152631_f)
    {
      d4 = sprite.getInterpolatedU((1.0D - this.renderMinX) * 16.0D);
      d3 = sprite.getInterpolatedU((1.0D - this.renderMaxX) * 16.0D);
    }
    double d5 = sprite.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
    double d6 = sprite.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
    if (this.flipTexture)
    {
      double d7 = d3;
      d3 = d4;
      d4 = d7;
    }
    if ((this.renderMinX < 0.0D) || (this.renderMaxX > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinY < 0.0D) || (this.renderMaxY > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateEast == 2)
    {
      d3 = sprite.getInterpolatedU(this.renderMinY * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxY * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateEast == 1)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxX * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinX * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateEast == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinY * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMinX;
    double d12 = x + this.renderMaxX;
    double d13 = y + this.renderMinY;
    double d14 = y + this.renderMaxY;
    double d15 = z + this.renderMinZ;
    if (this.renderFromInside)
    {
      d11 = x + this.renderMaxX;
      d12 = x + this.renderMinX;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    
    tessellator.getBuffer().pos(d11, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public void renderFaceZPos(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinX * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxX * 16.0D);
    double d5 = sprite.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
    double d6 = sprite.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
    if (this.flipTexture)
    {
      double d7 = d3;
      d3 = d4;
      d4 = d7;
    }
    if ((this.renderMinX < 0.0D) || (this.renderMaxX > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinY < 0.0D) || (this.renderMaxY > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateWest == 1)
    {
      d3 = sprite.getInterpolatedU(this.renderMinY * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxY * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateWest == 2)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMaxX * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateWest == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinY * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMinX;
    double d12 = x + this.renderMaxX;
    double d13 = y + this.renderMinY;
    double d14 = y + this.renderMaxY;
    double d15 = z + this.renderMaxZ;
    if (this.renderFromInside)
    {
      d11 = x + this.renderMaxX;
      d12 = x + this.renderMinX;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    
    tessellator.getBuffer().pos(d11, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d12, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public void renderFaceXNeg(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinZ * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxZ * 16.0D);
    double d5 = sprite.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
    double d6 = sprite.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
    if (this.flipTexture)
    {
      double d7 = d3;
      d3 = d4;
      d4 = d7;
    }
    if ((this.renderMinZ < 0.0D) || (this.renderMaxZ > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinY < 0.0D) || (this.renderMaxY > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateNorth == 1)
    {
      d3 = sprite.getInterpolatedU(this.renderMinY * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateNorth == 2)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMinZ * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMaxZ * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateNorth == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinY * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMinX;
    double d12 = y + this.renderMinY;
    double d13 = y + this.renderMaxY;
    double d14 = z + this.renderMinZ;
    double d15 = z + this.renderMaxZ;
    if (this.renderFromInside)
    {
      d14 = z + this.renderMaxZ;
      d15 = z + this.renderMinZ;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    
    tessellator.getBuffer().pos(d11, d13, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d12, d14).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d12, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public void renderFaceXPos(double x, double y, double z, TextureAtlasSprite sprite, float red, float green, float blue, int bright)
  {
    Tessellator tessellator = Tessellator.getInstance();
    
    double d3 = sprite.getInterpolatedU(this.renderMinZ * 16.0D);
    double d4 = sprite.getInterpolatedU(this.renderMaxZ * 16.0D);
    if (this.field_152631_f)
    {
      d4 = sprite.getInterpolatedU((1.0D - this.renderMinZ) * 16.0D);
      d3 = sprite.getInterpolatedU((1.0D - this.renderMaxZ) * 16.0D);
    }
    double d5 = sprite.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
    double d6 = sprite.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
    if (this.flipTexture)
    {
      double d7 = d3;
      d3 = d4;
      d4 = d7;
    }
    if ((this.renderMinZ < 0.0D) || (this.renderMaxZ > 1.0D))
    {
      d3 = sprite.getMinU();
      d4 = sprite.getMaxU();
    }
    if ((this.renderMinY < 0.0D) || (this.renderMaxY > 1.0D))
    {
      d5 = sprite.getMinV();
      d6 = sprite.getMaxV();
    }
    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;
    if (this.uvRotateSouth == 2)
    {
      d3 = sprite.getInterpolatedU(this.renderMinY * 16.0D);
      d5 = sprite.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
      d4 = sprite.getInterpolatedU(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.uvRotateSouth == 1)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxZ * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinZ * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.uvRotateSouth == 3)
    {
      d3 = sprite.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
      d4 = sprite.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
      d5 = sprite.getInterpolatedV(this.renderMaxY * 16.0D);
      d6 = sprite.getInterpolatedV(this.renderMinY * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }
    double d11 = x + this.renderMaxX;
    double d12 = y + this.renderMinY;
    double d13 = y + this.renderMaxY;
    double d14 = z + this.renderMinZ;
    double d15 = z + this.renderMaxZ;
    if (this.renderFromInside)
    {
      d14 = z + this.renderMaxZ;
      d15 = z + this.renderMinZ;
    }
    int i = bright;
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    tessellator.getBuffer().pos(d11, d12, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d12, d14).tex(d4, d6).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, alpha).endVertex();
    tessellator.getBuffer().pos(d11, d13, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, alpha).endVertex();
  }
  
  public static RenderBlocks getInstance()
  {
    if (instance == null) {
      instance = new RenderBlocks();
    }
    return instance;
  }
}
