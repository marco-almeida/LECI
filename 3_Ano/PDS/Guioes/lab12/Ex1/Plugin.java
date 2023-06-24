package lab12.Ex1;

import java.io.File;
import java.util.ArrayList;

abstract class PluginManager {
    public static IPlugin load(String name) throws Exception {
        Class<?> c = Class.forName(name);
        return (IPlugin) c.getDeclaredConstructor().newInstance();
    }
}

public class Plugin {
    public static void main(String[] args) throws Exception {
        File proxyList = new File("out/production/labs/lab12/Ex1");

        ArrayList<IPlugin> plgs = new ArrayList<>();

        for (String f : proxyList.list()) {
            if (f.endsWith(".class")) {
                try {
                    plgs.add(PluginManager.load("lab12.Ex1." + f.substring(0, f.lastIndexOf('.'))));
                } catch (Exception e) {
                    System.out.println("\t" + f + ": Componente ignorado. Não é IPlugin.");
                }
            }
        }

        for (IPlugin plg : plgs) {
            plg.fazQualQuerCoisa();
        }
    }
}