package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.PredmetiController;
import controller.ProfesoriController;
import controller.StudentiController;

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu(final JFrame parent) {
		java.awt.Color banana = new java.awt.Color(255, 225, 53, 183);
		setBackground(banana);

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem newEntity = new JMenuItem("New", new ImageIcon("images/sq_plus_icon&16.png"));
		newEntity.setMnemonic(KeyEvent.VK_N);
		newEntity.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newEntity.setBackground(banana);

		newEntity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().dodajStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().dodajProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().dodajPredmet(parent);
				}

			}
		});

		JMenuItem close = new JMenuItem("Close", new ImageIcon("images/app_window_cross&16.png"));
		close.setMnemonic(KeyEvent.VK_C);
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		close.setBackground(banana);

		file.add(newEntity);
		file.addSeparator();
		file.add(close);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				String[] options = new String[2];
				options[0] = new String("Da");
				options[1] = new String("Ne");
				int option = JOptionPane.showOptionDialog(frame.getContentPane(),
						"Da li želite da napustite aplikaciju?", "Izlazak iz aplikacije", 0,
						JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (option == JOptionPane.YES_OPTION)
					parent.dispose();
			}
		});

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		JMenuItem editItem = new JMenuItem("Edit", new ImageIcon("images/pencil_icon&16.png"));
		editItem.setMnemonic(KeyEvent.VK_I);
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editItem.setBackground(banana);

		editItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().izmeniStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().izmeniProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().izmeniPredmet(parent);
				}
			}
		});

		JMenuItem delete = new JMenuItem("Delete", new ImageIcon("images/trash_icon&16.png"));
		delete.setMnemonic(KeyEvent.VK_D);
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		delete.setBackground(banana);

		edit.add(editItem);
		edit.addSeparator();
		edit.add(delete);

		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainWindow.getInstance().getTabIndex() == 0) {
					StudentiController.getInstance().obrisiStudenta(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 1) {
					ProfesoriController.getInstance().obrisiProfesora(parent);
				} else if (MainWindow.getInstance().getTabIndex() == 2) {
					PredmetiController.getInstance().obrisiPredmet(parent);
				}
			}
		});
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		JMenuItem helpItem = new JMenuItem("Help", new ImageIcon("images/doc_lines_icon&16.png"));
		helpItem.setMnemonic(KeyEvent.VK_L);
		helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		helpItem.setBackground(banana);
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog dialog = new HelpDialog(parent, "Help", true);
				dialog.setVisible(true);
			}
		});

		JMenuItem about = new JMenuItem("About", new ImageIcon("images/info_icon&16.png"));
		about.setMnemonic(KeyEvent.VK_A);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.setBackground(banana);
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialog = new AboutDialog(parent, "About", true);
				dialog.setVisible(true);
			}
		});

		help.add(helpItem);
		help.addSeparator();
		help.add(about);

		add(file);
		add(edit);
		add(help);
	}
}
