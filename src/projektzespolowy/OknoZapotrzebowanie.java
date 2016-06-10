/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Paweł
 */
public class OknoZapotrzebowanie extends JFrame implements ActionListener {

    int i = 0;
    JTextField pb95[] = new JTextField[50];
    JTextField pb98[] = new JTextField[50];
    JTextField on[] = new JTextField[50];
    JTextField onEko[] = new JTextField[50];
    JTextField onSuper[] = new JTextField[50];
    static Stacja stacja[] = new Stacja[30];
    OknoInicjalizujace x = new OknoInicjalizujace();

    static int[][] zapotrzebowania;

    int[] zap_pb95 = new int[x.getIloscStacji()];
    int[] zap_pb98 = new int[x.getIloscStacji()];
    int[] zap_on = new int[x.getIloscStacji()];
    int[] zap_on_eko = new int[x.getIloscStacji()];
    int[] zap_on_super = new int[x.getIloscStacji()];

    public OknoZapotrzebowanie() {

        setTitle("Zapotrzebowanie");
        setSize(500, 400);
        setLayout(null);

        JButton przyciskOK = new JButton("OK");
        przyciskOK.setBounds(460, 500, 100, 20);

        add(new JLabel("Pb 95")).setBounds(100, 5, 50, 50);
        add(new JLabel("Pb 98")).setBounds(170, 5, 50, 50);
        add(new JLabel("ON")).setBounds(250, 5, 50, 50);
        add(new JLabel("ON eko")).setBounds(310, 5, 50, 50);
        add(new JLabel("ON super")).setBounds(380, 5, 70, 50);

        for (i = 0; i < x.getIloscStacji(); i++) {      // nie powinno być więcej niz 30 stacje

            add(new JLabel("Stacja " + ((char) ('A' + i)) + ":")).setBounds(24, 40 + i * 30, 70, 50);
            for (int l = 0; l < 5; l++) {
                if (l == 0) {
                    add(pb95[i] = new JTextField(3)).setBounds(100 + l * 70 + l, 55 + i * 30, 40, 20);

                } else if (l == 1) {
                    add(pb98[i] = new JTextField(3)).setBounds(100 + l * 70 + l, 55 + i * 30, 40, 20);

                } else if (l == 2) {
                    add(on[i] = new JTextField(3)).setBounds(100 + l * 70 + l, 55 + i * 30, 40, 20);

                } else if (l == 3) {
                    add(onEko[i] = new JTextField(3)).setBounds(100 + l * 70 + l, 55 + i * 30, 40, 20);

                } else if (l == 4) {
                    add(onSuper[i] = new JTextField(3)).setBounds(100 + l * 70 + l, 55 + i * 30, 40, 20);

                }
            }

        }

        add(przyciskOK).setBounds(200, 60 + 30 * i, 100, 40);
        przyciskOK.addActionListener(this);
        //pack();
    }

    boolean testZapotrzebowanie() {
        for (int i = 0; i < x.getIloscStacji(); i++) {
            if (!(zap_pb95[i] >= 0 && zap_pb98[i] >= 0 && zap_on[i] >= 0 && zap_on_eko[i] >= 0 && zap_on_super[i] >= 0 && zap_pb95[i]
                    <= 100 && zap_pb98[i] <= 100 && zap_on[i] <= 100 && zap_on_eko[i] <= 100 && zap_on_super[i] <= 100)) {
                return false;

            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //inicjalzacja zapotrzebowania na kazda stacje
        zapotrzebowania = new int[x.getIloscStacji()][5];
        for (int j = 0; j < x.getIloscStacji(); j++) {
            zapotrzebowania[j][0] = Integer.parseInt(pb95[j].getText());
            zapotrzebowania[j][1] = Integer.parseInt(pb98[j].getText());
            zapotrzebowania[j][2] = Integer.parseInt(on[j].getText());
            zapotrzebowania[j][3] = Integer.parseInt(onEko[j].getText());
            zapotrzebowania[j][4] = Integer.parseInt(onSuper[j].getText());
        }

        if (testZapotrzebowanie()) {

            for (int k = 0; k < x.getIloscStacji(); k++) {
                stacja[k] = new Stacja(zapotrzebowania[k][0], zapotrzebowania[k][1], zapotrzebowania[k][2], zapotrzebowania[k][3], zapotrzebowania[k][4]);

            }
            this.dispose();
            OknoGlowne okno = new OknoGlowne();
            okno.setLocationRelativeTo(null);
            okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            okno.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Popraw błędne dane!!", "Błędne dane", HEIGHT);
        }

    }

    public int[][] getZapotrzebowania() {
        return zapotrzebowania;
    }
}
