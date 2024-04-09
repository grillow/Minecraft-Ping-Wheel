<p align="center" style="text-align: center">
  <a href="https://github.com/LukenSkyne/Minecraft-Ping-Wheel">
    <img alt="Ping Wheel Logo" src=".github/icon.png" width="128" height="128" />
  </a>
</p>

<h1 align="center">Ping Wheel</h1>
<h3 align="center">ease communication with your friends by pinging locations</h3>
<br>

<div align="center">

<a href="#">![Fabric](https://luken.cc/badges/fabric)</a>

</div>

## Fork

[Original mod](https://github.com/LukenSkyne/Minecraft-Ping-Wheel) uses the minecraft protocol to exchange packets between the client and the server. So it's impossible to use this mod without having it installed on the server side.

[This fork](https://github.com/grillow/Minecraft-Ping-Wheel) makes clients exchange ping packets using a *side channel* (RabbitMQ), which allows it to be used on servers that do not have this mod installed.

### Build

```sh
gradle build
```

The ```.jar``` file can be found in the ```fabric/build/libs``` directory.

### Configuration

1. Start RabbitMQ

```docker run -d --name rabbitmq -p 127.0.0.1:5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:latest```

2. Set the correct side channel URI in the mod configuration menu (example: ```amqp://user:password@127.0.0.1:5672```)

Make sure to change ```127.0.0.1``` to the correct IP address.

## About

Modern co-op games often allow players to communicate via "pings" to mark in-game locations.  
This simple mod provides such pinging mechanism for the fabric and forge mod loaders.

Default keybind is "Mouse5" aka "Forward" to ping.  
The settings can be changed via `/pingwheel config` or [Mod Menu](https://github.com/TerraformersMC/ModMenu).

If you are looking to use the mod on Bukkit servers, you can install our [official Plugin](https://github.com/RXJpaw/Minecraft-Ping-Wheel-Plugin/).

## Gallery

The following screenshot shows two pings in different distances with according scale.

<img src=".github/in-game-screenshot.png" alt="In-Game">

When enabled, this feature replaces the ping icon with the respective item texture (or model).

<img src=".github/item-icon-showcase.png" alt="Item-Icons">

## Dependencies

<p>
  <a href="https://github.com/FabricMC/fabric">
    <img alt="Fabric API" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/requires/fabric-api_vector.svg">
  </a>
</p>
