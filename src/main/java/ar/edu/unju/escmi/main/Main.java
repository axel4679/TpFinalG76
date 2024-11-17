package ar.edu.unju.escmi.main;

import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.dao.imp.SalonDaoImp;
import ar.edu.unju.escmi.dao.imp.ServicioAdicionalDaoImp;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.Reserva;
import ar.edu.unju.escmi.entities.Salon;
import ar.edu.unju.escmi.entities.ServiciosAdicionales;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static ClienteDaoImp clienteDao = new ClienteDaoImp();
	public static ReservaDaoImp reservaDao = new ReservaDaoImp();
	public static SalonDaoImp salonDao= new SalonDaoImp();
	public static ServicioAdicionalDaoImp servAddDao = new ServicioAdicionalDaoImp();
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        while (true) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║        MENÚ DE OPCIONES  	 	  ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ 1. Alta de cliente             	  ║");
            System.out.println("║ 2. Consultar Clientes                   ║");
            System.out.println("║ 3. Modificar Cliente       		  ║");
            System.out.println("║ 4. Realizar pago              	  ║");
            System.out.println("║ 5. Realizar reserva           	  ║");//map
            System.out.println("║ 6. Consultar todas las Reservas         ║");
            System.out.println("║ 7. Consultar una reserva                ║");
            System.out.println("║ 8. Consultar Salones                    ║");
            System.out.println("║ 9. Consultar Servicios adicionales      ║");
            System.out.println("║ 0. Salir                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            System.out.print("Selecciona una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    altaCliente();
                    break;
                case "2":
                    consultarClientes();
                    break;
                case "3":
                    modificarCliente();
                    break;
                case "4":
                    realizarPago();
                    break;
                case "5":
                    realizarReserva();
                    break;
                case "6":
                    consultarTodasLasReservas();
                    break;
                case "7":
                    consultarUnaReserva();
                    break;
                case "8":
                    consultarSalones();
                    break;
                case "9":
                    consultarServiciosAdicionales();
                    break;
                case "0":
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
          
        }
        
    }
    
    public static void precargaDatos() {
    	List<Salon> salones = new ArrayList<>(); 
    	List<Cliente> clientes = new ArrayList<>();
    	List<ServiciosAdicionales> servicios = new ArrayList<>();
    	salones.add(new Salon("Salón Cosmos", 60, false, 60000.00));
    	salones.add(new Salon("Salón Esmeralda", 20, false, 40000.00)); 
    	salones.add(new Salon("Salón Galaxy", 100, true, 60000.00)); 
    
    	for (Salon salon : salones) {
    		salonDao.guardarSalon(salon); 
    		}

        clientes.add(new Cliente("12345678", "Juan", "Pérez", "Calle Falsa 123", "555-1234", true));
        clientes.add(new Cliente("23456789", "María", "Gómez", "Avenida Siempre Viva 742", "555-5678", true));
        clientes.add(new Cliente("34567890", "Carlos", "Martínez", "Pasaje Sin Nombre 456", "555-9012", true));
        clientes.add(new Cliente("45678901", "Ana", "Rodríguez", "Boulevard Perdido 789", "555-3456", true));
        clientes.add(new Cliente("56789012", "Pedro", "López", "Ruta Inventada 321", "555-7890", true));
        clientes.add(new Cliente("67890123", "Laura", "Hernández", "Callejuela Olvidada 654", "555-4321", true));
        clientes.add(new Cliente("78901234", "Diego", "Fernández", "Camino Desconocido 987", "555-2109", true));
        clientes.add(new Cliente("89012345", "Elena", "García", "Calle Sin Salida 123", "555-6543", true));
        clientes.add(new Cliente("90123456", "Sofía", "Sánchez", "Avenida Infinita 456", "555-8765", true));
        clientes.add(new Cliente("01234567", "Luis", "Ramos", "Boulevard Circular 789", "555-0987", true));
        for (Cliente cliente : clientes) { 
        	clienteDao.altaCliente(cliente); 
        	}
        servicios.add(new ServiciosAdicionales("Cámara 360", 1500.00, true));
        servicios.add(new ServiciosAdicionales("Cabina de fotos", 2000.00, true));
        servicios.add(new ServiciosAdicionales("Filmación", 3000.00, true));
        servicios.add(new ServiciosAdicionales("Pintacaritas", 500.00, true));
        
        for (ServiciosAdicionales servicio : servicios) {
        	servAddDao.guardarServicioAdicional(servicio); 
        	}
        
        
    }

    // Funciones de ejemplo (puedes definir cada una según tus necesidades)
    public static void altaCliente() {
    	Scanner scanner  = new Scanner(System.in);
    	System.out.print("Ingrese el DNI: "); 
    	String dni = scanner.nextLine();
    	System.out.print("Ingrese el nombre: ");
    	String nombre = scanner.nextLine();
    	System.out.print("Ingrese el apellido: ");
    	String apellido = scanner.nextLine();
    	System.out.print("Ingrese el domicilio: ");
    	String domicilio = scanner.nextLine();
    	System.out.print("Ingrese el teléfono: ");
    	String telefono = scanner.nextLine();
    	
    	Cliente cliente = new Cliente(dni, nombre, apellido, domicilio, telefono, true);
    	clienteDao.altaCliente(cliente);
    	
    	System.out.println("Cliente guardado: " );
    	cliente.mostrarDatos();
   
    	
    }


    public static void consultarClientes() {
    	Scanner scanner  = new Scanner(System.in);
    	System.out.println("Función de consulta de clientes");
    	System.out.print("Ingrese el ID del cliente a consultar: ");
    	long clienteId = scanner.nextLong();
    	Cliente cliente= clienteDao.consultarClienteId(clienteId);
    	cliente.mostrarDatos();
    	}

    public static void modificarCliente() {
    	Scanner scanner  = new Scanner(System.in);
    	long iid_cli = 0;
		while (true) {
			System.out.print("Ingrese el ID del cliente a modificar: ");
			try {
				iid_cli = scanner.nextLong();
				scanner.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
				scanner.nextLine();
			}
		}
		Cliente cliente = clienteDao.obtenerClienteId(iid_cli);
		if (cliente != null) {
			System.out.print("Ingrese nuevo nombre del cliente: ");
			String nombre = scanner.nextLine();
			System.out.print("Ingrese nuevo apellido del cliente: ");
			String apellido = scanner.nextLine();
			System.out.print("Ingrese nuevo domicilio del cliente: ");
			String domicilio = scanner.nextLine();
			String dni;
			while (true) {
				System.out.print("Ingrese nuevo DNI del cliente: ");
				try {
						dni  = scanner.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Error: Debe ingresar un número entero para el DNI del cliente.");
					scanner.nextLine();
				}
			}
			System.out.print("Ingrese nuevo Telefono del cliente: ");
			String telefono = scanner.nextLine();
			cliente.setDni(dni);
			cliente.setApellido(apellido);
			cliente.setDomicilio(domicilio);
			cliente.setNombre(nombre);
			cliente.setTelefono(telefono);
			clienteDao.modificarCliente(cliente);
			System.out.println("Cliente modificado exitosamente.");
		} else {
			System.out.println("Cliente no encontrado.");
		}
    }
    	

    public static void realizarPago() {
        System.out.println("Función de realizar pago");
        
    }

    public static void realizarReserva() {
    	Scanner scanner  = new Scanner(System.in);
        System.out.println("Función de realizar reserva");
        
        Cliente cliente;
        System.out.println("Ingrese id del Cliente :");
		long idcli = scanner.nextInt();
		cliente= clienteDao.obtenerClienteId(idcli);
		if(cliente!=null) {
			System.out.println("Cliente Existe");
			
		}
		else {
			altaCliente();
			cliente= clienteDao.obtenerClienteId(idcli);
			
		}
		
		System.out.println("Ingrese salon que desea reservar :");
		List<Salon> salones = salonDao.consultarSalones();
		int x =1;
		for( Salon salon  : salones) {
			System.out.println(x + "-"+ salon.getNombre());
			x=x+1;
		}
		
        
        
    }

    public static void consultarTodasLasReservas() {
        System.out.println("Función de consultar todas las reservas");
    }

    public static void consultarUnaReserva() {
        System.out.println("Función de consultar una reserva");
    }

    public static void consultarSalones() {
    	Scanner scanner  = new Scanner(System.in);
    	System.out.println("Función de consultar salones");
    	System.out.println("Ingrese id de salon a consultar");
    	long idconsultar = scanner.nextLong();
    	List<Salon> salones = salonDao.consultarSalones();
    	for (Salon salon : salones) {
    		if(salon.getId()== idconsultar) {
    			System.out.println("Cliente existente");
    			salon.mostrarDatos();
    			break;
    		}
    		else {
    			System.out.println("El cliente no existe ");
    		}
    	}
    }

    public static void consultarServiciosAdicionales() {
        System.out.println("Función de consultar servicios adicionales");
    }
}
