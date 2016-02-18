import javax.swing.SwingUtilities;



public class Go {

	public static void main(String[] args) {
			
		// the program is still in development , much more is needed 
		// comming soon , detailed stock list for each Warehouse
		// log on every movement of the products
		// the drink and snack will have more atributes and methods
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				 new Window();

			}

		});

		
		
	}

}
