import java.awt.EventQueue;

import DATA.Compagnie;

public class Main_App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compagnie Comp = new Compagnie("AFRICA TGV", "SARL", "8 Bis, Rue Abderrahmane El Ghafiki, Agdal - Rabat.", "3,76 MMDH", 265);
					new Main_Window(Comp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
