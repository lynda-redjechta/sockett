import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Serveur extends Thread {
	
	private int nombreclients;
	
	public static void main(String[]args){
		new Serveur().start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		Socket s = null;
		try {
			ss=new ServerSocket(6666);
			System.out.println("En attente de connexion");
			while(true){
				s=ss.accept();
				nombreclients++;
				new Service(s).start();
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			s.close();

			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}





class Service extends Thread{
	Socket socket;
	int nombreclients;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Le client numero "+nombreclients+" s'est connécté");
		//lire ce qu'on a recu
		try {
			InputStream is=socket.getInputStream();
			OutputStream os= socket.getOutputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String str=br.readLine();
			
			System.out.println("Client " +nombreclients+ " "+str);
			
			//envoyer
			/*PrintWriter pw= new PrintWriter(os,true);
			System.out.println("En attende de message");
			String str=br.readLine();
			System.out.println("Client: "+nombreclients+str);
			System.out.println("Envois de reponse");			
			Scanner sc=new Scanner(System.in);
			String reponse=sc.nextLine();
			pw.println(reponse);*/
			
			
			if(str.equals("produit matriciel")){
				InputStream is2=socket.getInputStream();
				ObjectInputStream ois2=new ObjectInputStream(is2);
				int [][] mat1= (int[][]) ois2.readObject();
				System.out.println("Matrice 1 recue");
					
				InputStream is3=socket.getInputStream();
				ObjectInputStream ois3=new ObjectInputStream(is3);
				int [][]mat2=(int [][]) ois3.readObject();
				System.out.println("Matrice 2 recue");
					
				int [][] matresultat=produitMatrice(mat1, mat2);

				OutputStream os5=socket.getOutputStream();
				ObjectOutputStream oos5=new ObjectOutputStream(os5);
				oos5.writeObject(matresultat);

				System.out.println("Resultat envoyé");
			}
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Service(Socket socket){
		this.socket=socket;
		
	}
	
	public int[][] produitMatrice(int[][] m1, int[][] m2) throws RemoteException {
		// TODO Auto-generated method stub
		int i = 0;
		int produit=0;
		int[][]mat3 = new int[m1.length][m2[0].length];
		for ( i=0; i<m1.length;i++){
			for (int j=0; j<m1.length;j++){

				for (int k=0; k<m1[0].length;k++)
				{
					produit+=m1[i][k]*m2[k][j];
				}
				mat3[i][j]=produit;
				produit=0;
				
			}
		}
		return mat3;
	}
}