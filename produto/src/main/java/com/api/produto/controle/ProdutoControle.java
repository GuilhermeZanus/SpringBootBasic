package com.api.produto.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.modelo.ProdutoModelo;
import com.api.produto.modelo.RespostaModelo;
import com.api.produto.repositorio.ProdutoRepositorio;

//import br.com.cod3r.exerciciossb.models.entities.Produto;

@RestController
@RequestMapping("/api")
public class ProdutoControle {
	
	@Autowired
	private ProdutoRepositorio acoes;

	@RequestMapping(value="", method = RequestMethod.GET)
	public @ResponseBody String inicio() {
		return "Bem vindo à API de produtos!";
	}
	
	@RequestMapping(value="/produtos", method = RequestMethod.GET)
	public @ResponseBody List<ProdutoModelo> listar() {
		return acoes.findAll();
	}
	
	@RequestMapping(value="/produtos", method = RequestMethod.POST)
	public @ResponseBody ProdutoModelo cadastrar(@RequestBody ProdutoModelo produto) {
		acoes.save(produto);
		return produto;
	}
	
	
	@RequestMapping(value="/produtos/{codigo}", method = RequestMethod.GET)
	public @ResponseBody ProdutoModelo filtrar(@PathVariable int codigo) {
		return acoes.findByCodigo(codigo);
	}
	
	@RequestMapping(value="/produtos", method = RequestMethod.PUT)
	public @ResponseBody ProdutoModelo alterar(@RequestBody ProdutoModelo produto) {
		acoes.save(produto);
		return produto;
	}
	
	@RequestMapping(value="/produtos/{codigo}", method = RequestMethod.DELETE)
	public @ResponseBody RespostaModelo apagar(@PathVariable int codigo) {
	//	acoes.deleteById(codigo);
		
		RespostaModelo resposta = new RespostaModelo();
		
		try {
			ProdutoModelo produto = filtrar(codigo);
			acoes.delete(produto);
			resposta.setMensagem("Produto removido com sucesso");
		}catch(Exception erro) {
			resposta.setMensagem("Falha ao remover: '"+erro.getMessage()+"'.");
		}
		
		return resposta;		
	}
	
	
}
