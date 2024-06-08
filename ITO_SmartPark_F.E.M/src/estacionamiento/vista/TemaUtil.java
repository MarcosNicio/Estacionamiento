/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.vista;



import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JFrame;

public class TemaUtil {

    private static final String CONFIG_FILE = "config.properties";
    private static String temaActual = "Flat Light"; // Valor por defecto

    public static void cargarConfiguracion() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            props.load(input);
            temaActual = props.getProperty("tema", "Flat Light"); 
        } catch (IOException ex) {
            temaActual = "Flat Light";
        }
    }

    public static void guardarConfiguracion() {
        Properties props = new Properties();
        props.setProperty("tema", temaActual);
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            props.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void aplicarTema(JFrame frame) {
        try {
            switch (temaActual) {
                case "Flat Light":
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case "Flat Dark":
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                case "Arc Dark Orange":
                    UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme());
                    break;
                case "Material Design Dark":
                    UIManager.setLookAndFeel(new FlatMaterialDesignDarkIJTheme());
                    break;
                case "Monokai Pro":
                    UIManager.setLookAndFeel(new FlatMonokaiProIJTheme());
                    break;
                case "Solarized Light":
                    UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
                    break;
                case "Gradiano Midnight Blue":
                    UIManager.setLookAndFeel(new FlatGradiantoMidnightBlueIJTheme());
                    break;
                default:
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
            }
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    public static String getTemaActual() {
        return temaActual;
    }

    public static void setTemaActual(String tema) {
        temaActual = tema;
    }
}

