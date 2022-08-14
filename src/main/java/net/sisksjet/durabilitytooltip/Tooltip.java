package net.sisksjet.durabilitytooltip;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Collection;

public class Tooltip {

    public static final String BAR_FULL_SYMBOL = "█";
    public static final String BAR_EMPTY_SYMBOL = "▒";
    public static final String BAR_LINE = "[%s]";

    private static Formatting getFormatForDurability(int durability, int maxDurability) {
        if (durability >= 0.6f * maxDurability) {
            return Formatting.GREEN;
        }

        if (durability >= 0.4f * maxDurability) {
            return Formatting.YELLOW;
        }

        if (durability >= 0.2f * maxDurability) {
            return Formatting.GOLD;
        }

        return Formatting.RED;
    }

    public static Collection<Text> appendTooltip(ItemStack itemStack, TooltipContext tooltipContext) {
        var tooltips = new ArrayList<Text>();
        var baseFormat = Formatting.GRAY;
        var showTooltipHint = ModClient.CONFIG.ShowTooltipHint;
        var maxDurability = itemStack.getMaxDamage();
        var durability = maxDurability - itemStack.getDamage();
        if (maxDurability > 0) {
            if (showTooltipHint) {
                tooltips.add(Text.translatable("durabilitytooltip.info.bar.durability_hint").formatted(baseFormat));
            }

            int fullCharacters = Math.round(10f * durability / maxDurability);
            var inner = Text.literal("");
            for (var i = 0; i < 10; i++) {
                inner.append(Text.translatable(i < fullCharacters ? BAR_FULL_SYMBOL : BAR_EMPTY_SYMBOL))
                        .formatted(getFormatForDurability(durability, maxDurability));
            }

            var bar = Text.translatable(BAR_LINE, inner).formatted(baseFormat);
            tooltips.add(bar);
        }

        return tooltips;
    }
}
