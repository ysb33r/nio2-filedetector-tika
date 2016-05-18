//
// ============================================================================
// (C) Copyright Schalk W. Cronje 2016
//
// This software is licensed under the Apache License 2.0
// See http://www.apache.org/licenses/LICENSE-2.0 for license details
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// License for the specific language governing permissions and limitations
// under the License.
//
// ============================================================================
//

package org.ysb33r.nio.filetypedetector.spi

import groovy.transform.CompileStatic
import org.apache.tika.config.TikaConfig
import org.apache.tika.io.TikaInputStream
import org.apache.tika.metadata.Metadata

import java.nio.file.Path
import java.nio.file.spi.FileTypeDetector

/** A Groovy implementation of a NIO2 FileTypeDetector.
 *
 */
@CompileStatic
class GroovyDetector extends FileTypeDetector {

    /**
     * Probes the given file to guess its content type.
     *
     * <p> The means by which this method determines the file type is highly
     * implementation specific. It may simply examine the file name, it may use
     * a file <a href="../attribute/package-summary.html">attribute</a>,
     * or it may examines bytes in the file.
     *
     * <p> The probe result is the string form of the value of a
     * Multipurpose Internet Mail Extension (MIME) content type as
     * defined by <a href="http://www.ietf.org/rfc/rfc2045.txt"><i>RFC&nbsp;2045:
     * Multipurpose Internet Mail Extensions (MIME) Part One: Format of Internet
     * Message Bodies</i></a>. The string must be parsable according to the
     * grammar in the RFC 2045.
     *
     * @param path
     *          the path to the file to probe
     *
     * @return The content type or {@code null} if the file type is not
     *          recognized
     *
     * @throws IOException
     *          An I/O error occurs
     * @throws SecurityException
     *          If the implementation requires to access the file, and a
     *          security manager is installed, and it denies an unspecified
     *          permission required by a file system provider implementation.
     *          If the file reference is associated with the default file system
     *          provider then the {@link SecurityManager#checkRead(String)} method
     *          is invoked to check read access to the file.
     *
     * @see java.nio.file.Files#probeContentType
     */
    @Override
    String probeContentType(Path path) throws IOException {
        if(path == null) {
            return null
        }
        try {
            Metadata metadata = new Metadata()
            metadata.set(Metadata.RESOURCE_NAME_KEY,path.toString())
            return tika.detector.detect(TikaInputStream.get(path),metadata)
        } catch (final IOException e) {
            throw e
        } catch (final Exception e) {
            throw new IOException(e)
        }
    }

    private TikaConfig tika = new TikaConfig()
}
