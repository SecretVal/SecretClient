package secretclient.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "hasStatusEffect")
    private void fakeHasStatusEffect() {

    }

}
