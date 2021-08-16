package com.rebelkeithy.extendedarmorbars;

import com.rebelkeithy.extendedarmorbars.config.Config;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ModMenuImpl implements ModMenuApi {

    public Text categoryTest(String str) {
        return new TranslatableText("category.extendedarmorbars.toughnessbar." + str);
    }

    public Text optionText(String str) {
        return new TranslatableText("option.extendedarmorbars.toughnessbar." + str);
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("Test Title"));

            ConfigCategory category = builder.getOrCreateCategory(categoryTest("general"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            Config defaultConfig = new Config();
            category.addEntry(entryBuilder.startStrList(optionText("colors"), ToughnessBar.config.getColors())
                    .setDefaultValue(defaultConfig.getColors())
                    .setTooltip(optionText("colors.tooltip"))
                    .setSaveConsumer(list -> {
                        ToughnessBar.config.setColors(list);
                        ToughnessBar.saveAndReloadConfig();
                    })
                    .build());

            category.addEntry(entryBuilder.startBooleanToggle(optionText("armorenable"), ToughnessBar.config.isArmorEnable())
                    .setDefaultValue(defaultConfig.isArmorEnable())
                    .setSaveConsumer(value -> {
                        ToughnessBar.config.setArmorEnable(value);
                        ToughnessBar.saveAndReloadConfig();
                    })
                    .build());

            category.addEntry(entryBuilder.startBooleanToggle(optionText("toughnessenable"), ToughnessBar.config.isToughnessEnable())
                    .setDefaultValue(defaultConfig.isToughnessEnable())
                    .setSaveConsumer(value -> {
                        ToughnessBar.config.setToughnessEnable(value);
                        ToughnessBar.saveAndReloadConfig();
                    })
                    .build());

            category.addEntry(entryBuilder.startBooleanToggle(optionText("toughnesshidebackground"), ToughnessBar.config.isToughnessHideEmptySlots())
                    .setDefaultValue(defaultConfig.isToughnessHideEmptySlots())
                    .setSaveConsumer(value -> {
                        ToughnessBar.config.setToughnessHideEmptySlots(value);
                        ToughnessBar.saveAndReloadConfig();
                    })
                    .build());

            category.addEntry(entryBuilder.startBooleanToggle(optionText("toughnesshidewhenempty"), ToughnessBar.config.isToughnessHideWhenEmpty())
                    .setDefaultValue(defaultConfig.isToughnessHideWhenEmpty())
                    .setSaveConsumer(value -> {
                        ToughnessBar.config.setToughnessHideEmptySlots(value);
                        ToughnessBar.saveAndReloadConfig();
                    })
                    .build());

            return builder.build();
        };
    }
}