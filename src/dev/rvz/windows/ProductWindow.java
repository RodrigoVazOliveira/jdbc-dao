package dev.rvz.windows;

import dev.rvz.controllers.CategoryController;
import dev.rvz.controllers.ProductController;
import dev.rvz.models.Category;
import dev.rvz.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductWindow extends JFrame {
    private final ProductController productController;

    private JTextField nameProductTextField;
    private JTextField descriptionProducttextField;
    private JComboBox<Category> categoryProductComboBox;
    private JTable table1;
    private DefaultTableModel defaultTableModel;


    public ProductWindow(String title, ProductController productController, CategoryController categoryController) throws HeadlessException {
        super(title);
        this.productController = productController;

        Container container = getContentPane();
        setLayout(null);

        createLabels(container);
        createTextFileds(container, categoryController.getAll());
        createButton(container);
        createTable(container);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void createTable(Container container) {
        table1 = new JTable();
        defaultTableModel = (DefaultTableModel) table1.getModel();

        defaultTableModel.addColumn("Identificador do produto");
        defaultTableModel.addColumn("Nome do produto");
        defaultTableModel.addColumn("Descrição do produto");
        fillTable();

        table1.setBounds(10, 185, 760, 300);
        container.add(table1);
    }

    private void fillTable() {
        List<Product> products = this.productController.getAll();
        for (Product product : products) {
            defaultTableModel.addRow(new Object[]{product.getId(), product.getName(), product.getDescription()});
        }
    }

    private void createButton(Container container) {
        container.add(createSaveButton());
        container.add(createClearButton());
        container.add(createRemoveButton());
        container.add(createUpdateButton());

    }

    private JButton createUpdateButton() {
        JButton updateButton = new JButton("Atualizar");
        updateButton.setBounds(200, 500, 150, 20);

        updateButton.addActionListener(actionEvent -> {
            updateProduct();
            clearInputs();
            clearTable();
            fillTable();
        });

        return updateButton;
    }

    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Excluir");
        removeButton.setBounds(10, 500, 150, 20);

        removeButton.addActionListener(actionEvent -> {
            removeProduct();
            clearInputs();
            clearTable();
        });

        return removeButton;
    }

    private JButton createClearButton() {
        JButton clearButton = new JButton("Limpar");
        clearButton.setBounds(200, 145, 150, 20);

        clearButton.addActionListener(actionEvent -> clearInputs());

        return clearButton;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Salvar produto");
        saveButton.setBounds(10, 145, 150, 20);

        saveButton.addActionListener(actionEvent -> {
            saveProduct();
            clearInputs();
            clearTable();
            fillTable();
        });
        return saveButton;
    }

    private void updateProduct() {
        Object lineSelect = defaultTableModel.getValueAt(table1.getSelectedRow(), 0);
        if (lineSelect instanceof Integer) {
            Integer id = (Integer) defaultTableModel.getValueAt(table1.getSelectedRow(), 0);
            String name = (String) defaultTableModel.getValueAt(table1.getSelectedRow(), 1);
            String description = (String) defaultTableModel.getValueAt(table1.getSelectedRow(), 2);
            Product product = new Product.Builder()
                    .setId(id)
                    .setName(name)
                    .setDescription(description)
                    .build();
            try {
                this.productController.update(product);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void removeProduct() {
        Object lineSelect = defaultTableModel.getValueAt(table1.getSelectedRow(), 0);
        if (lineSelect instanceof Integer) {
            Integer id = (Integer) lineSelect;
            try {
                this.productController.remove(id);
                defaultTableModel.removeRow(table1.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void clearInputs() {
        nameProductTextField.setText("");
        descriptionProducttextField.setText("");
        categoryProductComboBox.setSelectedIndex(0);
    }

    private void clearTable() {
        defaultTableModel.getDataVector().clear();
    }

    private void saveProduct() {
        if (!nameProductTextField.getText().equals("") && !descriptionProducttextField.getText().equals("")) {
            Category category = (Category) categoryProductComboBox.getSelectedItem();
            Product product = new Product.Builder()
                    .setName(nameProductTextField.getText())
                    .setDescription(descriptionProducttextField.getText())
                    .setCategory(category).build();
            this.productController.save(product);
        }
    }

    private void createTextFileds(Container container, List<Category> categories) {
        nameProductTextField = new JTextField();
        nameProductTextField.setBounds(10, 25, 265, 20);

        descriptionProducttextField = new JTextField();
        descriptionProducttextField.setBounds(10, 65, 265, 20);

        categoryProductComboBox = new JComboBox<>();
        categoryProductComboBox.setBounds(10, 105, 265, 20);

        for (Category category : categories) {
            categoryProductComboBox.addItem(category);
        }

        container.add(nameProductTextField);
        container.add(descriptionProducttextField);
        container.add(categoryProductComboBox);
    }

    private void createLabels(Container container) {
        JLabel nameProductLabel = new JLabel("nome do produto");
        nameProductLabel.setBounds(10, 10, 240, 15);
        nameProductLabel.setForeground(Color.BLACK);

        JLabel descriptionProductLabel = new JLabel("descrição do produto");
        descriptionProductLabel.setBounds(10, 50, 240, 15);
        descriptionProductLabel.setForeground(Color.BLACK);

        JLabel categoryProductLabel = new JLabel("categoria do produto");
        categoryProductLabel.setBounds(10, 90, 240, 15);
        categoryProductLabel.setForeground(Color.BLACK);

        container.add(nameProductLabel);
        container.add(descriptionProductLabel);
        container.add(categoryProductLabel);
    }
}
