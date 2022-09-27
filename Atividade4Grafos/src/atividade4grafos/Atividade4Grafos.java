package atividade4grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class Atividade4Grafos {



	public static LinkedList<Grafo> grafos = new LinkedList<>();
	public static LinkedList<Grafo> fila = new LinkedList<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("ATIVIDADE 1: ");
		
		File doc = new File("C:\\Users\\Bruno\\Desktop\\Atividade4Grafos\\src\\atividade4grafos\\pequenoG.txt");
		Scanner obj = new Scanner(doc);

		alocar(grafos, obj);
		int nVerticeIsolada = 0;
		int nVerticeExtremiadade = 0;

		for (int n = 0; n < grafos.size(); n++) {
			System.out.println("Vertice: " + grafos.get(n).getValor() + " Grau: " + grafos.get(n).getGrau());
			if (grafos.get(n).getGrau() == 0) {
				nVerticeIsolada++;
			}
			if (grafos.get(n).getGrau() == 1) {
				nVerticeExtremiadade++;
			}
		}

		System.out.println("QTD isolado: " + nVerticeIsolada);
		System.out.println("QTD extremidade: " + nVerticeExtremiadade);
		System.out.println("\nGRAFO :");
		for (int n = 0; n < grafos.size(); n++) {
			System.out.println(grafos.get(n));
		}
		System.out.println("\nDFS + IMPRESSÃO :");

		
		int s = 0;
		System.out.println("\nDFS");
		DFS(grafos, s);

		
		int v = 4;
		System.out.println("\nCAMINHO MENOR ENTRE " + s + " E " + v + ":");
		impressao(grafos, s, v);
	}


	public static void alocar(LinkedList<Grafo> grafos, Scanner obj) {
		while (obj.hasNextLine()) {
			String[] textoSeparado = obj.nextLine().split(" ");
			if (textoSeparado.length == 1) {
				System.out.println("Ordem: " + textoSeparado[0]);
				System.out.println("Tamanaho: " + obj.nextLine());

			} else {
				Grafo g = new Grafo(Integer.parseInt(textoSeparado[0]));
				Grafo g2 = new Grafo(Integer.parseInt(textoSeparado[1]));
				int[] vizinhos = new int[0];
				int[] vizinhosg2 = new int[0];

				if (!grafos.contains(g)) {
					grafos.add(g);
				}
				if (!grafos.contains(g2)) {
					grafos.add(g2);
				}

				if (grafos.get(grafos.indexOf(g)).getVizinhos() != null) {
					vizinhos = grafos.get(grafos.indexOf(g)).getVizinhos();
				}

				if (grafos.get(grafos.indexOf(g2)).getVizinhos() != null) {
					vizinhosg2 = grafos.get(grafos.indexOf(g2)).getVizinhos();
				}

				int[] vizinhosAux = new int[vizinhos.length + 1];
				int n;
				for (n = 0; n < vizinhos.length; n++) {
					vizinhosAux[n] = vizinhos[n];
				}

				int[] vizinhosAux2 = new int[vizinhosg2.length + 1];
				for (n = 0; n < vizinhosg2.length; n++) {
					vizinhosAux2[n] = vizinhosg2[n];
				}

				vizinhosAux[(vizinhosAux.length - 1)] = Integer.parseInt(textoSeparado[1]);
				vizinhosAux2[(vizinhosAux2.length - 1)] = Integer.parseInt(textoSeparado[0]);

				grafos.get(grafos.indexOf(g)).setVizinhos(vizinhosAux);
				grafos.get(grafos.indexOf(g)).setGrau(vizinhosAux.length);
				grafos.get(grafos.indexOf(g2)).setVizinhos(vizinhosAux2);
				grafos.get(grafos.indexOf(g2)).setGrau(vizinhosAux2.length);

			}

		}

	}


	public static void DFS(LinkedList<Grafo> grafos, int s) {
		int tempo = 1;
		for (int n = 0; n < grafos.size(); n++) {
			grafos.get(n).setCor("branco");
			grafos.get(n).setI(1);
			grafos.get(n).setF(-1);
			grafos.get(n).setAnte(-1);
		}
		DFSvisit(grafos, s, tempo);

	}

	public static void DFSvisit(LinkedList<Grafo> grafos, int s, int tempo) {
		Grafo aux = new Grafo(s);
		Grafo grafo = grafos.get(grafos.indexOf(aux));
		grafo.setCor("cinza");
		grafo.setI(tempo++);
		for (int i = 0; i < grafo.getVizinhos().length; i++) {
			Grafo vizinho = new Grafo(grafo.getVizinhos()[i]);
			vizinho = grafos.get(grafos.indexOf(vizinho));
			if (vizinho.getCor().compareTo("branco") == 0) {
				vizinho.setCor("cinza");
				vizinho.setAnte(grafo.getValor());
				DFSvisit(grafos, vizinho.getValor(), tempo);
			}

			grafo.setCor("preto");
			grafo.setF(tempo++);
		}
	}


	public static void impressao(LinkedList<Grafo> grafos, int s, int v) {
		Grafo aux = new Grafo(v);
		Grafo aux2, aux3, aux4;

		aux2 = grafos.get(grafos.indexOf(aux));

		aux3 = new Grafo(s);
		aux4 = grafos.get(grafos.indexOf(aux3));
		if (v == s) {
			System.out.println(aux4.getValor());
		} else {
			if (aux2.getAnte() == -1) {
				System.out.println("Não tem caminho");
			} else {
				impressao(grafos, s, aux2.getAnte());
				System.out.println(aux2.getValor());
			}

		}
	}
}
