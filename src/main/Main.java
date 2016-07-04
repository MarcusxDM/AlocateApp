package main;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Activity;
import classes.Locate;
import classes.Resource;
import classes.User;

import java.text.*;
/**
 * @author Marcus Vinicius G. Pestana
 *
 */

public class Main {
	private static ArrayList<User> userList = new ArrayList<>();
	private static ArrayList<Locate> locateList = new ArrayList<>();
	public static User mainUser;
	public static Date dateNow = new Date();
	public static String time = "12/11/2016 12:12";
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	//public static String formated = dateFormat.format(dateNow);
	
	
	
	public static void main(String[] args) throws Exception {
		Date d = dateFormat.parse(time);
		Scanner integerScan = new Scanner(System.in);
		while(mainUser == null){
			System.out.println("##############  Bem vindo ao Allocate  ##############");
			System.out.println("\n              1 - Login");
			System.out.println("              2 - Cadastro");
			System.out.println("              3 - Sair");
			System.out.println("\n\n  Data: "+dateNow);
	
			int entry = integerScan.nextInt();
	
			switch (entry) {
				case 1:
					loginScreen();
					while(mainUser != null){
						System.out.println("#########  Bem vindo, "+mainUser.getName()+"! O que deseja fazer?   ########\n");
						System.out.println("1 - Nova Atividade");
						System.out.println("2 - Permitir locacao");
						System.out.println("3 - Buscar Usuario");
						System.out.println("4 - Buscar Recurso");
						System.out.println("5 - Informacao do Sistema");
						System.out.println("6 - Logout");
						
						entry = integerScan.nextInt();
						
						switch (entry) {
						case 1:
							allocateResource();
							break;
						case 2:
							permitAllocate();
							break;
						case 3:
							userSearch();
							break;
						case 4:
							Locate mainAllocate = null;
							mainAllocate = resourceSearch(mainAllocate);
							break;
						case 5:
							systemInfo();
							break;
						case 6:
							mainUser = null;
						}
					}
					break;
				case 2:
					signupScreen();
					break;
				case 3:
					System.exit(0);
					break;
			}
		}
	}
	
	public static void systemInfo() {
		int allocated = 0, allocating = 0, completed = 0, inProgress = 0, classNormal = 0, lab = 0, presentation = 0; 
		System.out.println("\n\n#################  Status do Systema  ##################");
		System.out.println("\nNumero de Usuarios: "+userList.size());
		System.out.println("\nNumero de Locacoes: "+locateList.size());
		for (Locate l : locateList){
			if(l.getState()==1){
				allocated++;
			}
			if(l.getState()==2){
				allocating++;
			}
			if(l.getState()==4){
				completed++;
			}
			if(l.getState()==5){
				inProgress++;
			}
		}
		System.out.println("\nLocacoes Alocadas: "+allocated);
		System.out.println("\nLocacoes sendo alocadas "+allocating);
		System.out.println("\nLocacoes Completas "+completed);
		System.out.println("\nLocacoes Em Progresso "+inProgress);
		
		for (Locate l : locateList){
			if(l.getActivity().getType()==1){
				classNormal++;
			}
			if(l.getActivity().getType()==2){
				presentation++;
			}
			if(l.getActivity().getType()==3){
				lab++;
			}
		}
		System.out.println("\nAulas Normais: "+classNormal);
		System.out.println("\nApresentacoes "+presentation);
		System.out.println("\nLaboratorios "+lab);	
		System.out.println("\n\n##################################################\n\n");
	}

