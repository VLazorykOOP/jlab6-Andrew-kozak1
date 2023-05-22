import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class MatrixComparisonUI {
    private JFrame frame;
    private JTextField sizeTextField;
    private JTextArea matrixATextArea;
    private JTextArea matrixBTextArea;
    private JTextArea matrixXTextArea;

    public MatrixComparisonUI() {
        frame = new JFrame("Matrix Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JLabel sizeLabel = new JLabel("Enter the size of the matrices:");
        sizeTextField = new JTextField();
        JButton loadButton = new JButton("Load Matrices");
        matrixATextArea = new JTextArea();
        matrixBTextArea = new JTextArea();
        matrixXTextArea = new JTextArea();

        panel.add(sizeLabel);
        panel.add(sizeTextField);
        panel.add(loadButton);
        panel.add(matrixATextArea);
        panel.add(matrixBTextArea);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(sizeTextField.getText());
                    int[][] A = new int[n][n];
                    int[][] B = new int[n][n];

                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(frame);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        Scanner scanner = new Scanner(file);

                        matrixATextArea.setText("Matrix A:\n");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (scanner.hasNextInt()) {
                                    A[i][j] = scanner.nextInt();
                                    matrixATextArea.append(A[i][j] + "\t");
                                } else {
                                    throw new NumberFormatException("Invalid input format.");
                                }
                            }
                            matrixATextArea.append("\n");
                        }

                        matrixBTextArea.setText("Matrix B:\n");
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (scanner.hasNextInt()) {
                                    B[i][j] = scanner.nextInt();
                                    matrixBTextArea.append(B[i][j] + "\t");
                                } else {
                                    throw new NumberFormatException("Invalid input format.");
                                }
                            }
                            matrixBTextArea.append("\n");
                        }

                        int[] X = new int[n];

                        for (int i = 0; i < n; i++) {
                            boolean rowIsGreaterThanB = true;
                            for (int j = 0; j < n; j++) {
                                if (A[i][j] <= B[i][j]) {
                                    rowIsGreaterThanB = false;
                                    break;
                                }
                            }
                            X[i] = rowIsGreaterThanB ? 1 : 0;
                        }

                        matrixXTextArea.setText("Matrix X:\nX = [ ");
                        for (int i = 0; i < n; i++) {
                            matrixXTextArea.append(X[i] + " ");
                        }
                        matrixXTextArea.append("]");
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format.");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MatrixComparisonUI();
            }
        });
    }
}
