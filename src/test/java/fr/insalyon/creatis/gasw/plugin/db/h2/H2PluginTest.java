package fr.insalyon.creatis.gasw.plugin.db.h2;

import fr.insalyon.creatis.gasw.GaswConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class H2PluginTest {

    @Mock
    private H2Configuration h2Configuration;

    @Mock
    private GaswConfiguration gaswConfiguration;

    private H2Plugin h2Plugin;

    @BeforeEach
    public void setUp() {
        h2Plugin = new H2Plugin(h2Configuration, gaswConfiguration);
    }

    @ParameterizedTest(name = "serverEnabled={0}")
    @DisplayName("getConnectionUrl() should return the correct connection URL with or without server mode enabled")
    @ValueSource(booleans = {true, false})
    public void testGetConnectionUrlServerMode(boolean serverEnabled) {
        when(h2Configuration.isServerEnabled()).thenReturn(serverEnabled);
        when(h2Configuration.getDbPath()).thenReturn("test_db");
        if (serverEnabled) {
            when(h2Configuration.getServerHost()).thenReturn("localhost");
            when(h2Configuration.getServerPort()).thenReturn(9092);
            when(gaswConfiguration.getExecutionPath()).thenReturn("path/to/exec");
        }

        String url = h2Plugin.getConnectionUrl();

        if (serverEnabled) {
            assertEquals("jdbc:h2:tcp://localhost:9092/path/to/exec/test_db", url);
        } else {
            assertEquals("jdbc:h2:test_db", url);
        }
    }
}
