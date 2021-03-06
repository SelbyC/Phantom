package net.jitse.phantom.spigot.utilities.files;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TextFileTest {

    private static JavaPlugin plugin;

    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpClass() {
        plugin = mock(JavaPlugin.class);
        given(plugin.getDataFolder()).willReturn(folder.getRoot());
    }

    @Test
    public void testWithCopyrightFile() throws Exception {
        File mock = new File(getClass().getResource("/copyright.txt").getPath());
        when(plugin.getResource("copyright.txt")).thenReturn(new FileInputStream(mock));

        TextFile textFile = new TextFile(plugin, "copyright");
        assertFalse(textFile.exists());
        assertTrue(textFile.create());
        assertTrue(new File(folder.getRoot() + "/copyright.txt").exists());
        assertTrue(textFile.exists());
    }
}