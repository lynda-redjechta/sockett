import java.net.* ;
import java.io.* ;
import java.util.Scanner;

public class Client1 {
	public static void affiche_matrice (int t[][])
	{
	int i, j;
	int space;
	String s;

	System.out.println();
	for (i = 0 ; i < t.length ; i = i + 1 )
	   { 
	   for (j = 0 ; j < t[0].length ; j = j + 1 ) 

		{
	        s = Integer.toString(t[i][j]);
	        if(j == 0)
	          System.out.print(" | ") ;
	        System.out.print(t[i][j]+ " ") ;
	        for(space = s.length(); space < 7; space++)
	             System.out.print(" ");
	        }
	   System.out.print(" | ") ;
	   System.out.println();
	   }       
	}


	public static void main(String[]args) throws ClassNotFoundException{
		new Client1();
	}
	public Client1() throws ClassNotFoundException{
		
		try {
			Socket s=new Socket("127.0.0.1", 6666);
			//lire ce qu'on a recu
			/*InputStream is=s.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String str=br.readLine();
			System.out.println("Le serveur :"+str);*/
			//envoyer
			//yaricupiri les flux
			OutputStream os= s.getOutputStream();
			//for writing matr and send it
			PrintWriter pw= new PrintWriter(os,true);
			
			pw.println("produit matriciel");
			// l'id
			
			
			Scanner n=new Scanner(System.in);
			
				//read from consol	    

				System.out.println("Tapez le nombre de lignes de la 1ere matrice");
				int nbrl1=n.nextInt();
				System.out.println("Tapez le nombre de colones de la 1ere matrice");
				int nbrc1=n.nextInt();
				int [][]mat1=new int[nbrl1][nbrc1];
				int val=0;
				for(int p=0;p<mat1.length;p++){
					for(int q=0;q<mat1[0].length;q++)
						{
						System.out.println("Entrez l'element ["+p+"]["+q+"] de la matrice :");
						val=n.nextInt();
						mat1[p][q]=val;
						val=0;
						}
				}
				//send matrice to server f flu de sortie
				OutputStream os2=s.getOutputStream();
				ObjectOutputStream oos2=new ObjectOutputStream(os2);
			    //int mat1[][]={{7,8},{9,10},{11,12}};
				oos2.writeObject(mat1);
				
				System.out.println("Tapez le nombre de lignes de la 2eme matrice");
				int nbrl2=n.nextInt();
				System.out.println("Tapez le nombre de colones de la 2eme matrice");
				int nbrc2=n.nextInt();
				int [][]mat2=new int[nbrl2][nbrc2];
				int val2=0;
				for(int p2=0;p2<mat2.length;p2++){
					for(int q2=0;q2<mat2[0].length;q2++)
						{
						System.out.println("Entrez l'element ["+p2+"]["+q2+"] de la matrice :");
						val2=n.nextInt();
						mat2[p2][q2]=val2;
						val2=0;
						}
				}
				//int mat2[][]={{1,2,3},{4,5,6}};
				OutputStream os3=s.getOutputStream();
				ObjectOutputStream oos3=new ObjectOutputStream(os3);
				oos3.writeObject(mat2);
				
				InputStream is2=s.getInputStream();
				ObjectInputStream ois2=new ObjectInputStream(is2);
				int [][]matresultat= (int[][]) ois2.readObject();
				System.out.println("Le resultat du produit des deux matrice est :");
				affiche_matrice(matresultat);
			    //videz le flu d sortie
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

