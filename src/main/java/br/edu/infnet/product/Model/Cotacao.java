package br.edu.infnet.product.Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cotacao")
@NamedQueries({
    @NamedQuery(name = "Quotation.findAll", query = "SELECT q FROM  q"),
    @NamedQuery(name = "Quotation.findByQuotationId", query = "SELECT q FROM  q WHERE q.quotationId = :quotationId"),
    @NamedQuery(name = "Quotation.findByproductPrice", query = "SELECT q FROM  q WHERE q.productPrice= :productPrice"),
    @NamedQuery(name = "Quotation.findByProduct", query = "SELECT q FROM  q WHERE q.produto = :product"),
    @NamedQuery(name = "Address.findByLastUpdate", query = "SELECT q FROM  q WHERE q.lastUpdate = :lastUpdate")})
public class Cotacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cotacao_id")
    private Short cotacaoId;
    @Column(name = "preco_cotacao")
    private String preco;
    @Basic(optional = false)
    @Column(name = "produto")
    private String produto;
    @Basic(optional = false)
    @Column(unique=true)
    private int codigo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotacaoId")
    private List<Produto> productList;

    public Cotacao() {
    }

    public Cotacao(Short cotacaoId) {
        this.cotacaoId = cotacaoId;
    }

    public Cotacao(Short cotacaoId, String preco, String produto) {
        this.cotacaoId = cotacaoId;
        this.preco = preco;
        this.produto = produto;
    }

    public Short getCotacaoId() {
        return cotacaoId;
    }

    public void setCotacaoId(Short quotationId) {
        this.cotacaoId = quotationId;
    }

    public void setPreco(String productPrice) {
        this.preco = productPrice;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String product) {
        this.produto = product;
    }

    public void setProductList(List<Produto> customerList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cotacao{id=").append(cotacaoId);
        sb.append(", preco=").append(preco);
        sb.append(", produto=").append(produto);
        sb.append(", code=").append(codigo);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotacaoId != null ? cotacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotacao)) {
            return false;
        }
        Cotacao other = (Cotacao) object;
        if ((this.cotacaoId == null && other.cotacaoId != null) || (this.cotacaoId != null && !this.cotacaoId.equals(other.cotacaoId))) {
            return false;
        }
        return true;
    }

}


