package com.themastergeneral.ctdpower.config;


import org.apache.logging.log4j.Level;

import scala.Int;
import net.minecraftforge.common.config.Configuration;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.proxy.CommonProxy;


public class Config 
{
    private static final String CATEGORY_GENERAL = "General";

    // This values below you can access elsewhere in your mod:
    public static int CoalRF = 75;
    public static int CoalCooldown = 70;
    public static int CoalBlockCooldown = 700;
    public static int CoalGeneratorMaxBuffer = 150000;
    public static int TierOneCableRF = 32;
    public static int TierTwoCableRF = 128;
    public static int TierThreeCableRF = 512;
    public static String ModVersion = CTDPower.VERSION+"-"+CTDPower.acceptedMinecraftVersions;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() 
    {
        Configuration cfg = CommonProxy.config;
        try 
        {
            cfg.load();
            initGeneralConfig(cfg);
        } 
        catch (Exception e1) 
        {
            CTDPower.logger.log(Level.ERROR, "Problem loading config file!", e1);
        }
        finally 
        {
            if (cfg.hasChanged()) 
            {
                cfg.save();
            }
        }
    }
    private static void initGeneralConfig(Configuration cfg) 
    {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration for the CTD Power mod.");
        ModVersion = cfg.getString("ModVersion", CATEGORY_GENERAL, ModVersion, "Internal. Don't need to mess with this.");
        //DoPaintBrushBreak = cfg.getBoolean("DoPaintBrushBreak", CATEGORY_GENERAL, DoPaintBrushBreak, "Set to false if you do not want your paint brushes to run out of paint after a random amount of time.");
        CoalRF = cfg.getInt("Coal RF/t", CATEGORY_GENERAL, CoalRF, 1, 65565, "How much RF/t you wish for coal to generator in a coal generator.");
        CoalCooldown = cfg.getInt("Coal Burn Time", CATEGORY_GENERAL, CoalCooldown, 1, 65565, "How many ticks will coal burn before being used up in a coal generator.");
        CoalBlockCooldown = cfg.getInt("Coal Block Burn Time", CATEGORY_GENERAL, CoalBlockCooldown, 1, 65565, "How many ticks will coal blocks burn before being used up in a coal generator.");
        CoalGeneratorMaxBuffer = cfg.getInt("Coal Generator Buffer Size", CATEGORY_GENERAL, CoalGeneratorMaxBuffer, 1, 30000000, "How much RF a coal generator will hold before becoming full.");
        TierOneCableRF = cfg.getInt("Tier One Cable Max RF", CATEGORY_GENERAL, TierOneCableRF, 1, 30000000, "How much RF the tier one cable can transfer");
        TierTwoCableRF = cfg.getInt("Tier Two Cable Max RF", CATEGORY_GENERAL, TierTwoCableRF, 1, 30000000, "How much RF the tier two cable can transfer");
        TierThreeCableRF = cfg.getInt("Tier Three Cable Max RF", CATEGORY_GENERAL, TierThreeCableRF, 1, 30000000, "How much RF the tier three cable can transfer");
    }
}
