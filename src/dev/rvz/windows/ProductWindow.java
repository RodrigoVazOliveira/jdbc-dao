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
    private final CategoryController categoryController;

    private JLabel nameProductLabel;
    private JTextField nameProductTextField;
    private JLabel descriptionProductLabel;
    private JTextField descriptionProducttextField;
    private JLabel categoryProductLabel;
    private JComboBox<Category> categoryProductComboBox;
    private JButton saveButton, updateButton, clearButton, removeButton;
    private JTable table1;
    private DefaultTableModel defaultTableModel;


    public ProductWindow(String title, ProductController productController, CategoryController categoryController) throws HeadlessException {
        super(title);
        this.productController = productController;
        this.categoryController = categoryController;

        Container container = getContentPane();
        setLayout(null);

        createLabels(container);
        createTextFileds(container, this.categoryController.getAll());
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
        Integer width = 150;
        saveButton = new JButton("Salvar produto");
        saveButton.setBounds(10, 145, width, 20);

        clearButton = new JButton("Limpar");
        clearButton.setBounds(200, 145, width, 20);

        removeButton = new JButton("Excluir");
        removeButton.setBounds(10, 500, width, 20);

        updateButton = new JButton("Atualizar");
        updateButton.setBounds(200, 500, width, 20);

        container.add(saveButton);
        container.add(clearButton);
        container.add(removeButton);
        container.add(updateButton);

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
        nameProductLabel = new JLabel("nome do produto");
        nameProductLabel.setBounds(10, 10, 240, 15);
        nameProductLabel.setForeground(Color.BLACK);

        descriptionProductLabel = new JLabel("descrição do produto");
        descriptionProductLabel.setBounds(10, 50, 240, 15);
        descriptionProductLabel.setForeground(Color.BLACK);

        categoryProductLabel = new JLabel("categoria do produto");
        categoryProductLabel.setBounds(10, 50, 240, 15);
        categoryProductLabel.setForeground(Color.BLACK);

        container.add(nameProductLabel);
        container.add(descriptionProductLabel);
        container.add(categoryProductLabel);
    }
}
