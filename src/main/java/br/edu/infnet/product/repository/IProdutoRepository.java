package br.edu.infnet.product.repository;

import br.edu.infnet.product.Model.Produto;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value="SELECT * FROM produto WHERE codProduct = :codProduct", nativeQuery=true)
    public Produto getByCodProduct(@Param("codProduct") int codProduct);
}

