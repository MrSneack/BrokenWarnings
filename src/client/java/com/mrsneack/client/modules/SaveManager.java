package com.mrsneack.client.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SaveManager {
    private static final int DEFAULT_DURABILITY_PERCENTAGE = 30;

    public static class BrokenWarningsData {
        public boolean debug = true;
        public int durabilityPercentage = DEFAULT_DURABILITY_PERCENTAGE;
        public boolean isStopEnabled = false;
    }

    private final DebugTraceManager traceManager = new DebugTraceManager();
    private final Path path = FabricLoader.getInstance().getConfigDir().resolve("BrokenWarnings.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private BrokenWarningsData brokenWarningsData = new BrokenWarningsData();

    public void save() {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                gson.toJson(brokenWarningsData, writer);
            }
        } catch (IOException e) {
            traceManager.error("Error saving BrokenWarnings.json: " + e.getMessage());
        }
    }

    public BrokenWarningsData load() {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (Files.notExists(path)) {
                brokenWarningsData = new BrokenWarningsData();
                save();
                return brokenWarningsData;
            }

            try (BufferedReader reader = Files.newBufferedReader(path)) {
                BrokenWarningsData loaded = gson.fromJson(reader, BrokenWarningsData.class);
                if (loaded == null) {
                    recoverInvalidConfig();
                    return brokenWarningsData;
                }

                brokenWarningsData = loaded;
                if (brokenWarningsData.durabilityPercentage < 1
                        || brokenWarningsData.durabilityPercentage > 100) {
                    traceManager.warn("Invalid durabilityPercentage in BrokenWarnings.json; clamping to 1..100");
                    brokenWarningsData.durabilityPercentage =
                            Math.max(1, Math.min(100, brokenWarningsData.durabilityPercentage));
                    save();
                }

                return brokenWarningsData;
            }
        } catch (JsonParseException e) {
            traceManager.error("Invalid JSON in BrokenWarnings.json: " + e.getMessage());
            recoverInvalidConfig();
            return brokenWarningsData;
        } catch (IOException e) {
            traceManager.error("Error loading BrokenWarnings.json: " + e.getMessage());
            brokenWarningsData = new BrokenWarningsData();
            return brokenWarningsData;
        }
    }

    private void recoverInvalidConfig() {
        brokenWarningsData = new BrokenWarningsData();

        try {
            if (Files.exists(path)) {
                Path backupPath = path.resolveSibling(path.getFileName() + ".corrupt");
                Files.copy(path, backupPath, StandardCopyOption.REPLACE_EXISTING);
            }
            save();
        } catch (IOException e) {
            // Keep the original file intact when it cannot be backed up.
            traceManager.error("Unable to back up invalid BrokenWarnings.json: " + e.getMessage());
        }
    }
}
