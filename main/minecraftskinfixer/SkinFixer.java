package minecraftskinfixer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;


// is added
import java.io.*;


public class SkinFixer {
	
	/**
	 * 0 = automatically decide if bottom sides of skins need to be flipped.<br>
	 *     does not work for versions from release 1.0 to release 1.2.5<br>
	 * 1 = don't convert skins<br>
	 * 2 = never flip bottom sides of skin parts<br>
	 * 3 = always flip bottom sides of skin parts<br>
	 */
	public static byte CONVERT_MODE = 0;
	
	private static SuperSimpleLogger logger;
	private static HashMap<String, byte[]> remoteFileCache = new HashMap<String, byte[]>();
	
	public static void gimmeLogger(SuperSimpleLogger logger) {
		SkinFixer.logger = logger;
	}
	
	public static void runSkinFixer(String connectionId, HttpExchange exchange, String hostname, String url) throws IOException {
		byte[] imageFile = doRunSkinFixer(connectionId, hostname, url);
		if(imageFile.length != 0) {
			exchange.getResponseHeaders().add("Content-Type", "image/png");
			exchange.sendResponseHeaders(200, imageFile.length);
			exchange.getResponseBody().write(imageFile);
			exchange.close();
		} else {
			exchange.sendResponseHeaders(404, 0);
			exchange.close();
		}
		logger.log("[Skin Fixer] " + connectionId + "Request served successfully. HTTP Connection to game closed.");
	}
	
