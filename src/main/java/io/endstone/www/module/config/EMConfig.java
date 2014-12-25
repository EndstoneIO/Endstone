package io.endstone.www.module.config;

import io.endstone.www.annotation.EModule;
import io.endstone.www.config.ESConfig;
import io.endstone.www.config.EndstoneConfig;

@EModule(name = "emConfig")
public class EMConfig implements IemConfig {

    @Override
    public ESConfig get(String name) {
        return new EndstoneConfig(name);
    }
}
