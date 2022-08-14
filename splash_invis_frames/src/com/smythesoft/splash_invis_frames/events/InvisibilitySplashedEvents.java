package com.smythesoft.splash_invis_frames.events;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.List;

public class InvisibilitySplashedEvents implements Listener {
    @EventHandler
    public static void onSplashEffect (PotionSplashEvent event)
    {
        ThrownPotion potion = event.getPotion();
        PotionMeta potionMeta = (PotionMeta)potion.getItem().getItemMeta();

        if (potionMeta.getBasePotionData().getType().equals(PotionType.INVISIBILITY)){
            setVisibility(potion, false, 4, 2);
        } else if (potionMeta.getBasePotionData().getType().equals(PotionType.AWKWARD)) {
            setVisibility(potion, true, 4, 2);
        }
    }

    @EventHandler
    public static void onLingeringEffect (AreaEffectCloudApplyEvent event)
    {
        AreaEffectCloud areaEffectCloud = event.getEntity();
        PotionType potionType = areaEffectCloud.getBasePotionData().getType();
        if (potionType == PotionType.INVISIBILITY) {
            setVisibility(areaEffectCloud, false, areaEffectCloud.getRadius(), areaEffectCloud.getHeight()/2);
        }
        // Awkward potion doesn't leave an AreaEffectCloud with potion data so can't use it for removing invisibility
    }

    private static void setVisibility(Entity potionEntity, boolean isVisible, double horizontalRadius, double verticalHeight){
        List<Entity> affectedEntities = potionEntity.getNearbyEntities(horizontalRadius, verticalHeight, horizontalRadius);
        for (Entity entity: affectedEntities) {
            if (entity.getType() == EntityType.ITEM_FRAME){
                ItemFrame itemFrame = (ItemFrame) entity;
                itemFrame.setVisible(isVisible);
            } else if (entity.getType() == EntityType.GLOW_ITEM_FRAME){
                GlowItemFrame glowItemFrame = (GlowItemFrame) entity;
                glowItemFrame.setVisible(isVisible);
            } else if (entity.getType() == EntityType.ARMOR_STAND) {
                ArmorStand armorStand = (ArmorStand) entity;
                armorStand.setVisible(isVisible);
            }
        }
    }
}
