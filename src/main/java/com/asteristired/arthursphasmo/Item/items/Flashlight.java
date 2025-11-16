package com.asteristired.arthursphasmo.Item.items;

import com.asteristired.arthursphasmo.Item.components.ModComponents;
import com.asteristired.arthursphasmo.Sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class Flashlight extends Item {
    public Flashlight(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.arthursphasmo.electric").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("tooltip.arthursphasmo.flashlight").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);

        if (this.getComponents().get(ModComponents.SWITCHED_ON)!=null && !user.getItemCooldownManager().isCoolingDown(ModItems.FLASHLIGHT)) {
            // get component
            boolean is_on = Boolean.TRUE.equals(item.getComponents().get(ModComponents.SWITCHED_ON));
            item.set(ModComponents.SWITCHED_ON, !is_on);
            user.getItemCooldownManager().set(ModItems.FLASHLIGHT, 10);

            if (!world.isClient()) {
                ServerWorld serverWorld = (ServerWorld) world;
                serverWorld.playSound(null, user.getBlockPos(), ModSounds.FLASHLIGHT_CLICK_EVENT, SoundCategory.PLAYERS);
            }
        }

        return super.use(world, user, hand);
    }
}
