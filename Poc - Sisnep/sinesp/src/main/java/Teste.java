
public class Teste {

	public String getString() {
		StringBuilder contatenada = new StringBuilder();
		contatenada.append("SELECT (ABREVIACAO_MODULO ").append(" ||'").append("-").append("'||").append(
				" DESCRICAO_MODULO_SISTEMA) AS SIGLA_E_DESC FROM SEFAZ_SEG.TA_MODULO_SISTEMA WHERE REGISTRO_EXCLUIDO LIKE 'N' ORDER BY ABREVIACAO_MODULO ASC");
		return contatenada.toString();
	}
}
