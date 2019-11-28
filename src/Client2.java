import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {


	public static void main(String[]args) throws ClassNotFoundException{
		new Client2();
	}
	public Client2() throws ClassNotFoundException{
		
		try {
			Socket s=new Socket("127.0.0.1", 6666);
			//lire ce qu'on a recu
			/*InputStream is=s.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String str=br.readLine();
			System.out.println("Le serveur :"+str);*/
			//envoyer
			OutputStream os= s.getOutputStream();
			PrintWriter pw= new PrintWriter(os,true);
			System.out.println("Ecrire la requête:");
			
			Scanner sc=new Scanner(System.in);
			String envoye=sc.nextLine();
			pw.println(envoye);
			
			Scanner n=new Scanner(System.in);
		
				OutputStream os1=s.getOutputStream();
				ObjectOutputStream oos=new ObjectOutputStream(os1);
				System.out.println("Entrer le nombre d'elements du tableau");
				
				int nbrelem=n.nextInt();
				int []t = new int [nbrelem];
				int j=1;
				for(int i1=0;i1<nbrelem;i1++)
				{
					System.out.println("Tapez l'element numero "+j+" du tableau");
					t[i1]=n.nextInt();
					j++;
				}
				
				oos.writeObject(t);
				
				InputStream is1=s.getInputStream();
				ObjectInputStream ois=new ObjectInputStream(is1);
				int [] tab2= (int[]) ois.readObject();
				System.out.println("Le tableau trié :");
				System.out.print("{ ");
				for(int i=0;i<tab2.length;i++){
					System.out.print(tab2[i]+"   ");
					}
				System.out.print("}");
				
			pw.flush();
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}