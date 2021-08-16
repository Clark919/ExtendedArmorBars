package com.rebelkeithy.extendedarmorbars.mixin;

import com.rebelkeithy.extendedarmorbars.ToughnessBar;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Inject(at = @At("TAIL"), method = "renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V")
	private void init(MatrixStack matrices, CallbackInfo info) {
		ToughnessBar.render(matrices);
	}
}
