package org.ysb33r.nio.filetypedetector.spi

import org.apache.tika.config.TikaConfig
import org.apache.tika.io.TikaInputStream
import java.nio.file.Path
import java.nio.file.spi.FileTypeDetector
import org.apache.tika.metadata.Metadata
import java.io.IOException

/**
 * @author Schalk W. Cronjé
 */
class KotlinDetector() : FileTypeDetector() {
    var tika  = TikaConfig()

    override fun probeContentType(path: Path?): String? {
        if(path == null) {
            return null
        }
        try {
            val metadata = Metadata()
            metadata.set(Metadata.RESOURCE_NAME_KEY,path.toString())
            return "${tika.getDetector().detect(TikaInputStream.get(path),metadata)}"
        } catch (e : IOException) {
            throw e
        } catch (e : Exception) {
            throw IOException(e)
        }
    }
}