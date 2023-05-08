package br.edu.infnet.product.controller;

import br.edu.infnet.product.Model.Produto;
import br.edu.infnet.product.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public List<Produto> mostrarProdutos() {
        return produtoService.mostrarTudo();
    }

    @RequestMapping(value = "/registrarProduto",method = RequestMethod.POST)
    public void registro(@RequestPart MultipartFile productImage, @RequestPart String productName, @RequestPart  int productQuantity, @RequestPart int codProduct) {
        Produto produto;

        produto = produtoService.buscarPorCodigo(codProduct);
        if (produto == null) {
            produtoService.register(new Produto(productName, productQuantity, codProduct));
        }
    }

    @RequestMapping(value = "/alterarProduto/{id}", method = RequestMethod.PUT)
    public String alterarProduto(@PathVariable Integer productId, @RequestPart MultipartFile productImage, @RequestParam String productName, @RequestParam int productQuantity
    ) throws IOException {
        Produto produto;
        String imgURL;

        try {
            produto = produtoService.buscarPorId(productId);
        } catch (Exception e) {
            return "Produto não encontrado";
        }
        imgURL = produtoService.prepararImgURL(productImage);
        produto.setProdutoImagem(imgURL);
        produto.setProdutoNome(productName);
        produto.setProdutoQuantidade(productQuantity);

        try {
            produtoService.save(produto);
        } catch (Exception error) {
            return " Erro inesperado ao editar um produto: "+error;
        }
        return "";
    }

    @RequestMapping(path = "/download")
    public void baixarCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"products.csv\"");
        produtoService.Csv(servletResponse.getWriter());
    }

    @RequestMapping(value = "/deletarProduto/{id}", method = RequestMethod.DELETE)
    public String deletarProduto(@PathVariable Integer productId) {
        Produto produto;

        try {
            produto = produtoService.buscarPorId(productId);
        } catch (Exception e) {
            return "Produto não encontrado";
        }
        try {
            produtoService.delete(productId);
        } catch (Exception error) {
            return "Erro inesperado ao excluir um produto: "+error;
        }
        return "Product excluído com sucesso!!! ";
    }
 }