	public static void permitAllocate() {
		Locate mainAllocate = null;
		Scanner integerScan = new Scanner(System.in);
		int entry = 0;
		mainAllocate = resourceSearch(mainAllocate);
		if(mainAllocate.getState() == 1){
			System.out.println("\n############# RECURSO JA ESTA ALOCADO ##############\n");
			if(mainUser == mainAllocate.getResponsable()){	
				System.out.println("\nDeseja confirmar a locacao? 1 - Sim, 2 - Nao\n");
				entry = integerScan.nextInt();
				switch(entry){
				case 1:
					mainAllocate.setState(5);
					System.out.println("\n############# RECURSO EM ANDAMENTO ##############\n");
					break;
				case 2:
					break;
				}
			}
			else{
				System.out.println("Voce nao é o responsavel pela Locacao\n\n");
			}
		}
		else if(mainAllocate.getState() == 4){
			System.out.println("\n\n############# RECURSO JA CONCLUIDO ##############\n\n");
		}
		else if(mainAllocate.getState() == 2){
			System.out.println("\nDeseja permitir a locacao? 1 - Sim, 2 - Nao\n");
			entry = integerScan.nextInt();
			if(mainUser.getPermissionLevel() == 4){
				switch(entry){
				case 1:
					mainAllocate.setState(1);
					mainAllocate.getResponsable().setAllocating(false);
					System.out.println("\n############# RECURSO ALOCADO ##############\n");
					break;
				case 2:
					break;
				}
			}
			else{
				System.out.println("\nApenas administradores podem alocar recursos\n");
			}
		}
		else if(mainAllocate.getState() == 5){
			System.out.println("\n\n############# RECURSO JA ESTA EM PROGRESSO ##############\n\n");
			System.out.println("\nDeseja concluir a locacao? 1 - Sim, 2 - Nao\n");
			if(mainUser.getPermissionLevel() == 4){
				entry = integerScan.nextInt();
				switch(entry){
				case 1:
					mainAllocate.setState(4);
					System.out.println("\n############# RECURSO CONCLUIDO ##############\n\n");
					break;
				case 2:
					break;
				}
			}
			else{
				System.out.println("\nApenas administradores podem concluir um Recurso\n\n");
			}
		}
		
	}

	public static Locate resourceSearch(Locate mainAllocation) {
		/**
		 * Finds a resource in a List
		 */
		String name;
		int resourcetp = 0, found = 0, activitytp = 0, state = 3;
		Scanner stringScan = new Scanner(System.in);
		System.out.println("############## Pesquisa por Nome de Atividade ###############");
		
		System.out.println("\nNome:");
		name = stringScan.nextLine();
		for(Locate l : locateList){
			if(l.getActivity().getName().equals(name)){
				found = 1;
				System.out.println("########################\nInfo:\n\nNome: "+l.getActivity().getName());
				state = l.getState();
				switch (state) {
				case 1:
					System.out.println("\nAlocado");
					break;
				case 2:
					System.out.println("\nEm processo de locacao");
					break;
				case 3:
					System.out.println("\nDisponivel");
					break;
				case 4:
					System.out.println("\nFinalizado");
					break;
				case 5:
					System.out.println("\nEm progresso");
					break;
				}	
				activitytp = l.getActivity().getType();
				resourcetp = l.getResource().getType();
				
				switch (resourcetp) {
				case 1:
					System.out.println("\nTipo de Recurso: Sala de Aula");
					break;
				case 2:
					System.out.println("\nTipo de Recurso: Auditorio");
					break;
				case 3:
					System.out.println("\nTipo de Recurso: Laboratorio");
					break;
				case 4:
					System.out.println("\nTipo de Recurso: Projetor");
					break;
				}				
				
				switch (activitytp) {
					case 1:
						System.out.println("\nTipo de Atividade: Aula Normal");
						break;
					case 2:
						System.out.println("\nTipo de Atividade: Apresentacao");
						break;
					case 3:
						System.out.println("\nTipo de Atividade: Laboratorio");
						break;
				}
				System.out.println("\nResponsavel: "+l.getResponsable().getName());
				System.out.println("\nParticipantes:\n\n");
				for (User participant : l.getActivity().getParticipant()){
					System.out.println("    "+participant.getName()+"\n");
				}
				System.out.println("\nInicio: "+l.getBeginDate());
				System.out.println("\nFinal: "+l.getEndDate());
				mainAllocation = l;
				System.out.println("\n########################\n\n");
			}
	    }
		if(found == 0)
			System.out.println("\nRecurso não encontrado\n");
		return mainAllocation;
	}

