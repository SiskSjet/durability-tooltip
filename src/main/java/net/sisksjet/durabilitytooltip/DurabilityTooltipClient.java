package net.sisksjet.durabilitytooltip;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class DurabilityTooltipClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, textList)->{
            var clientInstance = MinecraftClient.getInstance();
            var tooltips = Tooltip.appendTooltip(itemStack, tooltipContext);

            if(tooltips.size() > 0) {
                textList.addAll(tooltips);
            }
        });
    }
}
