package org.aguzman.hibernateapp;

import jakarta.persistence.EntityManager;
import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.ClienteDetalle;
import org.aguzman.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("this is another client", "override");
            cliente.setFormaPago("creditcard gonorrea");
            
            Cliente cliente2 = new Cliente("Testing!!!!!!!", "override");
            cliente2.setFormaPago("mastercard");
            em.persist(cliente2);
            em.persist(cliente);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            ClienteDetalle detalle2 = new ClienteDetalle(true, 6000L);
            em.persist(detalle2);

            em.persist(detalle);

            cliente.setDetalle(detalle);
            cliente.setDetalle(detalle2);

            em.getTransaction().commit();

            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
