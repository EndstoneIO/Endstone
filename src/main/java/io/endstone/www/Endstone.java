package io.endstone.www;


import io.endstone.www.config.EndstoneConfig;

import java.io.File;

public class Endstone extends Thread {

    public static void main(String[] args) {
        Endstone endstone = new Endstone();
        endstone.start();
    }

    public static final File originDir = new File("./origin");
    public static final File modulesDir = new File("./modules");
    public static final File configDir = new File("./config");

    private EndstoneConfig config;

    public Endstone() {
        this.config = new EndstoneConfig("Endstone");
    }

    @Override
    public void run() {

        loadOrigin();

        loadModules();

        excuteModules();

        launchOrigin();
    }

    private void loadOrigin() {

    }

    private void loadModules() {

    }

    private void excuteModules() {

    }

    private void launchOrigin() {

    }
}
