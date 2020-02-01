package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Client;

import controleur.Corsair;

public class Supprimer extends PanelCentral implements ActionListener 
{
	private JComboBox cbxRef = new JComboBox(); 
	private JButton btOk = new JButton("Supprimer"); 
	private JLabel lbtitre = new JLabel("Supression par r�f�rence : ");
	private Object unClient; 
			
	public Supprimer() {
		super(Color.red);
		
		this.lbtitre.setBounds(20, 50, 170, 30);
		this.add(this.lbtitre); 
		
		this.cbxRef.setBounds(210, 50, 100, 30);
		this.add(this.cbxRef); 
		
		this.btOk.setBounds(330, 50, 100, 30);
		this.add(this.btOk); 
		
		//On appelle la m�thode pour remplir le select
		this.remplirCbx(); 
		
		//rendre le bouton cliquable 
		this.btOk.addActionListener(this);
		
	}
	
	public void remplirCbx()
	{
		ArrayList<Client> lesProduits = Corsair.selectAllClients(); 
		
		for(Client unClientt :lesClients)
		{
			this.cbxRef.addItem(unClient.getIdClient() + " | " + unClient.getNom() + " | " + unClient.getPrenom()
			 + " | " + unClient.getAdresse() + " | " + unClient.getEmail() + " | " + unClient.getNumero_passeport()
			 + " | " + unClient.getTel() + " | " + unClient.getMdp() + " | " + unClient.getAge());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == this.btOk)
		{
			String chaine = this.cbxRef.getSelectedItem().toString(); 
			String ref = chaine.split(" | ")[0]; 
			int retour = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer ?"); 
			
			if(retour == 0)
			{
				Corsair.deleteClient();
				JOptionPane.showMessageDialog(this, "Suppresion r�ussie"); 
				this.cbxRef.removeItem(chaine);
			}
		}
		
	}
	
	public void actualise()
	{
		this.cbxRef.removeAllItems(); 
		this.remplirCbx(); 
	}

}
