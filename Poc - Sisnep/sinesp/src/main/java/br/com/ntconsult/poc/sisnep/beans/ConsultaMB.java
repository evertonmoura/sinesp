package br.com.ntconsult.poc.sisnep.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.ntconsult.poc.sisnep.cliente.Result;
import br.com.ntconsult.poc.sisnep.cliente.SinespClient;

@ManagedBean
@RequestScoped
public class ConsultaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private String placa;
	private String retorno;
	private boolean erro;
	private List<Result> retornoConsulta;

	@Inject
	private SinespClient sinespClient;

	@PostConstruct
	public void init() {
		this.placa = "";
		this.retorno = "";
		this.erro = false;
		this.retornoConsulta = null;
	}

	public void consultar() {
		this.retornoConsulta = new ArrayList<>();
		try {
			if (null == placa || placa.isEmpty()) {
				return;
			}
			if (null != placa && placa.contains("-")) {
				retorno = "Formato de placa inaváldo! Utilize o formato \"AAA9999\".";
				erro = true;
				this.retornoConsulta = null;
				return;
			}
			Result result = new Result();
			result = sinespClient.search(placa);
			if (null == result || (null != result && null == result.getPlate())) {
				retorno = "Dados não Encontrado";
				erro = true;
				this.retornoConsulta = null;
			} else
				retornoConsulta.add(result);
		} catch (RuntimeException e) {
			retorno = "Formato de placa inaváldo! Utilize o formato \"AAA9999\".";
			erro = true;
			this.retornoConsulta = null;

		}
	}

	public void limpar() {
		placa = "";
		retorno = "";
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public List<Result> getRetornoConsulta() {
		return retornoConsulta;
	}

	public void setRetornoConsulta(List<Result> retornoConsulta) {
		this.retornoConsulta = retornoConsulta;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

}