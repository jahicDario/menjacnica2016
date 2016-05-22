package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {

	private static MenjacnicaGUI menjacnicaGui;
	private static MenjacnicaInterface menjacnicaInterfejs;
	private static ObrisiKursGUI obrisiKurs;
	
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
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(menjacnicaGui.getContentPane());
		prozor.setVisible(true);
	}
	public static void prikaziObrisiKursGUI(MenjacnicaTableModel model, int row) {
		
		if (menjacnicaGui.getTable().getSelectedRow() != -1) {
			obrisiKurs = new ObrisiKursGUI(model.vratiValutu(row));
			obrisiKurs.setLocationRelativeTo(menjacnicaGui.getContentPane());
			obrisiKurs.setVisible(true);
		}
	}

	public static void prikaziIzvrsiZamenuGUI(MenjacnicaTableModel model, int row) {
		if (menjacnicaGui.getTable().getSelectedRow() != -1) {
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(model.vratiValutu(row));
			prozor.setLocationRelativeTo(menjacnicaGui.getContentPane());
			prozor.setVisible(true);
		}
	}

	public static void dodajValutu(Valuta valuta) {
		menjacnicaInterfejs.dodajValutu(valuta);
		menjacnicaGui.prikaziSveValute();
	}

	public static List<Valuta> vratiKursnuListu() {
		return menjacnicaInterfejs.vratiKursnuListu();
	}

	public static String izvrsiTransakciju(Valuta valuta, boolean selected, String text) {
		double transakcija = menjacnicaInterfejs.izvrsiTransakciju(valuta, selected, Double.parseDouble(text));
		return String.valueOf(transakcija);
	}

	public static void obrisiValutu(Valuta valuta) {
		try {
			menjacnicaInterfejs.obrisiValutu(valuta);
			menjacnicaGui.prikaziSveValute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(obrisiKurs.getContentPane(), e.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
