package nx.pingwheel.common.config;

import net.minecraft.client.MinecraftClient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nx.pingwheel.common.networking.SideChannelNetworkHandler;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientConfig implements IConfig {
	int pingVolume = 100;
	int pingDuration = 7;
	int pingDistance = 2048;
	float correctionPeriod = 1f;
	boolean itemIconVisible = true;
	boolean directionIndicatorVisible = true;
	boolean nameLabelForced = false;
	int pingSize = 100;
	String uri = "amqp://127.0.0.1:5672";
	String channel = "";

	// hidden from settings screen
	int raycastDistance = 1024;
	int safeZoneLeft = 5;
	int safeZoneRight = 5;
	int safeZoneTop = 5;
	int safeZoneBottom = 60;

	public static final Integer MAX_CHANNEL_LENGTH = 128;

	public void validate() {
		if (channel.length() > MAX_CHANNEL_LENGTH) {
			channel = channel.substring(0, MAX_CHANNEL_LENGTH);
		}
	}

	public void onUpdate() {
		SideChannelNetworkHandler.getInstance().close();
		MinecraftClient client = MinecraftClient.getInstance();
		if (client == null) {
			return;
		}
		if (client.isInSingleplayer() || client.getCurrentServerEntry() != null) {
			SideChannelNetworkHandler.getInstance().connect(uri);
		}
	}
}
