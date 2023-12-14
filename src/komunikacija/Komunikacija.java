/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class Komunikacija {
    
    private Socket s;
    private static Komunikacija instance;

    private Komunikacija() {
        try {
            s = new Socket("localhost",9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Komunikacija getInstance(){
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public ServerskiOdgovor primiOdgovor() { 
        try {
            // iz paketa transfer iz zajednickog projekta

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor)ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        
        
        
    
    public void posaljiZahtev(KlijentskiZahtev kz) {
       
            ObjectOutputStream ous;
        try {
            ous = new ObjectOutputStream(s.getOutputStream());
            
            ous.writeObject(kz);
            ous.flush();
            System.out.println("Poslat zahtev serveru od strane klijenta i ispraznjen strim za output.");
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
}
