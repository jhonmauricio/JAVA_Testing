package org.aguzman.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name="forma_pago")
    private String formaPago;

  

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    //@JoinColumn(name = "id_cliente")
                                                              // this is the main foreign key
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name="id_cliente")
    , inverseJoinColumns = @JoinColumn(name = "id_direccion")
    , uniqueConstraints = @UniqueConstraint(columnNames={"id_direccion"}))
   private List<Direccion> direcciones;
    
    // To add two parameters
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;
	
	  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	  @JoinTable(name = "tbl_client_personal", joinColumns = @JoinColumn(name="id_cliente")
	   , inverseJoinColumns = @JoinColumn(name = "id_personal")
	  , uniqueConstraints = @UniqueConstraint(columnNames={"id_personal"}))
	  private List<PersonalQuestions> personalquestions;





//    
    

  public List<PersonalQuestions> getPersonalquestions() {
		return personalquestions;
}

	public void setPersonalquestions(List<PersonalQuestions> personalquestions) {
		this.personalquestions = personalquestions;
	}

	public Cliente(Long id, List<PersonalQuestions> personalquestions) {
		super();
		this.id = id;
		this.personalquestions = personalquestions;
	}



    public Cliente() {
        facturas = new ArrayList<>();
        direcciones = new ArrayList<>();
      personalquestions = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

  


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

   public  Cliente  addDirections(Direccion direccion) {
	this.direcciones.add(direccion);
	   
	   return null;

      }


    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Cliente addFactura(Factura factura) {
        this.facturas.add(factura);
        factura.setCliente(this);
        return this;
    }

    public void removeFactura(Factura factura) {
        this.facturas.remove(factura);
        factura.setCliente(null);
    }

    @Override
    public String toString() {

        return "{" + "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago+ '\'' +
              
                ", direcciones='" + direcciones +  '\'' +
                ", facturas='" + facturas +  '\'' +
               ", personalquestions='" + personalquestions +  '\'' +
              
                '}';
    }
}
