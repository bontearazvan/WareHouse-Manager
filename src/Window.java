import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Window extends JFrame {

	
	private static final long serialVersionUID = 1L;

	// elements needed for Window
	JPanel panelWarehouse1 = new JPanel(new BorderLayout());
	JPanel panelWarehouse2 = new JPanel(new BorderLayout());
	JPanel panelCentral = new JPanel();
	JPanel panelLabel = new JPanel();
	JLabel picture = new JLabel(new ImageIcon("warehouse_icon2.png"));
	JLabel picture2 = new JLabel(new ImageIcon("forklift_256.png"));
	JButton buttonMove1 = new JButton("Move >>");
	JButton buttonMove2 = new JButton("<< Move");
	JButton buttonAdd = new JButton("Add new Drink");
	JButton buttonDelete = new JButton("Delete a product");
	JLabel label = new JLabel("Welcome to Warehouse Manager");
	JLabel labelWarehouse1 = new JLabel("Warehouse Muncii");
	JLabel labelWarehouse2 = new JLabel("Warehouse Dudesti");
	Warehouse warehouse2 = new Warehouse("Dudesti");
	Warehouse warehouse1 = new Warehouse("Muncii");
	JList<String> jListwarehouse1 = new JList(warehouse1);
	JList<String> jListwarehouse2 = new JList(warehouse2);

	
	
	Window() {
		
		initialize();
		
		}
	

	// writing to file the logging date
	public void initialize() {
		File file = new File("log.txt");
        try (BufferedWriter output = new BufferedWriter(new FileWriter(file,true));){
        
          output.write("logare la data "+ new GregorianCalendar().getTime().toString()+"\n");
          
        } catch ( IOException e ) {
	}
    
		
		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Warehouse Manager");
		
		// populating the 2 warehouse lists with some products
		for (int i = 0; i < 18; i++) {
			warehouse2.getWarehouseList().add(new Snack("Alune", 3));
			warehouse1.getWarehouseList().add(new Drink("Fanta", 5));
		}
		// organizing the components for the Window

	
		
		this.add(panelLabel, BorderLayout.NORTH);  
		panelLabel.add(label);
		label.setFont(new Font("Arial",Font.BOLD,20));
		this.add(panelWarehouse1, BorderLayout.WEST);
		this.add(panelWarehouse2, BorderLayout.EAST);
		panelWarehouse1.add(labelWarehouse1, BorderLayout.NORTH);
		panelWarehouse2.add(labelWarehouse2, BorderLayout.NORTH);
		panelWarehouse1.add(jListwarehouse1);
		panelWarehouse2.add(jListwarehouse2);
		panelWarehouse1.add(buttonMove1, BorderLayout.SOUTH);
		panelWarehouse2.add(buttonMove2, BorderLayout.SOUTH);
		this.add(panelCentral);
		panelCentral.add(buttonAdd);
		panelCentral.add(buttonDelete);
		panelCentral.add(picture);
		panelCentral.add(picture2);
		JScrollPane scrollPane = new JScrollPane();
		panelWarehouse1.add(scrollPane,BorderLayout.EAST);
		scrollPane.setViewportView(jListwarehouse1);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scrollPane2 = new JScrollPane();
		panelWarehouse2.add(scrollPane2,BorderLayout.EAST);
		scrollPane2.setViewportView(jListwarehouse2);
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		picture.setVisible(false);
		
		
		// first move button that moves the product from the left warehouse to the right one
		buttonMove1.addActionListener(new ActionListener() {
			
			
			@Override	// getting the selected index and removing the product while adding it to the other warehouse
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					Product prod = (Product) warehouse1.getElementAt(jListwarehouse1.getSelectedIndex());
					warehouse2.addProductToWarehouse(warehouse1.removeProductFromWarehouse(
							(Product) warehouse1.getElementAt(jListwarehouse1.getSelectedIndex())));
					
					label.setText("You took: " + prod + " from Warehouse " + warehouse1.getName());

				} catch (Exception e) {
					label.setText("Please select a product from the list");
				}
			}

		});
		// second Move button , does the exact thing as the one above , just from the right to left
		buttonMove2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Product prod = (Product) warehouse2.getElementAt(jListwarehouse2.getSelectedIndex());
					warehouse1.addProductToWarehouse(warehouse2.removeProductFromWarehouse(
							(Product) warehouse2.getElementAt(jListwarehouse2.getSelectedIndex())));
					label.setText("You took: " + prod + " from Warehouse " + warehouse2.getName());

				} catch (Exception e) {
					label.setText("Please select a product from the list");
				}
			}

		});

		// the Delete button that uses select index and clears the selection afterwards
		// it can delete from both warehouse at the same time if they are selected
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warehouse1.removeProductFromWarehouse(
							warehouse1.getWarehouseList().get(jListwarehouse1.getSelectedIndex()));
					jListwarehouse1.clearSelection();

				} catch (Exception i) {

				}
				try {
					warehouse2.removeProductFromWarehouse(
							warehouse2.getWarehouseList().get(jListwarehouse2.getSelectedIndex()));
					jListwarehouse2.clearSelection();

				} catch (Exception b) {
				}

			}
		});

		// button for adding new Products , creates a new JFrame
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JButton save = new JButton("Save");
				JPanel central = new JPanel();
				JRadioButton wh2 = new JRadioButton("WH Dudesti");
				JRadioButton wh1 = new JRadioButton("WH Muncii");
				JTextField nameBox = new JTextField(10);
				JLabel labelName = new JLabel("Name : ");
				JLabel labelPrice = new JLabel("Price : ");
				JTextField priceBox = new JTextField(4);
				JLabel notice = new JLabel("Enter the product:");
				frame.add(notice, BorderLayout.NORTH);
				frame.add(wh2, BorderLayout.EAST);
				frame.add(wh1, BorderLayout.WEST);
				frame.add(central);
				frame.add(save, BorderLayout.SOUTH);
				central.add(labelName);
				central.add(nameBox);
				central.add(labelPrice);
				central.add(priceBox);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setSize(400, 150);
				frame.setAlwaysOnTop(true);
				frame.setLocationRelativeTo(null);
				
				// the save button actions 
				// it only lets you save if you give all the input needed 
				// it warns you on the label what are you doing wrong
				// you can add to both Warehouses at the same time
				save.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (nameBox.getText().equals("")) {
							notice.setText("Please give a name to the product:");
							return;
						}
						if (priceBox.getText().equals("")) {
							notice.setText("Please give a price to the product:");
							return;
						}
						try {
							Integer i = 0;
							i = i.parseInt(priceBox.getText());
							if (i < 0) {
								notice.setText("Please give a price not lower than Zero");
								return;
							}
						} catch (Exception d) {
							notice.setText("Please give a price NUMBER to the product:");
							return;
						}

						if (!wh2.isSelected() && !wh1.isSelected()) {
							notice.setText("Please select one or both Warehouses");
							return;
						}
						Integer i = 0;
						i = i.parseInt(priceBox.getText());
						if (wh2.isSelected() && wh1.isSelected()) {
							warehouse2.addProductToWarehouse(new Drink(nameBox.getText(), i));
							warehouse1.addProductToWarehouse(new Drink(nameBox.getText(), i));
							frame.dispose();
							return;
						}
						if (wh2.isSelected()) {
							warehouse2.addProductToWarehouse(new Drink(nameBox.getText(), i));
							frame.dispose();
						}
						if (wh1.isSelected()) {
							warehouse1.addProductToWarehouse(new Drink(nameBox.getText(), i));
							frame.dispose();
						}
					}
				});

			}

		});
	}

}
