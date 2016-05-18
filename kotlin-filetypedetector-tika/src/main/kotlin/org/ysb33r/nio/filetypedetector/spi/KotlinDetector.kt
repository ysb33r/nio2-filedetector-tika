/**
 * ============================================================================
 * (C) Copyright Schalk W. Cronje 2016
 *
 * This software is licensed under the Apache License 2.0
 * See http://www.apache.org/licenses/LICENSE-2.0 for license details
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * ============================================================================
 */

package org.ysb33r.nio.filetypedetector.spi

import org.apache.tika.config.TikaConfig
import org.apache.tika.io.TikaInputStream
import java.nio.file.Path
import java.nio.file.spi.FileTypeDetector
import org.apache.tika.metadata.Metadata
import java.io.IOException

class KotlinDetector() : FileTypeDetector() {
    var tika  = TikaConfig()

    override fun probeContentType(path: Path): String? {
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