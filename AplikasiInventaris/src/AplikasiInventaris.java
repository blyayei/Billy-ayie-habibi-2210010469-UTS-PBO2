import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AplikasiInventaris extends JFrame {
    private InventoryManager manager;
    private JList<InventoryItem> itemList;
    private JTextArea itemDescription;
    private JTextField itemName;

    public AplikasiInventaris() {
        manager = new InventoryManager();
        setTitle("Aplikasi Inventaris Barang");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header tanpa logo
        JLabel header = new JLabel("Aplikasi Inventaris Barang", JLabel.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setForeground(new Color(70, 130, 180));

        // Panel Daftar Barang
        JPanel listPanel = new JPanel(new BorderLayout());
        itemList = new JList<>();
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(e -> updateDetails());
        JScrollPane listScrollPane = new JScrollPane(itemList);
        listPanel.add(new JLabel("Daftar Barang:"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Panel Detail Barang
        JPanel detailPanel = new JPanel(new BorderLayout());
        itemName = new JTextField();
        itemDescription = new JTextArea(5, 20);
        itemDescription.setLineWrap(true);
        itemDescription.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(itemDescription);

        detailPanel.add(new JLabel("Nama Barang:"), BorderLayout.NORTH);
        detailPanel.add(itemName, BorderLayout.CENTER);
        detailPanel.add(new JLabel("Deskripsi Barang:"), BorderLayout.SOUTH);
        detailPanel.add(descriptionScrollPane, BorderLayout.SOUTH);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Tambah");
        JButton updateButton = new JButton("Ubah");
        JButton deleteButton = new JButton("Hapus");

        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Menambahkan warna dan desain
        getContentPane().setBackground(new Color(240, 248, 255));
        listPanel.setBackground(new Color(230, 230, 250));
        detailPanel.setBackground(new Color(255, 239, 213));
        buttonPanel.setBackground(new Color(255, 250, 240));

        // Layout Utama
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(listPanel, BorderLayout.WEST);
        add(detailPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateItemList();
    }

    private void updateDetails() {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex != -1) {
            InventoryItem item = manager.getItems().get(selectedIndex);
            itemName.setText(item.getName());
            itemDescription.setText(item.getDescription());
        }
    }

    private void addItem() {
        String name = itemName.getText().trim();
        String description = itemDescription.getText().trim();
        if (!name.isEmpty()) {
            manager.addItem(new InventoryItem(name, description));
            updateItemList();
            clearFields();
        }
    }

    private void updateItem() {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex != -1) {
            String name = itemName.getText().trim();
            String description = itemDescription.getText().trim();
            if (!name.isEmpty()) {
                manager.updateItem(selectedIndex, new InventoryItem(name, description));
                updateItemList();
                clearFields();
            }
        }
    }

    private void deleteItem() {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex != -1) {
            manager.removeItem(selectedIndex);
            updateItemList();
            clearFields();
        }
    }

    private void updateItemList() {
        DefaultListModel<InventoryItem> model = new DefaultListModel<>();
        for (InventoryItem item : manager.getItems()) {
            model.addElement(item);
        }
        itemList.setModel(model);
    }

    private void clearFields() {
        itemName.setText("");
        itemDescription.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiInventaris app = new AplikasiInventaris();
            app.setVisible(true);
        });
    }
}
