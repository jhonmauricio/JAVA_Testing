package org.aguzman.hibernateapp;

import jakarta.persistence.EntityManager;

import java.util.Iterator;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Direccion;
import org.aguzman.hibernateapp.entity.PersonalQuestions;
import org.aguzman.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("mercado pago");
     
            Cliente cliente2 = new Cliente("ariadna", "sofia");
            cliente2.setFormaPago("visa");
            
            Cliente cliente3 = new Cliente("Maria Rocio", "Lopez");
            cliente3.setFormaPago("visa");
            
           PersonalQuestions p1 = new PersonalQuestions("juan caros" , 1);
           PersonalQuestions p2 = new PersonalQuestions("adriana rua" , 2);
           PersonalQuestions p3 = new PersonalQuestions("niniana" , 3);

                                        //ORPHANREMOVAL
            // this would be the address which will be eliminating for our example
            Direccion d1 = new Direccion("el vergel", 123);
            Direccion d2 = new Direccion("el vergel", 123);

            
            Direccion d3= new Direccion("pueblorrico", 1234567);
            Direccion d4 = new Direccion("jerico", 45612345);
            Direccion d5 = new Direccion("tarso", 45612345);
            
        
            cliente2.getDirecciones().add(d4);
            cliente2.getDirecciones().add(d5);
            

              cliente3.getPersonalquestions().add(p1);
              cliente3.getPersonalquestions().add(p2);
              cliente3.getPersonalquestions().add(p3);
            
       

           if (d1.equals(d2)) {
        	   
        	   System.out.println("the address was changed by me");
        	   d2 = new Direccion("this has been changed in a test",8888);

               cliente.addDirections(d2);
               
		}else {
			System.out.println("the information has not changed");

		}
           
    	   cliente.addDirections(d1);

           em.persist(cliente);

            em.persist(cliente2);
            em.persist(cliente3);
  


            em.getTransaction().commit();

            System.out.println(cliente);

            em.getTransaction().begin();
            cliente = em.find(Cliente.class, cliente.getId());
            cliente2 = em.find(Cliente.class, cliente2.getId());

            
         // cliente.getDirecciones().remove(d1);
         // cliente2.getDirecciones().remove(d4);
         // cliente2.getDirecciones().remove(d5);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
