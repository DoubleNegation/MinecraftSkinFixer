# MinecraftSkinFixer

MinecraftSkinFixer is a simple program which allows old versions of Minecraft to download Skins from the new Skin servers.  
~~The [Minecraft Forum Thread](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-tools/2923190-minecraftskinfixer-skins-in-old-minecraft-versions) contains more information.~~  
*The Minecraft Forum Thread dissappeared around December 2018/January 2019, together with my Minecraft Forum Account.*

**NOTE: Versions 2.2 and 2.3 were removed.**  
This is because the mods for 1.6.2, 1.6.4 and 1.7.2 which shipped with them did not comply with Minecraft's EULA.  
I hope to eventually release a version 2.5 with new, compliant mods.  
*And in case you are wondering: 1.x, 2.0 and 2.1 were never released in the first place.*

---

### Supported Minecraft Versions

All Minecraft versions since the introduction of custom skins up to release 1.5.2 are supported.  
However, if you are using a version between release 1.0 and release 1.2.5, you need to supply the `--flip-bottoms=off` parameter when launching the program to make the skins render correctly.

---

### Usage instructions:
1. Download the MinecraftSkinFixer .jar file from the releases tab
2. Start the Minecraft launcher
3. Start MinecraftSkinFixer
4. If this is your first time using MinecraftSkinFixer, configure your launcher profile as instructed by the program
5. Launch Minecraft

---

The main program has two dependencies:
* [Non-standard JRE HTTP server](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/index.html) (included in most JREs)
* [org.json:json:20150729](https://search.maven.org/artifact/org.json/json/20150729/jar) (included in the release jars)

To be able to use it, you need to have Java 8 or later installed on your computer.

