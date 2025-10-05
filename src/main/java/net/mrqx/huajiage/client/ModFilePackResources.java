package net.mrqx.huajiage.client;

import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathPackResources;

import java.nio.file.Path;

/**
 * This class is based on the original work from Create by Creators-of-Create.
 * <p>
 * Original source: <a href="https://github.com/Creators-of-Create/Create/blob/mc1.19/0.5.1/src/main/java/com/simibubi/create/foundation/ModFilePackResources.java">Creators-of-Create/Create/.../ModFilePackResources.java</a>
 * <p>
 * License: <a href="https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/LICENSE.md">MIT License</a>
 *
 * @author Creators-of-Create
 */
public class ModFilePackResources extends PathPackResources {
    protected final IModFile modFile;
    protected final String sourcePath;

    public ModFilePackResources(String name, IModFile modFile, String sourcePath) {
        super(name, true, modFile.findResource(sourcePath));
        this.modFile = modFile;
        this.sourcePath = sourcePath;
    }

    @Override
    protected Path resolve(String... paths) {
        String[] allPaths = new String[paths.length + 1];
        allPaths[0] = sourcePath;
        System.arraycopy(paths, 0, allPaths, 1, paths.length);
        return modFile.findResource(allPaths);
    }
}
