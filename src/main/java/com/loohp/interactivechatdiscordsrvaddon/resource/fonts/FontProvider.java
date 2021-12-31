package com.loohp.interactivechatdiscordsrvaddon.resource.fonts;

import java.util.Collections;
import java.util.List;

import com.loohp.interactivechat.libs.org.apache.commons.lang.StringEscapeUtils;

public class FontProvider {
	
	private String key;
	private List<MinecraftFont> providers;

	public FontProvider(String key, List<MinecraftFont> providers) {
		this.key = key;
		this.providers = Collections.unmodifiableList(providers);
	}

	public String getNamespacedKey() {
		return key;
	}

	public List<MinecraftFont> getProviders() {
		return providers;
	}
	
	public void reloadFonts() {
		for (MinecraftFont fonts : providers) {
			fonts.reloadFonts();
		}
	}
	
	public MinecraftFont forCharacterOrNull(String character) {
		for (MinecraftFont font : providers) {
			if (font.canDisplayCharacter(character)) {
				return font;
			}
		}
		return null;
	}
	
	public MinecraftFont forCharacter(String character) {
		MinecraftFont font = forCharacterOrNull(character);
		if (font != null) {
			return font;
		}
		throw new RuntimeException("No font provider can display the character \"" + character + "\" (" + StringEscapeUtils.escapeJava(character) + ") for the font \"" + key + "\", this is likely due to an issue with your resource pack setup.");
	}

}