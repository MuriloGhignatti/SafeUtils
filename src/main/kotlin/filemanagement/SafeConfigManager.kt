package filemanagement

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.InputStream

abstract class SafeConfigManager(fileName: String, pluginName: String) {
    private val file: File

    var config: FileConfiguration

    init {
        file = File(Bukkit.getServer().pluginManager.getPlugin(pluginName)?.dataFolder, fileName)
        if (!file.exists())
            file.createNewFile()
        config = loadCustomFile(this.file)
        generateDefaults()
    }

    fun loadCustomFile(file: File): FileConfiguration{
        return YamlConfiguration.loadConfiguration(file)
    }

    fun get(): FileConfiguration{
        return config;
    }

    fun save(){
        config.save(file)
    }

    fun reload(){
        config = loadCustomFile(this.file)
    }

    abstract fun generateDefaults()
}