	public static void signupScreen() {
		/**
		 * 
		 * Registers an User.
		 * 
		 */
		String id, password, email, name;
		int permissionLevel;
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#################  Cadastro  ##################\n");

		System.out.println("ID:\n");
		id = stringScan.nextLine();

		System.out.println("\nSenha:\n");
		password = stringScan.nextLine();

		System.out.println("\nNome:\n");
		name = stringScan.nextLine();

		System.out.println("\nEmail:\n");
		email = stringScan.nextLine();

		System.out.println("\nPermissão:\n\n     1 - Estudante\n     2 - Professor\n     3 - Pesquisador\n     4 - Administrador\n\n");
		permissionLevel = integerScan.nextInt();

		User newUser = new User(id, password, name, email, permissionLevel);
		userList.add(newUser);
		System.out.println("############  Cadastro Completo!  #############\n");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
	}
	
	public static void loginScreen(){
		/**
		 * Logs in a User.
		 */
		String id, password;
		boolean userFound = false;
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		while(mainUser == null){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("#################  Login  ##################\n");
			
			System.out.println("ID:\n");
			id = stringScan.nextLine();
	
			System.out.println("\nSenha:\n");
			password = stringScan.nextLine();
			
			for(User u : userList){
				if ((u.getId().equals(id)) && (u.getPassword().equals(password))){
					userFound = true;
					System.out.println("##############  Login Efetuado!  ##############\n");
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
					mainUser = u;
				}
			}
			System.out.println("\n\n");
			if(!userFound){
				System.out.println("Usuario nao encontrado, tentar novamente?\n1 - Sim\n2 - nao\n");
				int entry = integerScan.nextInt();
				if(entry == 2){
					break;
				}
			}
		}
	}
	
	public static boolean dateApt(int resourceType, Date beginDate, Date endDate) {
	    for(Locate l : locateList){
	    	if(l.getResource().getType()== resourceType){
	    		if((beginDate.compareTo(l.getBeginDate()) < 0) && (endDate.compareTo(l.getEndDate()) < 0))
	    			return true;
	    		else if((beginDate.compareTo(l.getBeginDate()) > 0) && (endDate.compareTo(l.getEndDate()) > 0))
	    			return true;
	    		else if((beginDate.compareTo(l.getBeginDate()) == 0) && (endDate.compareTo(l.getEndDate()) == 0))
	    			return false;
	    		else if((beginDate.compareTo(l.getBeginDate()) <= 0 ) && (endDate.compareTo(l.getEndDate()) >= 0))
	    			return false;
	    		else if((beginDate.compareTo(l.getBeginDate()) >= 0 ) && (endDate.compareTo(l.getEndDate()) <= 0))
	    			return false;
	    		else if((beginDate.compareTo(l.getBeginDate()) <= 0 ) && (endDate.compareTo(l.getEndDate()) >= 0))
	    			return false;
	    	}
	    }
		return true;
	}
	
