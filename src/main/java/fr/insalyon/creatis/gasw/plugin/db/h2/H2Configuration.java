/* Copyright CNRS-CREATIS
 *
 * Rafael Silva
 * rafael.silva@creatis.insa-lyon.fr
 * http://www.rafaelsilva.com
 *
 * This software is a grid-enabled data-driven workflow manager and editor.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package fr.insalyon.creatis.gasw.plugin.db.h2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Rafael Silva
 */
@Configuration
@PropertySource("classpath:h2-plugin.properties")
public class H2Configuration {

    @Value("${plugin.h2.schema}")
    private String schema;

    @Value("${plugin.h2.user}")
    private String user;

    @Value("${plugin.h2.password}")
    private String password;

    @Value("${plugin.h2.server.enabled}")
    private boolean serverEnabled;

    @Value("${plugin.h2.server.host}")
    private String serverHost;

    @Value("${plugin.h2.server.port}")
    private int serverPort;

    @Value("${plugin.h2.db.path}")
    private String dbPath;

    public String getSchema() {
        return schema;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public boolean isServerEnabled() {
        return serverEnabled;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getDbPath() {
        return dbPath;
    }
}
