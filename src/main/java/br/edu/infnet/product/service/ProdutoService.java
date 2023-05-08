package br.edu.infnet.product.service;

import br.edu.infnet.product.Model.Produto;
import br.edu.infnet.product.repository.IProdutoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    IProdutoRepository repository;

    private final IProdutoRepository pr;
    private static final Logger log = getLogger(ProdutoService.class);

    public ProdutoService(IProdutoRepository pr) {
        this.pr = pr;
    }

    public List<Produto> mostrarTudo(){
        return (List<Produto>) repository.findAll();
    }

    public Produto buscarPorId(int productId) {
        return repository.findById((int) productId).get();
    }

    public Produto buscarPorCodigo(int codProduct) {
        return repository.getByCodProduct(codProduct);
    }

    public void delete(Integer productId) {
        repository.deleteById(productId);
    }

    public String prepararImgURL(MultipartFile multipartFile) throws IOException {
        String path = "C:/CloudServiceDevJavaAtImgFolder/";
        String fileUrl = "";
        String fileName;
        File file;

        file = converterToFile(multipartFile);
        fileName = file.getName();
        fileUrl = path + fileName;
        return fileUrl;
    }

    private File converterToFile(MultipartFile multipartFile) throws IOException {
        String path = "";
        new File(path).mkdirs();
        File convertedFile;
        FileOutputStream fileOutputStream;

        convertedFile = new File(path+multipartFile.getOriginalFilename());
        fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }

    public void Csv(Writer writer) {

        List<Produto> produto = pr.findAll();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Produto p : produto) {
                csvPrinter.printRecord(p.getProdutoId(), p.getProdutoNome(), p.getCotacaoId());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Produto> findAll(){
            return this.pr.findAll();
        }

    public void save(Produto product) {
    }

    public void register(Produto product) {
    }
}

