# shsmp-spigot
A new custom Spigot plugin for Semi-Hardcore Survival Multiplayer Worlds.

This time updated and move to Gradle.


## Prerequisites
- Java 17+ (JRE)
- Minecraft Version 1.20.4
  
## Configuration
There are multiple elements that you can customize or change in `config.yml` once the `SHSMP` folder has
been created:
___
### DiscordWebhook
**Type:** `String`  
Sends a message whenever a Necronomicon has been crafted or used via Discord Webhook

### LightNecronomicon
**Type:** `Boolean`  
**Default:** `false`  
Set to `true` to make Necronomicon recipe use 4 Ench Gapples rather than 8.


## Building Localy
*Nix:
`./gradlew shadowJar`

Windows:
`./gradlew.bat shadowJar`
