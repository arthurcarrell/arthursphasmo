package com.asteristired.arthursphasmo.Item.items.debug;

import com.asteristired.arthursphasmo.Entity.GhostEntity;
import com.asteristired.arthursphasmo.Entity.ModEntities;
import com.asteristired.arthursphasmo.Enums.GhostBehaviorState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Optional;

public class GhostTrigger extends Item {
    private final GhostBehaviorState state;
    public GhostTrigger(Settings settings, GhostBehaviorState setstate) {
        super(settings);
        state = setstate;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // get the ghost
        Optional<GhostEntity> ghost = Optional.empty();
        if (!world.isClient) {
            ghost = ModEntities.getGhost(user, world);
        }

        // do check
        if (ghost.isPresent()) {
            ghost.get().setGhostState(state);
            user.sendMessage(Text.literal("Overwrote Ghost state"), true);
        } else {
            user.sendMessage(Text.literal("No Ghost detected"), true);
        }
        return TypedActionResult.success(user.getStackInHand(hand), false);
    }
}
