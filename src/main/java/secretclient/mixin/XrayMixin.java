package secretclient.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import secretclient.SecretClient;

@Mixin(Block.class)
public class XrayMixin {
    @Inject(method = "shouldDrawSide", cancellable = true, at = @At("HEAD"))
    private static void shouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction side,
            BlockPos otherPos,
            CallbackInfoReturnable<Boolean> cir) {
        if (SecretClient.XrayEnabled) {
            cir.setReturnValue(false);
        }
    }
}
