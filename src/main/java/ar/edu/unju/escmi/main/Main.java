package ar.edu.unju.escmi.main;

import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.dao.imp.SalonDaoImp;
import ar.edu.unju.escmi.dao.imp.ServicioAdicionalDaoImp;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.Reserva;
import ar.edu.unju.escmi.entities.Salon;
import ar.edu.unju.escmi.entities.ServiciosAdicionales;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static ClienteDaoImp clienteDao = new ClienteDaoImp();
	public static ReservaDaoImp reservaDao = new ReservaDaoImp();
	public static SalonDaoImp salonDao = new SalonDaoImp();
	public static ServicioAdicionalDaoImp servAddDao = new ServicioAdicionalDaoImp();
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// precargaSalon();
		// precargaServiciosAdicionales();
		while (true) {
			System.out.println("╔═════════════════════════════════════════╗");
			System.out.println("║        MENÚ DE OPCIONES  	 	  ║");
			System.out.println("╠═════════════════════════════════════════╣");
			System.out.println("║ 1. Alta de cliente             	  ║");
			System.out.println("║ 2. Consultar Clientes                   ║");
			System.out.println("║ 3. Modificar Cliente       		  ║");
			System.out.println("║ 4. Realizar pago              	  ║");
			System.out.println("║ 5. Realizar reserva           	  ║");// map
			System.out.println("║ 6. Consultar todas las Reservas         ║");
			System.out.println("║ 7. Consultar una reserva                ║");
			System.out.println("║ 8. Consultar Salones                    ║");
			System.out.println("║ 9. Consultar Servicios adicionales      ║");
			System.out.println("║ 0. Salir                                ║");
			System.out.println("╚═════════════════════════════════════════╝");
			System.out.print("Selecciona una opción: ");

			switch (scanner.nextLine()) {
			case "1":
				Cliente clienteNuevo = new Cliente();
				clienteNuevo = cargaCliente(clienteNuevo);
				if (clienteNuevo != null) {
					clienteDao.altaCliente(clienteNuevo);
					System.out.println("Cliente guardado:");
				} else {
					System.out.println("Cliente NO guardado: ");
				}
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
				Reserva reserva = buscarUnaReserva();
				if (reserva != null) {
					reserva.mostrarDatos();
				} else {
					System.out.println("El cliente no existe");
				}
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

	public static Cliente cargaCliente(Cliente cliente) {
		System.out.print("Ingrese el DNI: ");
		cliente.setDni(scanner.nextLine());
		System.out.print("Ingrese el nombre: ");
		cliente.setNombre(scanner.nextLine());
		System.out.print("Ingrese el apellido: ");
		cliente.setApellido(scanner.nextLine());
		System.out.print("Ingrese el domicilio: ");
		cliente.setDomicilio(scanner.nextLine());
		System.out.print("Ingrese el teléfono: ");
		cliente.setTelefono(scanner.nextLine());
		cliente.setEstado(true);
		return cliente;

	}

	public static void modificarCliente() {
		Cliente clienteMod = buscarUnCliente();
		if (clienteMod != null) {
			clienteMod = cargaCliente(clienteMod);
			clienteDao.modificarCliente(clienteMod);
			System.out.println("El cliente modificado");
		} else {
			System.out.println("El cliente no existe");
		}
	}

	public static void realizarPago() {
		System.out.println("Función de realizar pago");
		Reserva reserva = buscarUnaReserva();
		if (reserva == null) {
			System.out.println("El reserva no existe");
			return;
		}

		System.out.println("Detalles de la Reserva");
		reserva.mostrarDatos();
		double montoTotal = reserva.calcularMontoTotal();
		double montoPendiente = reserva.calcularPagoPendiente();
		System.out.println("Monto total: " + montoTotal);
		System.out.println("Monto pendiente: " + montoPendiente);

		if (montoPendiente == 0) {
			System.out.println("¡La reserva ha sido completamente pagada!");
			return;
		}

		while (true) {
			try {
				System.out.println("Ingrese el monto a pagar: ");
				double pago = scanner.nextDouble();

				if (pago <= 0) {
					System.out.println("Error: El monto debe ser mayor a cero.");
				} else if (pago > montoPendiente) {
					System.out.printf("Error: El monto excede el pendiente ", montoPendiente);
				} else {
					reserva.setMonto_pagado(reserva.getMonto_pagado() + pago);

					System.out.println("Pago realizado: " + pago);
					System.out.println("Monto pendiente actualizado: " + reserva.calcularPagoPendiente());
					reserva.setCancelado(reserva.calcularPagoPendiente() <= 0);
					reservaDao.realizarReserva(reserva);
					break;
				}
			} catch (Exception e) {
				System.out.println("Error: Entrada inválida. Intente de nuevo.");
			} finally {
				scanner.nextLine();
			}
		}

	}

	public static void realizarReserva() {
		System.out.println("Función de realizar reserva");
		Reserva reserva = new Reserva();
		Cliente cliente = buscarUnCliente();
		if (cliente != null) {
			System.out.println("Cliente Existe");
		} else {
			cliente = new Cliente();
			System.out.println("Cliente N0 Existe");
			System.out.println("Creando cliente");
			clienteDao.altaCliente(cargaCliente(cliente));
			System.out.println("Cliente Creado");
		}
		reserva.setCliente(cliente);

		Long salonId;
		while (true) {
			try {
				System.out.print("Ingrese ID del salón: ");
				salonId = scanner.nextLong();
				Salon salon = salonDao.obtenerSalonId(salonId);
				if (salon != null) {
					reserva.setSalon(salon);
					break;
				}
				System.out.println("Salon no existe");
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el ID del cliente.");
			} finally {
				scanner.nextLine();
			}
		}

		while (true) {
			try {
				System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
				reserva.setFecha(LocalDate.parse(scanner.nextLine()));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Formato de fecha incorrecto. Intente de nuevo.");
			}
		}

		while (true) {
			try {
				System.out.print("Ingrese la hora de inicio (HH:MM): ");
				reserva.setHs_inicio(LocalTime.parse(scanner.nextLine()));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Formato de hora incorrecto. Intente de nuevo.");
			}
		}

		while (true) {
			try {
				System.out.print("Ingrese la hora de fin (HH:MM): ");
				reserva.setHs_fin(LocalTime.parse(scanner.nextLine()));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Formato de hora incorrecto. Intente de nuevo.");
			}
		}

		while (true) {
			try {
				System.out.print("Ingrese el monto pagado: ");
				reserva.setMonto_pagado(scanner.nextDouble());
				break;
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para monto pagado.");
			} finally {
				scanner.nextLine();
			}
		}

		reserva.setServiciosAdicionales(ingresarServiciosAdicionales());

		reserva.setPago_adelantado(ingresarPagoAdelantado());

		reserva.setCancelado(false);

		reserva.setEstado(true);

		reservaDao.realizarReserva(reserva);

	}

	public static List<ServiciosAdicionales> ingresarServiciosAdicionales() {
		List<ServiciosAdicionales> serviciosSeleccionados = new ArrayList<>();
		while (true) {
			System.out.println("¿Desea seleccionar servicios adicionales? (S/N): ");
			switch (scanner.nextLine()) {
			case "S":
				Long id_ser;
				while (true) {
					try {
						System.out.print("Seleccione un servicio por su ID ");
						id_ser = scanner.nextLong();
						break;
					} catch (InputMismatchException e) {
						System.out.println("Error: Debe ingresar un número entero para el ID.");
					} finally {
						scanner.nextLine();
					}
				}

				ServiciosAdicionales serviciosDisponibles = servAddDao.consultarServicioAdicionalId(id_ser);

				if (serviciosDisponibles != null) {
					if (!serviciosSeleccionados.contains(serviciosDisponibles)) {
						serviciosSeleccionados.add(serviciosDisponibles);
					} else {
						System.out.println("El servicio ya fue seleccionado.");
					}
					break;
				} else {
					System.out.println("El servicio no existe.");
				}

				break;

			case "N":
				System.out.println("No se seleccionaron servicios adicionales.");
				return serviciosSeleccionados;
			default:
				System.out.println("Opción no válida. No se seleccionarán servicios adicionales.");
			}
		}

	}

	public static double ingresarPagoAdelantado() {
		while (true) {
			System.out.print("¿Desea ingresar un pago adelantado? (S/N): ");
			switch (scanner.nextLine()) {
			case "S":
				while (true) {
					try {
						System.out.print("Ingrese el pago adelantado: ");
						double pago = scanner.nextDouble();
						return pago;
					} catch (InputMismatchException e) {
						System.out.println("Error: Debe ingresar un número entero para pago adelantado.");
					} finally {
						scanner.nextLine();
					}
				}

			case "N":
				System.out.println("No se ingresó un pago adelantado. Se establecerá en 0.0.");
				break;
			default:
				System.out.println("Opción no válida. ");
			}
		}

	}

	public static Cliente buscarUnCliente() {
		System.out.println("Función de consulta de clientes");
		while (true) {
			try {
				System.out.print("Ingrese el ID del cliente a buscar: ");
				Long clienteId = scanner.nextLong();
				return clienteDao.obtenerClienteId(clienteId);
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el ID del cliente.");
			} finally {
				scanner.nextLine();
			}
		}

	}

	public static Reserva buscarUnaReserva() {
		System.out.println("Función de consultar una reserva");
		while (true) {
			try {
				System.out.print("Ingrese el ID de la reserva:");
				Long consulta = scanner.nextLong();
				return reservaDao.consultarReservaId(consulta);
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero para el ID de la reserva.");
			} finally {
				scanner.nextLine();
			}
		}

	}

	public static void consultarClientes() {
		System.out.println("Función de consultar clientes");
		List<Cliente> clientes = clienteDao.consultarClientes();
		for (Cliente cliente : clientes) {
			cliente.mostrarDatos();
		}
	}

	public static void consultarSalones() {
		System.out.println("Función de consultar salones");
		List<Salon> salones = salonDao.consultarSalones();
		for (Salon salon : salones) {
			salon.mostrarDatos();
		}
	}

	public static void consultarServiciosAdicionales() {
		System.out.println("Función de consultar servicios adicionales");
		List<ServiciosAdicionales> adicionales = servAddDao.consultarServicioAdicional();
		for (ServiciosAdicionales serviciosAdicionales : adicionales) {
			serviciosAdicionales.mostrarDatos();
		}
	}

	public static void consultarTodasLasReservas() {
		System.out.println("Función de consultar todas las reservas");
		List<Reserva> reservas = reservaDao.consultarReservas();
		for (Reserva reserva : reservas) {
			reserva.mostrarDatos();
		}
	}

	public static void precargaSalon() {
		salonDao.guardarSalon(new Salon("Salón Cosmos", 60, false, 60000.00));
		salonDao.guardarSalon(new Salon("Salón Esmeralda", 20, false, 40000.00));
		salonDao.guardarSalon(new Salon("Salón Galaxy", 100, true, 60000.00));
	}

	public static void precargaServiciosAdicionales() {
		servAddDao.guardarServicioAdicional(new ServiciosAdicionales("Cámara 360", 1500.00, true));
		servAddDao.guardarServicioAdicional(new ServiciosAdicionales("Cabina de fotos", 2000.00, true));
		servAddDao.guardarServicioAdicional(new ServiciosAdicionales("Filmación", 3000.00, true));
		servAddDao.guardarServicioAdicional(new ServiciosAdicionales("Pintacaritas", 500.00, true));
	}
}
