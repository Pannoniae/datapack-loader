package net.panno.datapackLoader.mixin;

import net.fabricmc.loader.FabricLoader;
import net.minecraft.resource.FileResourcePackCreator;
import net.minecraft.resource.ResourcePackContainer;
import net.minecraft.resource.ResourcePackContainerManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Shadow
	private ResourcePackContainerManager<ResourcePackContainer> field_4595;

	@Inject(method = "method_3800",
			at = @At(value = "INVOKE",
			target = "Lnet/minecraft/resource/ResourcePackContainerManager;addCreator(Lnet/minecraft/resource/ResourcePackCreator;)V",
					ordinal = 1))
	public void method_3800(File file, LevelProperties properties, CallbackInfo info) {
		field_4595.addCreator(new FileResourcePackCreator(new File(FabricLoader.INSTANCE.getGameDirectory(), "datapacks")));
	}
}
