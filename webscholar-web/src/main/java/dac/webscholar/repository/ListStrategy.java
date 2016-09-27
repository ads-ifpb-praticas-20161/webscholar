/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import java.util.List;

/**
 *
 * @author vmvini
 */
public interface ListStrategy<T> {
    List<T> getResultList();

    T getSingleResult();
}
