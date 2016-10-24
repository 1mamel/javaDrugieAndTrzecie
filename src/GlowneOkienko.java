import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mamel on 11.10.2016.
 */
public class GlowneOkienko extends JFrame {

    private JPanel panel1;
    private JTextField szerokoscTextField;
    private JTextField dlugoscTextField;
    private JTextField wysokoscTextField;
    private JButton zatwierdzButton;
    private JTextArea outputTextArea;
    private JButton zapiszDoPlikuButton;
    private JTextField nazwaTextField;

    public GlowneOkienko(){
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        List<Pokoj> pokoje = new ArrayList<Pokoj>();
        pokoje.add(new Pokoj("salon", 10,2,10));
        pokoje.add(new Pokoj("kuchnia", 15,2,15));
        pokoje.add(new Pokoj("sypialnia", 5,1.5d,5));
        for (Pokoj item:
             pokoje) {
            outputTextArea.append(item.nazwa + " (" + item.szerokosc + "x" + item.dlugosc+"x"+item.wysokosc +") " +
                    "objetosc:" + item.getObjetosc() + " powierzchnia scian: " + item.getPowierzchniaScian() +
            " koszt malowania: " + item.getKosztMalowania(10) + " kosz podlogi: " + item.getKosztPodlogi(5) + "\n");
        }
        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(isNumeric(szerokoscTextField.getText()) && isNumeric(wysokoscTextField.getText()) &&
                        isNumeric(dlugoscTextField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Jedna z wielkosci nie jest typem Double", "InfoBox: " +
                            "error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                pokoje.add(new Pokoj(nazwaTextField.getText(), Double.parseDouble(szerokoscTextField.getText()),
                        Double.parseDouble(wysokoscTextField.getText()),
                        Double.parseDouble(dlugoscTextField.getText())));
                outputTextArea.setText("");
                for (Pokoj item:
                        pokoje) {
                    outputTextArea.append(item.nazwa + " (" + item.szerokosc + "x" + item.dlugosc+"x"+item.wysokosc +") " +
                            "objetosc:" + item.getObjetosc() + " powierzchnia scian: " + item.getPowierzchniaScian() +
                            " koszt malowania: " + item.getKosztMalowania(10) + " koszt podlogi: " + item.getKosztPodlogi(5) + "\n");
                }
            }
        });
        zapiszDoPlikuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        fileToSave.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        Files.write(Paths.get(fileToSave.getPath()) , Arrays.asList( outputTextArea.getText().split("\n")),
                                Charset.forName("UTF-8"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });
        this.setVisible(true);
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
