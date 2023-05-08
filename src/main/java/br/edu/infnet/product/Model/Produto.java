package br.edu.infnet.product.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Produto  p"),
        @NamedQuery(name = "Product.findByCustomerId", query = "SELECT p FROM Produto  p WHERE p.productId = :productId"),
        @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Produto  p WHERE p.productName = :productName"),
        @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Produto  p WHERE p.productQuantity = :productQuantity"),
        @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Produto  p WHERE  p.code = :code"),
        @NamedQuery(name = "Product.findByproductImage", query = "SELECT p FROM Produto  p WHERE p.productImage = :productImage"),
        @NamedQuery(name = "Product.findByLastUpdate", query = "SELECT p FROM Produto  p WHERE p.lastUpdate = :lastUpdate")})
public class Produto implements Serializable {

    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "produto_id")
    private Short produtoId;
    @Basic(optional = false)
    @Column(name = "produto_name")
    private String produtoNome;
    @Basic(optional = false)
    @Column(name = "produto_quantidade")
    private int produtoQuantidade;
    @Basic(optional = false)
    @Column(name = "produto_imagem")
    private String produtoImagem;
    @Column(unique=true)
    private int codigo;
    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cotacao> cotacaos = new HashSet<>();

    private int cotacaoId;

    public Produto() {
    }

    private Produto(Short produtoId) {
        this.produtoId = produtoId;
    }

    public Produto(short produtoId, String produtoImagem, String produtoNome, int produtoQuantidade, int codigo) {
        this.produtoId = produtoId;
        this.produtoImagem = produtoImagem;
        this.produtoNome = produtoNome;
        this.produtoQuantidade = produtoQuantidade;
        this.codigo = codigo;
    }

    public Produto(String produtoNome, int produtoQuantidade, String produtoImagem, int codigo) {
        this.produtoNome = produtoNome;
        this.produtoQuantidade = produtoQuantidade;
        this.produtoImagem = produtoImagem;
        this.codigo = codigo;
    }

    public Produto(String produtoNome, int produtoQuantidade, int codigo) {
    }

    public Short getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Short productId) {
        this.produtoId = productId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String productName) {
        this.produtoNome = productName;
    }

    public String getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(String productImage) {
        this.produtoImagem = productImage;
    }

    public int getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public void setProdutoQuantidade(int productQuantity) {
        this.produtoQuantidade = productQuantity;
    }

    public int getCotacaoId() {
        return cotacaoId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCode(int code) {
        this.codigo = codigo;
    }

    public void setCotacaoId(int quotationId) {
        this.cotacaoId = quotationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Produto{id=").append(produtoId);
        sb.append(", img=").append(produtoImagem);
        sb.append(", nome=").append(produtoNome);
        sb.append(", quantidade=").append(produtoQuantidade);
        sb.append(", code=").append(codigo);
        sb.append(", cotacoes=").append(cotacaos);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtoId != null ? produtoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.produtoId == null && other.produtoId != null) || (this.produtoId != null && !this.produtoId.equals(other.produtoId))) {
            return false;
        }
        return true;
    }

    }



