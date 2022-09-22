/*
 * KirisLib - https://github.com/tophatcats-mods/kiris-lib
 * Copyright (C) 2013-2022 <KiriCattus>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * Specifically version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */
package dev.tophatcat.kirislib.mixins;

import dev.tophatcat.kirislib.common.blocks.BasicLogBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Mixin our own BasicLogBlock class to add the Forge only method to enable log stripping.
 */
@Mixin(value = BasicLogBlock.class, remap = false)
public abstract class MixinLogs extends RotatedPillarBlock {

    /**
     * The stripped variant of the log taken from the constructor for use on Forge, ignored on Quilt.
     */
    @Shadow
    Supplier<? extends Block> strippedLogBlock;

    /**
     * @param properties       Our blocks properties.
     * @param strippedLogBlock The stripped variant of the current log.
     */
    @SuppressWarnings("unused")
    public MixinLogs(final BlockBehaviour.Properties properties, final Supplier<? extends Block> strippedLogBlock) {
        super(properties);
    }

    /**
     * @param state      The current state.
     * @param context    The use on context that the action was performed in.
     * @param toolAction The action being performed by the tool.
     * @param simulate   If {@code true}, no actions that modify the world in any way should be performed.
     *                   If {@code false}, the world may be modified.
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context,
                                           ToolAction toolAction, boolean simulate) {
        ItemStack itemStack = context.getItemInHand();
        if (itemStack.canPerformAction(toolAction) && ToolActions.AXE_STRIP.equals(toolAction)) {
            return strippedLogBlock.get().defaultBlockState().setValue(BasicLogBlock.AXIS,
                state.getValue(BasicLogBlock.AXIS));
        }
        return null;
    }
}
