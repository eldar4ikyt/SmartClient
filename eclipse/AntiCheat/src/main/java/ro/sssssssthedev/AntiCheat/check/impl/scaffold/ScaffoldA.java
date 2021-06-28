package ro.sssssssthedev.AntiCheat.check.impl.scaffold;

import ro.sssssssthedev.AntiCheat.alert.type.ViolationLevel;
import ro.sssssssthedev.AntiCheat.check.CheckData;
import ro.sssssssthedev.AntiCheat.check.type.PacketCheck;
import ro.sssssssthedev.AntiCheat.data.PlayerData;
import ro.sssssssthedev.AntiCheat.packet.type.WrappedPacket;
import ro.sssssssthedev.AntiCheat.packet.type.WrappedPacketPlayInBlockPlace;
import ro.sssssssthedev.AntiCheat.packet.type.WrappedPacketPlayInFlying;

@CheckData(name = "Scaffold (A)")
public final class ScaffoldA extends PacketCheck {
    private boolean sent;
    private long lastPlace, lastFlying;
    private double buffer;

    public ScaffoldA(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(final WrappedPacket packet) {
        if (packet instanceof WrappedPacketPlayInFlying) {
            final long now = System.currentTimeMillis();

            if (sent) {
                final long postDelay = now - lastPlace;

                if (postDelay > 40L && postDelay < 100L) {
                    buffer += 0.25;

                    if (buffer > 0.5) {
                        this.handleViolation().addViolation(ViolationLevel.HIGH).create();
                    }
                } else {
                    buffer = Math.max(buffer - 0.025, 0);
                }

                this.sent = false;
            }

            this.lastFlying = now;
        } else if (packet instanceof WrappedPacketPlayInBlockPlace) {
            final long now = System.currentTimeMillis();
            final long flyingDelay = now - lastFlying;

            if (flyingDelay < 10L) {
                this.sent = true;
                this.lastPlace = now;
            } else {
                buffer = Math.max(buffer - 0.025, 0);
            }
        }
    }
}
