package ro.sssssssthedev.AntiCheat.packet.register;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayOutTransaction;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import ro.sssssssthedev.AntiCheat.AntiCheatAPI;
import ro.sssssssthedev.AntiCheat.data.PlayerData;
import ro.sssssssthedev.AntiCheat.packet.handlers.PacketHandler1_8_R3;

public final class PacketRegister1_8_R3 {

    public PacketRegister1_8_R3(final PlayerData playerData) {
        final EntityPlayer entityPlayer = ((CraftPlayer) playerData.getPlayer()).getHandle();

        AntiCheatAPI.INSTANCE.getPacketExecutor().execute(() -> new PacketHandler1_8_R3(entityPlayer.server, entityPlayer.playerConnection.networkManager, entityPlayer, playerData));
    }

    public void sendTransactionPacket(final PlayerData playerData, final short action) {
        final PacketPlayOutTransaction transaction = new PacketPlayOutTransaction((byte) 0, action, false);

        ((CraftPlayer) playerData.getPlayer()).getHandle().playerConnection.sendPacket(transaction);
    }

    public void sendKeepAlivePacket(final PlayerData playerData, int action) {
        final PacketPlayOutKeepAlive keepAlive = new PacketPlayOutKeepAlive(action);

        ((CraftPlayer) playerData.getPlayer()).getHandle().playerConnection.sendPacket(keepAlive);
    }
}
