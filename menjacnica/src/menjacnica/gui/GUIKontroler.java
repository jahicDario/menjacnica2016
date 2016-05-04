package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {

	private static MenjacnicaGUI menjacnicaGui;
	private static MenjacnicaInterface menjacnicaInterfejs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnicaGui = new MenjacnicaGUI();
					menjacnicaGui.setVisible(true);
					menjacnicaInterfejs = new Menjacnica();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(menjacnicaGui.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(menjacnicaGui.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnicaInterfejs.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(menjacnicaGui.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnicaInterfejs.ucitajIzFajla(file.getAbsolutePath());
				prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	static void prikaziSveValute() {
		MenjacnicaTableModel model = (MenjacnicaTableModel)(menjacnicaGui.getTable().getModel());
		model.staviSveValuteUModel(menjacnicaInterfejs.vratiKursnuListu());

	}
	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI(menjacnicaGui);
		prozor.setLocationRelativeTo(menjacnicaGui.getContentPane());
		prozor.setVisible(true);
	}
	public static void prikaziObrisiKursGUI() {
		
		if (menjacnicaGui.getTable().getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(menjacnicaGui.getTable().getModel());
			ObrisiKursGUI prozor = new ObrisiKursGUI(menjacnicaGui,
					model.vratiValutu(menjacnicaGui.getTable().getSelectedRow()));
			prozor.setLocationRelativeTo(menjacnicaGui.getContentPane());
			prozor.setVisible(true);
		}
	}

	public static void prikaziIzvrsiZamenuGUI() {
		if (menjacnicaGui.getTable().getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(menjacnicaGui.getTable().getModel());
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(menjacnicaGui,
					model.vratiValutu(menjacnicaGui.getTable().getSelectedRow()));
			prozor.setLocationRelativeTo(menjacnicaGui.getContentPane());
			prozor.setVisible(true);
		}
	}
	
	
}
