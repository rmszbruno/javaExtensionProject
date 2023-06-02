/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.sgv.repository;

import br.com.sgv.model.Pedido;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author Bruno
 */
public interface PedidoRepository extends CrudRepository<Pedido,Long>{
    
}
