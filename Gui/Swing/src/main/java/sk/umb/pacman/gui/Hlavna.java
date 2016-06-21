package sk.umb.pacman.gui;

import java.util.logging.Logger;

import javax.swing.JFrame;


public class Hlavna {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("sk.umb.pacman.gui");
		logger.info("Spustam program.");


		JFrame window = new JFrame("Pacman");
		window.setContentPane(new HerneOkno());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);


	}
}

