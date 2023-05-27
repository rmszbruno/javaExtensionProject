/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sgv.controller;

import br.com.sgv.model.Cliente;
import br.com.sgv.repository.ClienteRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author rmszbruno
 */

@Controller
public class ClienteController {
    
    @Autowired
    private ClienteRepository ClienteRepository;
    
    @GetMapping("/cliente")
    public String listar(Model model) {
        model.addAttribute("listaCliente", ClienteRepository.findAll());
        return "gerenciar_cliente";
    }

    @GetMapping("/cliente/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "editar_cliente";
    }
    
    @GetMapping("/cliente/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Cliente> cliente = ClienteRepository.findById(id);
        model.addAttribute("cliente", cliente.get());
        return "editar_cliente";
    }
    
    @PostMapping("/cliente")
    public String salvar(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_cliente";
        }
        ClienteRepository.save(cliente);
        return "redirect:/cliente";
    }
    
    @DeleteMapping("/cliente/{id}")
    public String excluir(@PathVariable("id") long id) {
        ClienteRepository.deleteById(id);
        return "redirect:/cliente";
    }
}


