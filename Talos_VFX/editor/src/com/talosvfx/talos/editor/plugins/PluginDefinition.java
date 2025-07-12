package com.talosvfx.talos.editor.plugins;

import java.util.ArrayList;

public class PluginDefinition {

    public String name;
    public String version;
    public String pluginProvider;
    public ArrayList<String> plugins;
    public ArrayList<String> customNodes;

    public PluginDefinition () {

    }

    @Override
    public String toString () {
        return "Name: " + name + "\n" +
                "Version: " + version + "\n" +
                "PluginProvider: " + pluginProvider + "\n" +
                "Plugins: " + stringArray(plugins) + "\n" +
                "CustomNodes: " + stringArray(customNodes);
    }

    private String stringArray (ArrayList<String> stringEntry) {
        StringBuilder buffer = new StringBuilder();
        for (String plugin : stringEntry) {
            buffer.append("\n\t").append(plugin);
        }
        return buffer.toString();
    }
}
