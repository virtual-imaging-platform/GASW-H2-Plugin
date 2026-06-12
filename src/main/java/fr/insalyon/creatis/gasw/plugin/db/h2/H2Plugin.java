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
import fr.insalyon.creatis.gasw.plugin.DatabasePlugin;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rafael Silva
 */
@Service
public class H2Plugin implements DatabasePlugin {

    private final H2Configuration h2Configuration;
    private final GaswConfiguration gaswConfiguration;

    public H2Plugin(H2Configuration h2Configuration, GaswConfiguration gaswConfiguration) {
        this.h2Configuration = h2Configuration;
        this.gaswConfiguration = gaswConfiguration;
    }

    @Override
    public String getName() {
        return H2Constants.PLUGIN_NAME;
    }

    @Override
    public String getEntityPackage() {
        return H2Constants.ENTITY_PACKAGE;
    }

    @Override
    public String getSchema() throws GaswException {
        return h2Configuration.getSchema();
    }

    @Override
    public String getDriverClass() throws GaswException {
        return H2Constants.H2_DRIVER;
    }

    @Override
    public String getConnectionUrl() throws GaswException {

        if (h2Configuration.isServerEnabled()) {
            return "jdbc:h2:tcp://" + h2Configuration.getServerHost() + ":"
                    + h2Configuration.getServerPort() + "/"
                    + gaswConfiguration.getExecutionPath()
                    + "/"+ h2Configuration.getDbPath();
        } else {
            return "jdbc:h2:" + h2Configuration.getDbPath();
        }
    }

    @Override
    public String getHibernateDialect() throws GaswException {
        return H2Constants.HIBERNATE_DIALECT;
    }

    @Override
    public String getUserName() throws GaswException {
        return h2Configuration.getUser();
    }

    @Override
    public String getPassword() throws GaswException {
        return h2Configuration.getPassword();
    }
}
