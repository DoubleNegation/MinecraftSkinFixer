# MinecraftSkinFixer

MinecraftSkinFixer is a simple program which allows old versions of Minecraft to download Skins from the new Skin servers.


---

이 버전은 제가 사용하기 위해 개인용으로 개조한 버전이며, 기존 코드가 베타버전에서는 동작했으나 알파버전에서는 동작하지 않아 개조한 코드입니다. 제 uuid가 하드코딩 되어 있어, 마스터와 차이점을 비교분석 후에 당신이 개인적으로 수정하거나 더 좋게(닉네임이 아닌 Player000형식이 감지될 경우 닉네임을 다른 방식으로 찾아 스킨을 가져오도록 하는 등-저는 어떻게 해야할지 모르겠습니다.)개조하셔서 사용하면 됩니다.
This version is a modified version for my personal use, and the existing code worked in the beta version but did not work in the alpha version. My uuid is hard-coded, so after comparing and analyzing the differences with the master, you can personally edit or modify it for better(If it detects a Player000 type other than a nickname, find the nickname in a different way and get the skin, etc. - I don't know what to do.)use.

---


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

