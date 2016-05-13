/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Paweł
 */
public class OknoGlowne extends JFrame {

    public OknoGlowne() {

        setTitle("Marszrutyzacja");
        setSize(1000, 600);

        JPanel obrazPanel = new OknoGlownePanel();
        add(obrazPanel);

    }

}
