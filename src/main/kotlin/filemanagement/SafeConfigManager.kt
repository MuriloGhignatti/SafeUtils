package filemanagement

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

abstract class SafeConfigManager(fileName: String, pluginName: String) {
    private val file: File

    var config: FileConfiguration

    init {
        file = File(Bukkit.getServer().pluginManager.getPlugin(pluginName)?.dataFolder, fileName)
        if (!file.exists())
            file.createNewFile()
        config = loadCustomFile()
        generateDefaults()
    }

    private fun loadCustomFile(): FileConfiguration{
        return YamlConfiguration.loadConfiguration(file)
    }

    fun save(){
        config.save(file)
    }

    fun reload(){
        config = loadCustomFile()
    }

    abstract fun generateDefaults()
}