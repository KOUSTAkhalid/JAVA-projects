import java.awt.EventQueue;

public class Main_App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main_Window();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
