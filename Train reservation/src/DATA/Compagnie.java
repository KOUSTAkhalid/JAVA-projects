package DATA;


public class Compagnie {
	public String nom;
	public String type;
	public String adresse;
	private String chiff_daff;
	
	public  int Num_train;

	public Compagnie(String n, String t, String a, String c, int N_train) {
		// TODO Auto-generated constructor stub
		nom = n;
		type = t;
		adresse = a;
		setChiff_daff(c);
		Num_train = N_train;
	}
	
	protected void Fermer_Train(Train T) {
		T.statut = "DESACTIVER";
	}
	
	protected void ouvrir_Train(Train T) {
		T.statut = "ACTIVER";
	}

	public String getChiff_daff() {
		return chiff_daff;
	}

	public void setChiff_daff(String chiff_daff) {
		this.chiff_daff = chiff_daff;
	}
}
