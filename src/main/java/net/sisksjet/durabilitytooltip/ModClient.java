package net.sisksjet.durabilitytooltip;

import net.sisksjet.durabilitytooltip.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {
    public static final String MOD_ID = "durabilitytooltip";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ModConfig CONFIG = new ModConfig();

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, textList) -> {
            var tooltips = Tooltip.appendTooltip(itemStack, tooltipContext);

            if (tooltips.size() > 0) {
                textList.addAll(tooltips);
            }
        });
    }
}
