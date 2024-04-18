import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public Window(Tester tester){
        this.setTitle("single-layer-neural-network");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JButton button = new JButton("choose file");
        button.setPreferredSize(new Dimension(400, 150));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String filename = selectedFile.getAbsolutePath();
                    System.out.println("====================================================================================" +
                            "===============================================================================================" +
                            "===============");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCOMPUTING USER DATA");
                    System.out.println("====================================================================================" +
                            "===============================================================================================" +
                            "===============");
                    tester.computeUserData(filename);
                } else {
                    System.out.println("No file chosen.");
                }
            }
        });
        JPanel main = new JPanel();
        main.add(button);
        this.add(main, BorderLayout.CENTER);
        this.setVisible(true);
    }
}