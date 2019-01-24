package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ControlPanel {
    private JPanel ControlPanel;
    private JPanel Console;
    private JPanel ButtonGUI;
    private JPanel PAPanel;
    private ButtonGUI buttonGUIShoot;
    private ButtonGUI buttonGUISpecialAction;
    private ButtonGUI buttonRotateClockWise;
    private ButtonGUI buttonRotateCounterClockWise;
    private JLabel SpecialActionLabel;
    private JPanel ActionSpecialButtonPanel;
    private JPanel RotateButtonPanel;
    private JTextArea consoleTextArea;
    private ButtonGUI buttonGUIFinTour;
    private JPanel ShootButtonPanel;
    private JLabel CounterPA;

    private void createUIComponents() {
        this.buttonGUIShoot = new ButtonGUI(ButtonType.SHOOT, "", "Cancel");
        this.buttonRotateClockWise = new ButtonGUI(ButtonType.ROTATECW, "", "");
        this.buttonRotateCounterClockWise = new ButtonGUI(ButtonType.ROTATECCW, "", "");
        this.buttonGUISpecialAction = new ButtonGUI(ButtonType.SPECIALACTION, "", "Cancel");
        this.buttonGUIFinTour = new ButtonGUI(ButtonType.ENDTURN, "End of turn", "Cancel");
        this.PAPanel = new ActionPointGUI();

    }


    public JButton getButtonGUIShoot() {
        return buttonGUIShoot;
    }

    public JButton getButtonGUISpecialAction() {
        return buttonGUISpecialAction;
    }

    public JButton getButtonRotateClockWise() {
        return buttonRotateClockWise;
    }

    public JButton getButtonRotateCounterClockWise() {
        return buttonRotateCounterClockWise;
    }

    public JTextArea getConsoleTextArea() {
        return consoleTextArea;
    }

    public JButton getButtonGUIEndOfTurn() {
        return buttonGUIFinTour;
    }

    public JPanel getPAPanel() {
        return PAPanel;
    }

    public JLabel getCounterPA() {
        return CounterPA;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        ControlPanel = new JPanel();
        ControlPanel.setLayout(new GridBagLayout());
        ControlPanel.setBackground(new Color(-16777216));
        ControlPanel.setForeground(new Color(-1));
        ControlPanel.setMinimumSize(new Dimension(400, 500));
        ControlPanel.setPreferredSize(new Dimension(400, 500));
        ControlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15964156)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-1)));
        Console = new JPanel();
        Console.setLayout(new GridBagLayout());
        Console.setBackground(new Color(-16777216));
        Font ConsoleFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 12, Console.getFont());
        if (ConsoleFont != null) Console.setFont(ConsoleFont);
        Console.setForeground(new Color(-15964156));
        Console.setMinimumSize(new Dimension(20, 20));
        Console.setPreferredSize(new Dimension(20, 20));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        ControlPanel.add(Console, gbc);
        Console.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15964156)), null));
        consoleTextArea = new JTextArea();
        consoleTextArea.setBackground(new Color(-16777216));
        consoleTextArea.setCaretColor(new Color(-4514522));
        consoleTextArea.setDisabledTextColor(new Color(-15964156));
        Font consoleTextAreaFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 11, consoleTextArea.getFont());
        if (consoleTextAreaFont != null) consoleTextArea.setFont(consoleTextAreaFont);
        consoleTextArea.setForeground(new Color(-15964156));
        consoleTextArea.setMargin(new Insets(0, 0, 0, 0));
        consoleTextArea.setMinimumSize(new Dimension(58, 25));
        consoleTextArea.setSelectedTextColor(new Color(-1));
        consoleTextArea.setSelectionColor(new Color(-15964156));
        consoleTextArea.setText("Console >");
        consoleTextArea.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.weighty = 10.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        Console.add(consoleTextArea, gbc);
        ButtonGUI = new JPanel();
        ButtonGUI.setLayout(new GridBagLayout());
        ButtonGUI.setBackground(new Color(-16777216));
        ButtonGUI.setForeground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ControlPanel.add(ButtonGUI, gbc);
        ActionSpecialButtonPanel = new JPanel();
        ActionSpecialButtonPanel.setLayout(new GridBagLayout());
        ActionSpecialButtonPanel.setBackground(new Color(-16777216));
        ActionSpecialButtonPanel.setForeground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        ButtonGUI.add(ActionSpecialButtonPanel, gbc);
        ActionSpecialButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15964156)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, ActionSpecialButtonPanel.getFont()), new Color(-4473925)));
        SpecialActionLabel = new JLabel();
        Font SpecialActionLabelFont = this.$$$getFont$$$("Consolas", Font.BOLD, 20, SpecialActionLabel.getFont());
        if (SpecialActionLabelFont != null) SpecialActionLabel.setFont(SpecialActionLabelFont);
        SpecialActionLabel.setForeground(new Color(-1));
        SpecialActionLabel.setText("Special Action");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        ActionSpecialButtonPanel.add(SpecialActionLabel, gbc);
        buttonGUISpecialAction.setEnabled(true);
        Font buttonGUISpecialActionFont = this.$$$getFont$$$("Consolas", Font.BOLD, 14, buttonGUISpecialAction.getFont());
        if (buttonGUISpecialActionFont != null) buttonGUISpecialAction.setFont(buttonGUISpecialActionFont);
        buttonGUISpecialAction.setForeground(new Color(-1));
        buttonGUISpecialAction.setLabel("");
        buttonGUISpecialAction.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        ActionSpecialButtonPanel.add(buttonGUISpecialAction, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 11, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setText("AirCraft Carrier : Shoot AoE radius of 3 (15 AP)");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        ActionSpecialButtonPanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 11, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-1));
        label2.setText("Cruiser : Shoot AoE radius of 2 (11 AP)");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        ActionSpecialButtonPanel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 11, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-1));
        label3.setText("Submarine : Shoot AoE radius of 1 (5 AP)");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        ActionSpecialButtonPanel.add(label3, gbc);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 11, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-1));
        label4.setText("Torpedo boat : Put a mine behind him (2 AP)");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        ActionSpecialButtonPanel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 11, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-1));
        label5.setText("Sentinel : Put a mine behind him (3 AP)");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        ActionSpecialButtonPanel.add(label5, gbc);
        ShootButtonPanel = new JPanel();
        ShootButtonPanel.setLayout(new GridBagLayout());
        ShootButtonPanel.setBackground(new Color(-16777216));
        ShootButtonPanel.setForeground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        ButtonGUI.add(ShootButtonPanel, gbc);
        ShootButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15964156)), null));
        buttonGUIShoot.setEnabled(true);
        Font buttonGUIShootFont = this.$$$getFont$$$("Consolas", Font.BOLD, 14, buttonGUIShoot.getFont());
        if (buttonGUIShootFont != null) buttonGUIShoot.setFont(buttonGUIShootFont);
        buttonGUIShoot.setForeground(new Color(-1));
        buttonGUIShoot.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        ShootButtonPanel.add(buttonGUIShoot, gbc);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Consolas", Font.BOLD, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-1));
        label6.setText("Fire ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        ShootButtonPanel.add(label6, gbc);
        RotateButtonPanel = new JPanel();
        RotateButtonPanel.setLayout(new GridBagLayout());
        RotateButtonPanel.setBackground(new Color(-16777216));
        RotateButtonPanel.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        ButtonGUI.add(RotateButtonPanel, gbc);
        buttonRotateClockWise.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        RotateButtonPanel.add(buttonRotateClockWise, gbc);
        buttonRotateCounterClockWise.setBorderPainted(true);
        buttonRotateCounterClockWise.setSelected(false);
        buttonRotateCounterClockWise.setText("");
        buttonRotateCounterClockWise.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        RotateButtonPanel.add(buttonRotateCounterClockWise, gbc);
        PAPanel.setBackground(new Color(-16777216));
        PAPanel.setMaximumSize(new Dimension(10, 999999999));
        PAPanel.setMinimumSize(new Dimension(35, 500));
        PAPanel.setPreferredSize(new Dimension(35, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        ControlPanel.add(PAPanel, gbc);
        buttonGUIFinTour.setEnabled(true);
        Font buttonGUIFinTourFont = this.$$$getFont$$$("Consolas", Font.BOLD, 14, buttonGUIFinTour.getFont());
        if (buttonGUIFinTourFont != null) buttonGUIFinTour.setFont(buttonGUIFinTourFont);
        buttonGUIFinTour.setForeground(new Color(-1));
        buttonGUIFinTour.setText("End of turn");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        ControlPanel.add(buttonGUIFinTour, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-16777216));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        ControlPanel.add(panel1, gbc);
        CounterPA = new JLabel();
        CounterPA.setAlignmentX(0.5f);
        CounterPA.setAutoscrolls(false);
        CounterPA.setBackground(new Color(-16777216));
        CounterPA.setEnabled(true);
        CounterPA.setFocusTraversalPolicyProvider(true);
        Font CounterPAFont = this.$$$getFont$$$("Consolas", Font.BOLD, 14, CounterPA.getFont());
        if (CounterPAFont != null) CounterPA.setFont(CounterPAFont);
        CounterPA.setForeground(new Color(-1));
        CounterPA.setMinimumSize(new Dimension(10, 10));
        CounterPA.setOpaque(false);
        CounterPA.setText("20/20");
        CounterPA.setVerifyInputWhenFocusTarget(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(1, 1, 1, 1);
        panel1.add(CounterPA, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ControlPanel;
    }
}