	private static byte[] doRunSkinFixer(String connectionId, String hostname, String url) throws IOException {
		String username;
		String textureType;
		boolean autoWouldFlipBottoms;
		if(hostname.equals("www.minecraft.net") && url.startsWith("/skin/")) {
			username = url.substring("/skin/".length(), url.length() - ".png".length());
			textureType = "SKIN";
			// is not work!
			// /* this is added part */
			// if("Player" == username.substring(0, 6)) {
			// 	username = "sunung0110";
			// }
			//  /* this is added part */
			String MinecraftFandomWikiIsFuckers;
			String Minecraft_sOldestSkinApplyVersinIs_1_1_2_01;
			Minecraft_sOldestSkinApplyVersinIs_1_1_2_01 = "Player";
			MinecraftFandomWikiIsFuckers = username.substring("".length(), Minecraft_sOldestSkinApplyVersinIs_1_1_2_01.length());
			if(MinecraftFandomWikiIsFuckers.equals(Minecraft_sOldestSkinApplyVersinIs_1_1_2_01)) {
				logger.log("[Skin Fixer] " + connectionId + "Username changing is required!!");
				String FuckingMinecraftFandomWiki = "C:\\Users\\Bear\\AppData\\Roaming\\.minecraft\\launcher_profiles.json";
				String NoMinecraftEULAViolations = "";
				String MinecraftFandomWikiIsMotherFuckers;
				String Minecraft_sOldestSkinApplyVersinIsNOT_1_2_2;
				MinecraftFandomWikiIsMotherFuckers = "displayName";
				Minecraft_sOldestSkinApplyVersinIsNOT_1_2_2 = "";
				logger.log("11111111111111111111");
				
				try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FuckingMinecraftFandomWiki),"ISO-8859-1"))) 
				{
				    while ((NoMinecraftEULAViolations = br.readLine()) != null) {
						logger.log("22222222222222222222222");
						Minecraft_sOldestSkinApplyVersinIsNOT_1_2_2 = NoMinecraftEULAViolations.substring("          _".length(), "          _displayName".length());
						logger.log(Minecraft_sOldestSkinApplyVersinIsNOT_1_2_2);
						if(MinecraftFandomWikiIsMotherFuckers.equals(Minecraft_sOldestSkinApplyVersinIsNOT_1_2_2)){
							logger.log("33333333333333333333333");
							break;
						}
				    }
				}
				catch (IOException e) {
				    e.printStackTrace();
					logger.log("An error occurred.");
				}
				
				
				username = NoMinecraftEULAViolations.substring("          _displayName_ : _".length(), NoMinecraftEULAViolations.length() - "_".length());
				logger.log("444444444444444444");
			}
			autoWouldFlipBottoms = true;
			logger.log("[Skin Fixer] " + connectionId + "Received Generation 1 request for skin of player " + username);
		} else if(hostname.equals("s3.amazonaws.com") && url.startsWith("/MinecraftSkins/")) {
			username = url.substring("/MinecraftSkins/".length(), url.length() - ".png".length());
			textureType = "SKIN";
			autoWouldFlipBottoms = true;
			logger.log("[Skin Fixer] " + connectionId + "Received Generation 2 request for skin of player " + username);
		} else if(hostname.equals("s3.amazonaws.com") && url.startsWith("/MinecraftCloaks/")) {
			username = url.substring("/MinecraftCloaks/".length(), url.length() - ".png".length());
			textureType = "CAPE";
			autoWouldFlipBottoms = true;
			logger.log("[Skin Fixer] " + connectionId + "Received Generation 2 request for cape of player " + username);
		} else if(hostname.equals("skins.minecraft.net") && url.startsWith("/MinecraftSkins/")) {
			username = url.substring("/MinecraftSkins/".length(), url.length() - ".png".length());
			textureType = "SKIN";
			autoWouldFlipBottoms = false;
			logger.log("[Skin Fixer] " + connectionId + "Received Generation 3 request for skin of player " + username);
		} else if(hostname.equals("skins.minecraft.net") && url.startsWith("/MinecraftCloaks/")) {
			username = url.substring("/MinecraftCloaks/".length(), url.length() - ".png".length());
			textureType = "CAPE";
			autoWouldFlipBottoms = false;
			logger.log("[Skin Fixer] " + connectionId + "Received Generation 3 request for cape of player " + username);
		} else {
			throw new RuntimeException();
		}
		String uuid = new JSONObject(new String(getRemoteFile("https://api.mojang.com/users/profiles/minecraft/" + username, connectionId))).getString("id"); // is original
		// String uuid = "e07d3e7231ba4de0846132d29a51c7aa"; // is edditted
		
		if(uuid.equals("3f92defab3ec4d0389d6a0abd4a6ed01")){
			logger.log("GET OUT MOTHERFUCKER!!!!!!!!!");
			return new byte[0];
		}
		else{
			logger.log("[Skin Fixer] " + connectionId + "UUID of player " + username + " is " + uuid);
			JSONObject profileData = new JSONObject(new String(getRemoteFile("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid, connectionId)));
			JSONArray profileProperties = profileData.getJSONArray("properties");
			JSONObject textureProperties = null;
			for(Object o : profileProperties) {
				if(o instanceof JSONObject) {
					JSONObject obj = (JSONObject)o;
					if(obj.has("name") && obj.get("name").equals("textures")) {
						textureProperties = obj;
					}
				}
			}
			if(textureProperties == null) {
				logger.log("[Skin Fixer] " + connectionId + "Player does not have texture properties.");
				return new byte[0];
			}
			String texturePropsStr = textureProperties.getString("value");
			JSONObject texProps = new JSONObject(new String(Base64.getDecoder().decode(texturePropsStr)));
			if(!(texProps.has("textures") && texProps.get("textures") instanceof JSONObject)) {
				logger.log("[Skin Fixer] " + connectionId + "Texture properties are missing the textures object.");
				return new byte[0];
			}
			JSONObject textures = texProps.getJSONObject("textures");
			if(!(textures.has(textureType) && textures.get(textureType) instanceof JSONObject)) {
				logger.log("[Skin Fixer] " + connectionId + "Player doesn't have a " + textureType.toLowerCase());
				return new byte[0];
			}
			JSONObject texture = textures.getJSONObject(textureType);
			if(!(texture.has("url") && texture.get("url") instanceof String)) {
				logger.log("[Skin Fixer] " + connectionId + "Player doesn't have a " + textureType.toLowerCase());
				return new byte[0];
			}
			String textureUrl = texture.getString("url");
			String textureId = textureUrl.substring("http://textures.minecraft.net/texture/".length());
			logger.log("[Skin Fixer] " + connectionId + "ID of " + textureType.toLowerCase() + " texture is " + textureId);
			if(textureType.equals("SKIN") && CONVERT_MODE != 1) {
				byte[] skinFile = getRemoteFile(textureUrl, connectionId);
				BufferedImage skinImage = ImageIO.read(new ByteArrayInputStream(skinFile));
				boolean slim = false;
				if(texture.has("metadata") && texture.get("metadata") instanceof JSONObject) {
					JSONObject metadata = texture.getJSONObject("metadata");
					if(metadata.has("model") && metadata.get("model") instanceof String && metadata.getString("model").equals("slim")) {
						slim = true;
					}
				}
				boolean flipBottoms = CONVERT_MODE == 0 ? autoWouldFlipBottoms : CONVERT_MODE == 3;
				BufferedImage convertedSkinImage = SkinConverter.convert(skinImage, slim, flipBottoms, logger, connectionId);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(convertedSkinImage, "PNG", baos);
				return baos.toByteArray();
			} else {
				return getRemoteFile(textureUrl, connectionId);
			}
		}
	}
	
	private static byte[] getRemoteFile(String url, String connectionId) throws IOException {
		if(remoteFileCache.containsKey(url)) {
			logger.log("[Download] " + connectionId + "No need to download " + url + " - file is stored in cache.");
			return remoteFileCache.get(url);
		} else {
			logger.log("[Download] " + connectionId + "Downloading " + url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			URLConnection conn = new URL(url).openConnection();
			InputStream is = conn.getInputStream();
			byte[] buff = new byte[4096];
			int read;
			while((read = is.read(buff)) != -1) {
				baos.write(buff, 0, read);
			}
			byte[] file = baos.toByteArray();
			baos.close();
			logger.log("[Download] " + connectionId + "Successfully downloaded " + url + " (" + file.length + " bytes)");
			remoteFileCache.put(url, file);
			return file;
		}
	}

}
