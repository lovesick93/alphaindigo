package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Client;

import controleur.Corsair;
import controleur.Tableau;

/*Ajouter*/
public class PanelClient extends PanelCentral implements ActionListener {
	
	
	private JTextField txtIdClient = new JTextField();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtNumero_passeport = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtAge = new JTextField();
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	public PanelClient() {
		super(Color.cyan);
		
		this.setLayout(new GridLayout(5,2));
		
		this.add(new JLabel("IdClient : "));
		this.add(this.txtIdClient);
		
		this.add(new JLabel("Nom : "));
		this.add(this.txtNom);
		
		this.add(new JLabel("Prénom: "));
		this.add(this.txtPrenom);
		
		this.add(new JLabel("Adresse : "));
		this.add(this.txtAdresse);

		this.add(new JLabel("Email : "));
		this.add(this.txtEmail);
		
		this.add(new JLabel("Numéro de Passeport : "));
		this.add(this.txtNumero_passeport);
		
		this.add(new JLabel("Telephone : "));
		this.add(this.txtTel);
		
		this.add(new JLabel("Mdp : "));
		this.add(this.txtMdp);
		
		this.add(new JLabel("Age : "));
		this.add(this.txtAge);
		
		this.add(this.btAnnuler);
		this.add(this.btEnregistrer);
		
		//rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.txtIdClient.setText("");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtNumero_passeport.setText("");
			this.txtTel.setText("");
			this.txtMdp.setText("");
			this.txtAge.setText("");
			this.setVisible(false);
		}
		else if (e.getSource() == this.btEnregistrer)
		{
			Client unClient = new Client (
					this.txtIdClient.getText(), this.txtNom.getText(), this.txtPrenom.getText(), 
					this.txtAdresse.getText(), this.txtEmail.getText(),this.txtNumero_passeport.getText(),
					this.txtTel.getText(), this.txtMdp.getText(), this.txtAge.getText()
					);
			
			Corsair.insertClient(unClient);
			JOptionPane.showMessageDialog(this,"Insertion réussie");
			//mise à jouer de la JTable via le Tableau
			
			Object [] ligne = {this.txtIdClient.getText(), this.txtNom.getText(), this.txtPrenom.getText(), 
					this.txtAdresse.getText(), this.txtEmail.getText(),this.txtNumero_passeport.getText(),
					this.txtTel.getText(), this.txtMdp.getText(), this.txtAge.getText()};
			
			Generale.getUnLister().getTableau().ajouterLigne(ligne);
			
			/********************************************************************/
			this.txtIdClient.setText("");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtNumero_passeport.setText("");
			this.txtTel.setText("");
			this.txtMdp.setText("");
			this.txtAge.setText("");
			this.setVisible(false);
		}else
		{
			JOptionPane.showMessageDialog(this, "Attention aux mot de passe !");
			
		}
	}
	
	/*Lister*/
	private JTable uneTable ;
	private Tableau unTableau ;
	
	public void Lister() extends PanelCentral {
		super(Color.green);
		
		String entetes [] = {"idclient","nom","prenom","adresse","email","numero_passeport","tel","mdp","age"}; 
		
		//instancier le tableau
		this.unTableau = new Tableau(entetes, Corsair.getDonnees(Corsair.selectAllClients()));
				
		this.uneTable = new JTable(this.unTableau);
		
		//affichage de la Jtable dans une JscrollPane 
		JScrollPane uneScroll = new JScrollPane(this.uneTable); 
		uneScroll.setBounds(20, 20, 400, 300);
		this.add(uneScroll);
		
	}

	public Tableau getTableau()
	{
		return this.unTableau;
	}

}
