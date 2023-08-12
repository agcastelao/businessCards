package User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends JFrame {

    private List<User> userList;
    private Scanner scanner;

    public Main() {
        userList = new ArrayList<>();
        userList.add(new User(1, "João da Silva", 30, "joao.silva@example.com"));
        userList.add(new User(2, "Maria Souza", 25, "maria.souza@example.com"));
        userList.add(new User(3, "Pedro Santos", 35, "pedro.santos@example.com"));
        userList.add(new User(4, "Ana Oliveira", 28, "ana.oliveira@example.com"));
        userList.add(new User(5, "Carlos Rodrigues", 42, "carlos.rodrigues@example.com"));
        userList.add(new User(6, "Sofia Pereira", 22, "sofia.pereira@example.com"));
        userList.add(new User(7, "Rafael Almeida", 31, "rafael.almeida@example.com"));
        userList.add(new User(8, "Laura Fernandes", 27, "laura.fernandes@example.com"));
        userList.add(new User(9, "Bruno Costa", 29, "bruno.costa@example.com"));
        userList.add(new User(10, "Luana Ferreira", 24, "luana.ferreira@example.com"));
        scanner = new Scanner(System.in);

        setTitle("Cartões de Visita");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cardPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        for (User user : userList) {
            cardPanel.add(createBusinessCard(user));
        }

        add(new JScrollPane(cardPanel));
        setVisible(true);
    }

    private JPanel createBusinessCard(User user) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setPreferredSize(new Dimension(250, 150));

        JLabel idLabel = new JLabel("ID: " + user.getId());
        JLabel nameLabel = new JLabel("Nome: " + user.getName());
        JLabel ageLabel = new JLabel("Idade: " + user.getAge());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());

        card.add(idLabel);
        card.add(nameLabel);
        card.add(ageLabel);
        card.add(emailLabel);

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            editUserData(user, nameLabel, ageLabel, emailLabel);
        });

         JButton deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(e -> {
            deleteUserCard(user);
        });

        card.add(editButton);
        card.add(deleteButton);

        return card;
    }

    private void editUserData(User user, JLabel nameLabel, JLabel ageLabel, JLabel emailLabel) {
        JTextField nameField = new JTextField(user.getName());
        JTextField ageField = new JTextField(String.valueOf(user.getAge()));
        JTextField emailField = new JTextField(user.getEmail());

        Object[] fields = {
                "Nome:", nameField,
                "Idade:", ageField,
                "Email:", emailField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Editar Informações", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String newName = nameField.getText();
            int newAge = Integer.parseInt(ageField.getText());
            String newEmail = emailField.getText();

            user.setName(newName);
            user.setAge(newAge);
            user.setEmail(newEmail);

            nameLabel.setText("Nome: " + newName);
            ageLabel.setText("Idade: " + newAge);
            emailLabel.setText("Email: " + newEmail);
        }
    }

    private void deleteUserCard(User user) {
        int option = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar esse cartão?", "Deletar Cartão", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            userList.remove(user);
            refreshCards();
        }
    }

    private void refreshCards() {
        getContentPane().removeAll();
        JPanel cardPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        for (User user : userList) {
            cardPanel.add(createBusinessCard(user));
        }

        add(new JScrollPane(cardPanel));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }


}
