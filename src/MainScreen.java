import javax.swing.*;

/**
 * GUI for main screen.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class MainScreen extends JFrame {
    private JPanel panel;
    private JTextField txtUserName;
    private JButton enterButton;
    private JButton createButton;
    private JCheckBox setAdminCheckBox;

    /**
     *
     * @return enter button
     */
    public JButton getEnterButton() {
        return enterButton;
    }

    /**
     *
     * @return create button
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     *
     * @return admin check box
     */
    public JCheckBox getSetAdminCheckBox() {
        return setAdminCheckBox;
    }

    /**
     *
     * @return username
     */
    public JTextField getTxtUserName() {
        return txtUserName;
    }

    /**
     * Constructor for main screen.
     */
    public MainScreen() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}

