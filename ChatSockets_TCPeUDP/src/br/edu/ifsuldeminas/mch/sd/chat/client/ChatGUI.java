package br.edu.ifsuldeminas.mch.sd.chat.client;

import br.edu.ifsuldeminas.mch.sd.chat.ChatException;
import br.edu.ifsuldeminas.mch.sd.chat.ChatFactory;
import br.edu.ifsuldeminas.mch.sd.chat.MessageContainer;
import br.edu.ifsuldeminas.mch.sd.chat.Sender;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class ChatGUI extends JFrame implements MessageContainer {

    private static final long serialVersionUID = 1L;
    private JTextField nameField;
    private JTextField localPortField;
    private JTextField remoteIpField;
    private JTextField remotePortField;
    private JCheckBox connectionOrientedCheckBox;
    private JButton connectButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private Sender sender;

    private static final Color BACKGROUND_COLOR = new Color(230, 240, 250);
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Color BUTTON_COLOR = new Color(66, 135, 245);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color INPUT_BORDER_COLOR = new Color(200, 200, 200);

    private static final Font TITLE_FONT = new Font("Roboto", Font.BOLD, 24);
    private static final Font LABEL_FONT = new Font("Open Sans", Font.BOLD, 14);
    private static final Font TEXT_FONT = new Font("Open Sans", Font.PLAIN, 14);

    public ChatGUI() {
        super("CHAT UDP TCP - JOÃO LUÍS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("CHAT UDP TCP - JOÃO LUÍS");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel configPanel = new JPanel(new GridBagLayout());
        configPanel.setBackground(BACKGROUND_COLOR);
        configPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(LABEL_FONT);
        nameLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        configPanel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        nameField.setFont(TEXT_FONT);
        nameField.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        configPanel.add(nameField, gbc);

        JLabel remoteIpLabel = new JLabel("IP Remoto:");
        remoteIpLabel.setFont(LABEL_FONT);
        remoteIpLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 1;
        configPanel.add(remoteIpLabel, gbc);

        remoteIpField = new JTextField(15);
        remoteIpField.setFont(TEXT_FONT);
        remoteIpField.setText("localhost");
        remoteIpField.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        configPanel.add(remoteIpField, gbc);

        JLabel localPortLabel = new JLabel("Porta Local:");
        localPortLabel.setFont(LABEL_FONT);
        localPortLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 0;
        configPanel.add(localPortLabel, gbc);

        localPortField = new JTextField(7);
        localPortField.setFont(TEXT_FONT);
        localPortField.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        gbc.gridx = 3;
        gbc.gridy = 0;
        configPanel.add(localPortField, gbc);

        JLabel remotePortLabel = new JLabel("Porta Remota:");
        remotePortLabel.setFont(LABEL_FONT);
        remotePortLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 1;
        configPanel.add(remotePortLabel, gbc);

        remotePortField = new JTextField(7);
        remotePortField.setFont(TEXT_FONT);
        remotePortField.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        gbc.gridx = 3;
        gbc.gridy = 1;
        configPanel.add(remotePortField, gbc);

        connectionOrientedCheckBox = new JCheckBox("TCP");
        connectionOrientedCheckBox.setFont(LABEL_FONT);
        connectionOrientedCheckBox.setForeground(TEXT_COLOR);
        connectionOrientedCheckBox.setBackground(BACKGROUND_COLOR);
        connectionOrientedCheckBox.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        configPanel.add(connectionOrientedCheckBox, gbc);
        gbc.gridwidth = 1;

        connectButton = new JButton("Conectar");
        connectButton.setFont(LABEL_FONT);
        connectButton.setBackground(BUTTON_COLOR);
        connectButton.setForeground(BUTTON_TEXT_COLOR);
        connectButton.setFocusPainted(false);
        connectButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        connectButton.setOpaque(true);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        configPanel.add(connectButton, gbc);
        gbc.gridwidth = 1;

        mainPanel.add(configPanel, BorderLayout.NORTH);

        chatArea = new JTextArea(20, 50);
        chatArea.setFont(TEXT_FONT);
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(TEXT_COLOR);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        mainPanel.add(chatScrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        messagePanel.setBackground(BACKGROUND_COLOR);

        messageField = new JTextField();
        messageField.setFont(TEXT_FONT);
        messageField.setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 1));
        messagePanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Enviar");
        sendButton.setFont(LABEL_FONT);
        sendButton.setBackground(BUTTON_COLOR);
        sendButton.setForeground(BUTTON_TEXT_COLOR);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        sendButton.setOpaque(true);
        messagePanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        connectButton.addActionListener(e -> connect());
        ActionListener sendMessageAction = e -> sendMessage();
        sendButton.addActionListener(sendMessageAction);
        messageField.addActionListener(sendMessageAction);

        messageField.setEnabled(false);
        sendButton.setEnabled(false);

        add(mainPanel);

        setVisible(true);
    }

    private void connect() {
        try {
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira seu nome.", "Nome Requerido", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int localPort = Integer.parseInt(localPortField.getText());
            String remoteIp = remoteIpField.getText();
            int remotePort = Integer.parseInt(remotePortField.getText());
            boolean isConnectionOriented = connectionOrientedCheckBox.isSelected();

            if (isConnectionOriented) {
                connectButton.setEnabled(false);
                connectButton.setText("Conectando...");

                JDialog connectingDialog = new JDialog(this, "Conexão TCP", true);
                connectingDialog.add(new JLabel("Conectando ao servidor..."));
                connectingDialog.pack();
                connectingDialog.setLocationRelativeTo(this);

                SwingWorker<Sender, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Sender doInBackground() throws Exception {
                        return ChatFactory.build(isConnectionOriented, remoteIp, remotePort, localPort, ChatGUI.this);
                    }

                    @Override
                    protected void done() {
                        connectingDialog.dispose();
                        try {
                            Sender connectedSender = get();
                            onConnectionSuccess(connectedSender);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(ChatGUI.this, "Erro ao conectar: " + ex.getCause().getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                            connectButton.setEnabled(true);
                            connectButton.setText("Conectar");
                        }
                    }
                };
                worker.execute();
                connectingDialog.setVisible(true);
            } else {
                Sender connectedSender = ChatFactory.build(isConnectionOriented, remoteIp, remotePort, localPort, this);
                onConnectionSuccess(connectedSender);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Portas devem ser números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            connectButton.setEnabled(true);
            connectButton.setText("Conectar");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar chat: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            connectButton.setEnabled(true);
            connectButton.setText("Conectar");
        }
    }
    
    private void onConnectionSuccess(Sender connectedSender) {
        this.sender = connectedSender;
        nameField.setEnabled(false);
        localPortField.setEnabled(false);
        remoteIpField.setEnabled(false);
        remotePortField.setEnabled(false);
        connectionOrientedCheckBox.setEnabled(false);
        connectButton.setEnabled(true);
        connectButton.setText("Conectar");
        messageField.setEnabled(true);
        sendButton.setEnabled(true);
        messageField.requestFocusInWindow();
        chatArea.append("Conexão estabelecida. Você já pode enviar mensagens.\n");
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (message.trim().isEmpty()) {
            return;
        }
        try {
            String name = nameField.getText().trim();
            String formattedMessage = name + ": " + message;
            sender.send(formattedMessage);
            chatArea.append("Eu: " + message + "\n");
            messageField.setText("");
        } catch (ChatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar mensagem: " + e.getCause().getMessage(), "Erro de Envio", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void newMessage(String message) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            chatArea.append(message.trim() + "\n");
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ChatGUI::new);
    }
}
