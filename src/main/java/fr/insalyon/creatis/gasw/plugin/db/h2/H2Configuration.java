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

import fr.insalyon.creatis.gasw.GaswConfiguration;
import fr.insalyon.creatis.gasw.GaswException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 *
 * @author Rafael Silva
 */
public class H2Configuration {

    private static final Logger logger = Logger.getLogger("fr.insalyon.creatis.gasw");
    private static H2Configuration instance;
    private String schema;
    private String user;
    private String password;
    private boolean serverEnabled;
    private String serverHost;
    private int serverPort;
    private String dbPath;

    public static H2Configuration getInstance() throws GaswException {

        if (instance == null) {
            instance = new H2Configuration();
        }
        return instance;
    }

    private H2Configuration() throws GaswException {

        try {
            PropertiesConfiguration config = GaswConfiguration.getInstance().getPropertiesConfiguration();

            schema = config.getString(H2Constants.LAB_SCHEMA, "PUBLIC");
            user = config.getString(H2Constants.LAB_USER, "test");
            password = config.getString(H2Constants.LAB_PASSWORD, "");
            serverEnabled = config.getBoolean(H2Constants.LAB_SERVER_ENABLED, false);
            serverHost = config.getString(H2Constants.LAB_SERVER_HOST, "localhost");
            serverPort = config.getInt(H2Constants.LAB_SERVER_PORT, 9092);
            dbPath = config.getString(H2Constants.LAB_DB_PATH, "~/.gasw/db");

            config.setProperty(H2Constants.LAB_SCHEMA, schema);
            config.setProperty(H2Constants.LAB_USER, user);
            config.setProperty(H2Constants.LAB_PASSWORD, password);
            config.setProperty(H2Constants.LAB_SERVER_ENABLED, serverEnabled);
            config.setProperty(H2Constants.LAB_SERVER_HOST, serverHost);
            config.setProperty(H2Constants.LAB_SERVER_PORT, serverPort);
            config.setProperty(H2Constants.LAB_DB_PATH, dbPath);

            config.save();

        } catch (ConfigurationException ex) {
            logger.error(ex);
        }
    }

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
