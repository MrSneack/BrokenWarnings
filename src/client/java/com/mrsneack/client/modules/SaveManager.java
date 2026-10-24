package com.mrsneack.client.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveManager {
    public static class BrokenWarningsData {
        public boolean debug = false;
    }
    private final Path path = FabricLoader.getInstance().getConfigDir().resolve("BrokenWarnings.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private BrokenWarningsData brokenWarningsData;

    public void save() {
        try {
            Path parent = path.getParent();

            if(parent != null)
                Files.createDirectories(parent);

            try(BufferedWriter writer = Files.newBufferedWriter(path)) {
                gson.toJson(brokenWarningsData, writer);
            }
        } catch (IOException e) {
            System.err.println("[BrokenWarnings] Failed to save");
            e.printStackTrace();
        }
    }

    public BrokenWarningsData load() {
        try {
            Path parent = path.getParent();
            if(parent != null)
                Files.createDirectories(parent);

            if(Files.notExists(path)) {
                brokenWarningsData = new BrokenWarningsData();
                save();
                return brokenWarningsData;
            }

            try(BufferedReader reader = Files.newBufferedReader(path)) {
                BrokenWarningsData loader = gson.fromJson(reader, brokenWarningsData.getClass());
                brokenWarningsData = loader != null ? loader : new BrokenWarningsData();

                return brokenWarningsData;
            }
        }
        catch (IOException e) {
            System.err.println("[BrokenWarnings] Failed to load");
            e.printStackTrace();

            brokenWarningsData = new  BrokenWarningsData();
            return brokenWarningsData;
        }
    }

    public BrokenWarningsData getBrokenWarningsData() {
        return brokenWarningsData;
    }
}
