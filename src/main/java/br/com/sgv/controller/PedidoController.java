/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.controller;

import br.com.sgv.model.Cliente;
import br.com.sgv.model.Pedido;
import br.com.sgv.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.sgv.repository.PedidoRepository;
import br.com.sgv.repository.ProdutoRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Bruno
 */

@Controller
public class PedidoController {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/pedido")
    public String listar(Model model) {
        model.addAttribute("listaPedido", pedidoRepository.findAll());
        return "gerenciar_pedido";
    }
    
    @GetMapping("/pedido/novo")
    public String novo(Model model) {
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("pedido", new Pedido());
        return "editar_pedido";
    }
    
    @GetMapping("/pedido/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        model.addAttribute("pedido", pedido.get());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "editar_pedido";
    }
    
    @PostMapping("/pedido")
    public String salvar(@Valid Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pedido";
        }
        pedido.getProduto().setQuantidade(pedido.getProduto().getQuantidade()-1);
        pedidoRepository.save(pedido);
        return "redirect:/pedido";
    }
    
    @DeleteMapping("/pedido/{id}")
    public String excluir(@PathVariable("id") long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedido";
    }
}
