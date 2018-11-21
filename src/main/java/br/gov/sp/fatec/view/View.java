package br.gov.sp.fatec.view;

/**
 * Esta classe define as diferentes visualizacoes disponiveis para serializacoes
 */
public class View {
	
	/**
	 * Visualizacao principal com os principais atributos
	 */
	public static class UsuarioResumo {
	}
	
	/**
	 * Visualizacao com todos os atributos
	 * Inclui tudos os atributos marcados com Main
	 */
	public static class UsuarioCompleto extends UsuarioResumo {
	}
	
	/**
	 * Visualizacao alternativa
	 */
	public static class UsuarioResumoAlternativo {
	}
	
	public static class Anotacao {
	}
	
	public static class Autorizacao {
	}

}
