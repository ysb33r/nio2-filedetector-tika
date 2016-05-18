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

import spock.lang.Specification

import java.nio.file.Files


/**
 * @author Schalk W. Cronjé
 */
class GroovyDetectorSpec extends Specification {

    def "Detect a JAR file"() {
        when: "I give it the Gradle Wrapper JAR file"
        String mimetype = Files.probeContentType(new File( '../gradle/wrapper/gradle-wrapper.jar').toPath())

        then: 'It should be reported as a JAR'
        mimetype == 'application/java-archive'
    }
}