	public static void allocateResource() throws Exception{
		int resourceType = 0, activityType = 0, nParticipants = 0;
		String beginDate, endDate;
		Date beginDateReal = null, endDateReal = null;
		boolean resourceFound = false, responsableFound = false;
		User responsable=null;
		String name, description, supportMaterial, participant, nameResponsable;
		ArrayList<User> participants = new ArrayList<>();
		Scanner integerScan = new Scanner(System.in);
		Scanner stringScan = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#################  Nova Atividade  ##################\n");
		
		System.out.println("Tipo da Atividade:\n   1 - Aula\n   2 - Apresentacao\n   3 - Laboratorio\n\n");
		activityType = integerScan.nextInt();

		System.out.println("\nNome:\n");
		name = stringScan.nextLine();

		System.out.println("\nDescricao:\n");
		description = stringScan.nextLine();

		System.out.println("\nMaterial de Suporte:\n");
		supportMaterial = stringScan.nextLine();
		
		while(!resourceFound){
			System.out.println("\nTipo de Recurso:\n   1 - Sala de Aula\n   2 - Auditorio\n   3 - Laboratorio\n   4 - Projetor\n\n");
			resourceType = integerScan.nextInt();
			
			System.out.println("\nQue data e horario comeca?(dd/MM/yyyy HH:mm)\n");
			beginDate = stringScan.nextLine();
			beginDateReal = dateFormat.parse(beginDate);
			
			System.out.println("\nQue data e horario termina?(dd/MM/yyyy HH:mm)\n");
			endDate = stringScan.nextLine();
			endDateReal = dateFormat.parse(endDate);
			
			if(dateApt(resourceType, beginDateReal, endDateReal)==true){
				resourceFound = true;
			}
			else{
				System.out.println("\nHorario nao esta livre, escolha outro\n\n");
			}
			
		}
		
		
		while(responsable==null){
			System.out.println("\nUsuario Responsavel:\n");
			nameResponsable = stringScan.nextLine();
			
			for(User u : userList){
				if((u.getName().equals(nameResponsable)) && u.isAllocating()==false && u.getPermissionLevel()>=2){
					responsable = u;
					responsableFound = true;
				}	
			}
			if (responsableFound == false){
				System.out.println("\nUsuario não encontrado ou não apto\n");
			}
		}
		
		System.out.println("\nQuantos participantes?\n");
		nParticipants = integerScan.nextInt();
		
		for(int count = 1; count < nParticipants+1;){
			System.out.println("\nParticipante numero "+count+":\n");
			participant = stringScan.nextLine();
			boolean participantFound = false;

			while(!participantFound){
				for(User u : userList){
					if(u.getName().equals(participant)){
						participants.add(u);
						count++;
						participantFound = true;
					}	
			 	}
				if(participantFound == false)	
					System.out.println("\nUsuario não encontrado\n");
					break;
			}
		}
		responsable.setAllocating(true);
		Activity newActivity = new Activity(name, activityType, participants, description, supportMaterial);
		Resource newResource = new Resource(resourceType, 2);
		Locate newLocate = new Locate(newResource, beginDateReal, endDateReal, 2, responsable, newActivity);
		locateList.add(newLocate);
		System.out.println("\n############### ATIVIDADE ALOCADA! #################\n\n\n\n\n\n\n");
	}
	
	public static void userSearch(){
		String name;
		int resourcetp = 0, found = 0;
		Scanner stringScan = new Scanner(System.in);
		System.out.println("############## Pesquisa por Nome de Usuario ###############");
		
		System.out.println("\nNome:");
		name = stringScan.nextLine();
		for(User u : userList){
			if(u.getName().equals(name)){
				found = 1;
				System.out.println("########################\nInfo:\n\nNome: "+u.getName());
				System.out.println("\nEmail: "+u.getEmail());
				System.out.println("\nRecursos:\n\n########################");
				for(Locate l : locateList){
					if(l.getResponsable() == u){
						resourcetp = l.getResource().getType();
						switch (resourcetp) {
						case 1:
							System.out.println("Sala de Aula");
							break;
						case 2:
							System.out.println("Auditorio");
							break;
						case 3:
							System.out.println("Laboratorio");
							break;
						case 4:
							System.out.println("Projetor");
							break;
						}
						System.out.println(l.getActivity().getName());
						System.out.println("\n'"+l.getActivity().getDescription()+"'\n\n########################\n");
					}
				}
			}
	 	}
		if(found == 0)
			System.out.println("\nUsuario não encontrado\n");
	}
}


